/**
 * @title 가장 큰 정사각형 G5
 * @see https://www.acmicpc.net/problem/1915
 * @since 2019.07.06
 * @category dp
 * @complexity O(n^2) -> 84ms (n = 1000)
 * @description
 *      엑셀 짱
 * @see https://uk-coding.tistory.com/entry/%EB%B0%B1%EC%A4%80BOJ-1915%EB%B2%88-%EA%B0%80%EC%9E%A5-%ED%81%B0-%EC%A0%95%EC%82%AC%EA%B0%81%ED%98%95
 */


#include <vector>
#include <iostream>
#include <algorithm>

int main()
{
	int n, m;

	std::cin >> n >> m;
	std::vector<std::vector<int>> v(n+1, std::vector<int>(m+1, 0));

	int c;
	int max = 0;
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= m; j++) {
			scanf("%1d", &c);

			if (c) {
				v[i][j] = 1 + std::min({ v[i - 1][j - 1], v[i][j - 1], v[i - 1][j] });
				max = std::max(max, v[i][j]);
			}
		}
	}

	std::cout << max * max;

	return 0;
}