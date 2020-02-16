/**
 * @title Fly me to the Alpha Centauri S1
 * @see https://www.acmicpc.net/problem/1011
 * @since 2019.01.31
 * @category math
 * @complexity O(1) -> 0ms
 * @description
 *      거리의 차의 제곱근만큼 이동...
 */

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