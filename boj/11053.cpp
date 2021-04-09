#include <stdio.h>
#include <malloc.h>

int main(void)
{
	int n;
	int *arr;
	int *dp;
	int max = 1;
	scanf("%d", &n);
	arr = (int *)malloc(sizeof(int) * n);
	dp = (int *)calloc(n, sizeof(int));
	for (int i = 0; i < n; i++) {
		scanf("%d", &arr[i]);
		dp[i] = 1;
		for (int j = 0; j < i; j++) {
			if (arr[j] < arr[i] && dp[i] <= dp[j]) {
				dp[i] = dp[j] + 1;
			}
		}
		if (max < dp[i])
			max = dp[i];
	}

	printf("%d", max);

	return 0;
}