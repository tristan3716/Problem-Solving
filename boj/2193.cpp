#include <stdio.h>

int main(void) {
	int n;
	scanf("%d", &n);
	long long sol[1001];
	sol[1] = 1;
	sol[2] = 1;
	for (int i = 3; i <= n; i++) {
		sol[i] = (sol[i - 1] + sol[i - 2]);
	}
	printf("%lld", sol[n]);
	return 0;
}