#include <stdio.h>

int main() {
	int nValue;
	int shell = 1;
	int count = 1;
	int molecule, denominator;

	scanf("%d", &nValue);

	while (count+shell <= nValue) {
		count = count + shell;
		shell++;
	}
	molecule = shell;
	denominator = 1;
	nValue -= count;
	
	while (nValue > 0) {
		molecule--;
		denominator++;
		nValue--;
	}
	if (shell % 2 == 0) {
		int temp = molecule;
		molecule = denominator;
		denominator = temp;
	}

	printf("%d/%d", molecule, denominator);

	return 0;
}