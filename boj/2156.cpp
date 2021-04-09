#include <stdio.h>

int max(int a, int b, int c) {
	if (a > b) {
		if (a > c)
			return a;
		else
			return c;
	}
	else {
		if (b > c)
			return b;
		else
			return c;
	}
}

int main(void) {
	int n;
	int dp[10001];
	int t;
	int ot = 0;
	scanf("%d", &n);
	dp[0] = 0;
	scanf("%d", &t);
	dp[1] = t;
	if (n > 1) {
		scanf("%d", &t);
		dp[2] = dp[1] + t;
		ot = t;
	}
	for (int i = 3; i <= n; i++) {
		scanf("%d", &t);
		dp[i] = max(dp[i - 1], dp[i - 2] + t, dp[i - 3] + ot + t);
		ot = t;
	}
	printf("%d\n", dp[n]);

	return 0;
}