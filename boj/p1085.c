/**
 * @title 직사각형에서 탈출 B2
 * @see https://www.acmicpc.net/problem/1085
 * @since 2019.01.30
 * @category math
 * @complexity O(1) -> 0ms
 * @description
 *      (x, y) 로부터 직사각형의 변까지의 최단거리 계산
 *      역사적인 백준 첫 문제
 */

#include <stdio.h>

enum DIRECT { UP, DOWN, LEFT, RIGHT, DIRECT_COUNT };

int calculateDistance(int currentX, int currentY, int sizeWidth, int sizeHeight){
	int distance[DIRECT_COUNT];
	int min, i;
	// y-axis
	distance[UP] = sizeHeight - currentY;
	distance[DOWN] = currentY;
	// x-axis
	distance[LEFT] = currentX;
	distance[RIGHT] = sizeWidth - currentX;

	for (i = 0, min = distance[UP]; i < 4; i++) {
		if (min > distance[i]) {
			min = distance[i];
		}
	}
	return min;
}

int main() {
	int x, y, w, h;
	scanf("%d %d %d %d", &x, &y, &w, &h);
	printf("%d", calculateDistance(x, y, w, h));
}