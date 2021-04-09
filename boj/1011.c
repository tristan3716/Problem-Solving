#include <stdio.h>
#include <math.h>

int main() {
	int nTestcase;
	int src, dst;
	int distance, sqrtDistance;
	int i;
	scanf("%d", &nTestcase);
	for (i = 0; i< nTestcase; i++) {
		scanf("%d %d", &src, &dst);
		distance = dst - src;
		
		sqrtDistance = (int)sqrt(distance);
		if (distance == sqrtDistance * sqrtDistance)
			printf("%d\n", 2 * sqrtDistance - 1);
		else if (distance <= sqrtDistance * sqrtDistance + sqrtDistance)
			printf("%d\n", 2 * sqrtDistance);
		else
			printf("%d\n", 2 * sqrtDistance + 1);
	}
}