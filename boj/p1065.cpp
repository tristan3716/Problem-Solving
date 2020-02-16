/**
 * @title 한수 S4
 * @see https://www.acmicpc.net/problem/1065
 * @since 2019.07.04
 * @category brute-force
 * @complexity O(nlogn) -> 0ms
 * @description
 *      100 이상의 모든 수에 대한 한수 검사 진행
 */

#include <iostream>

bool isHansu(int n) {
	int last = n % 10;
	n /= 10;
	int before = n % 10;
	int diff = before - last;

	while (n >= 10) {
		last = n % 10;
		n /= 10;
		before = n % 10;
		if ((before - last) != diff)
			return false;
	}

	return true;
}

int solution(int n) {
	int count = 0;
	if (n < 100)
		return n;
	else
		count = 99;

	for (int i = 100; i <= n; i++) {
		if (isHansu(i)) {
			count += 1;
		}
	}

	return count;
}


int main() {
	int n;
	std::cin >> n;

	std::cout << solution(n) << std::endl;

	return 0;
}