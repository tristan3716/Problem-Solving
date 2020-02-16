/**
 * @title 영식함수 Unrated(2020.02.16)
 * @see https://www.acmicpc.net/problem/1596
 * @since 2019.07.08
 * @category back-tracking, brute-force
 * @complexity O(m logn logn) -> 80ms (n = 1'000'000'000, m = 160'000)
 * @description
 *      스터디 초기에 고통받으면서 풀었던 문제
 *      문제를 뒤집어서 역으로 빌딩하면 금방 답이 나옴
 * @see https://uk-coding.tistory.com/entry/%EB%B0%B1%EC%A4%80BOJ-1596%EB%B2%88-%EC%98%81%EC%8B%9D%ED%95%A8%EC%88%98
 */

#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>

int aa;
int bb;

void find(std::vector<int>& v, const int target_number[],
	int digit_level, const int & max_digit, int first_digit, int multiplier, int number) {
	if (digit_level == max_digit) {
		if (first_digit) {
			v.push_back(number);
		}
	}
	else {
		int next_multiplier = multiplier * 10;
		int next_first_digit = first_digit + target_number[digit_level];

		if (next_first_digit < 10) {
			find(v, target_number, digit_level + 1, max_digit, next_first_digit, next_multiplier, number + next_multiplier * next_first_digit);
		}
		next_first_digit = first_digit - target_number[digit_level];
		if (next_first_digit >= 0) {
			find(v, target_number, digit_level + 1, max_digit, next_first_digit, next_multiplier, number + next_multiplier * next_first_digit);
		}
	}
}

void makeFindSet(std::vector<int>& v, std::vector<int> copy, const int & i) {
	for (std::vector<int>::iterator v_iter = copy.begin(); v_iter != copy.end(); ++v_iter) {
		int target_number_array[9];
		int target_number = *v_iter;

		for (int j = 0; j < i; ++j) {
			target_number_array[j] = target_number % 10;
			target_number /= 10;
		}

		for (int j = 0; j < 10; ++j) {
			find(v, target_number_array, 0, i - 1, j, 1, j);
		}
	}
	sort(v.begin(), v.end());
	v.erase(unique(v.begin(), v.end()), v.end());
}

int solution() {
	const int max_num = 1000000000;

	std::cin >> aa >> bb;

	if (bb == max_num)
		--bb;

	int b_length = static_cast<int>(log10(bb)) + 1;
	std::vector<int> v;

	v.push_back(7);
	for (int i = 2; i <= b_length; ++i) {
		makeFindSet(v, v, i);
	}

	int count = 0;
	for (std::vector<int>::iterator i = v.begin(); i != v.end(); ++i) {
		if (*i >= aa && *i <= bb) {
			count++;
		}
	}
	return count;
}

int main() {
	std::cout << solution() << std::endl;

	return 0;
}