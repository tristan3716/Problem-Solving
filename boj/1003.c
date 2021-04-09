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