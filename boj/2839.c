#include <stdio.h>

int main() {
	int nValue, nValueC;
	int nCountFive = 0, nCountThree = 0;
	int res;
	scanf("%d", &nValue);
	nValueC = nValue;
	while (nValue >= 5) {
		nValue -= 5;
		nCountFive++;
	}
	while (nCountFive >= 0) {
		res = nCountFive * 5 + nCountThree * 3;
		if (res > nValueC) {
			nCountThree = 0;
			nCountFive--;
		}
		else if (res == nValueC) {
			printf("%d", nCountFive + nCountThree);
			return 0;
		}
		nCountThree++;
	}
	printf("%d", -1);
}