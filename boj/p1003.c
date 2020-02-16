/**
 * @title 피보나치 함수 S3
 * @see https://www.acmicpc.net/problem/1003
 * @since 2019.07.21
 * @category dp
 * @complexity O(n^2) -> 0ms
 * @description
 *      주어진 int fibonacci(int) 함수를 이용하여 N번째 피보나치 수를 계산할 때,
 *      각각의 fibonacci(x)가 호출되는 횟수 출력
 *      N <= 40 이므로 DP 이용
 */

#include <stdio.h>

int main() {
	int sol[41][2];
	sol[0][0] = 1;
	sol[0][1] = 0;
	sol[1][0] = 0;
	sol[1][1] = 1;
	int old_n = 1;
	int t;
	scanf("%d", &t);
	for (int l = 0; l < t; l++) {
		int n;
		scanf("%d", &n);
		if (n > old_n) {
			for (int i = old_n + 1; i <= n; i++) {
				sol[i][0] = sol[i - 1][0] + sol[i - 2][0];
				sol[i][1] = sol[i - 1][1] + sol[i - 2][1];
			}
			old_n = n;
		}
		printf("%d %d\n", sol[n][0], sol[n][1]);
	}
	return 0;
}