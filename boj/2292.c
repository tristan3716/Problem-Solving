#include <stdio.h>

int main() {
	int nValue;
	int shell = 1;
	int count = 1;
	scanf("%d", &nValue);
	
	while (count < nValue) {
		count = count + shell * 6;
		shell++;
	}
	printf("%d", shell);
	return 0;
}