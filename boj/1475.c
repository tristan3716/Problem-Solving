#include <stdio.h>

int main() {
	int bucket[10] = { 0, };
	int i=0, max;
	char roomNumber[8] = { 0, };

	scanf("%s", &roomNumber);
	while (roomNumber[i] != 0) {
		bucket[roomNumber[i]-'0']++;
		i++;
	}
	bucket[6] = bucket[6] + bucket[9];
	bucket[6] = bucket[6] % 2 == 0 ? bucket[6]/2 : (bucket[6] + 1)/2;

	for (i = 1, max = bucket[0]; i < 9; i++) {
		if (max < bucket[i]) {
			max = bucket[i];
		}
	}

	printf("%d", max);
}