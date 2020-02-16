/**
 * @title 다리 만들기 G4
 * @see https://www.acmicpc.net/problem/2146
 * @since 2019.08.06
 * @category bfs, graph, ccl
 * @description
 *      섬들을 식별한 후, 각각의 섬들간의 최단거리를 계산하면 끝.
 *      섬에 속한 땅들(최대 n^2개)끼리 모두 연결해보는 방식으로 계산하였음 (n^4)
 *      더 효율적으로 계산할 수 있는 방법이 존재할 듯.
 */

#include <stdio.h>

typedef struct _Pos {
	char x;
	char y;
} Pos;

Pos island[5001][9999];

int isValid(int map[101][101][2], int n, int x, int y) {
	if (map[x][y][0] == 0) {
		return 0;
	}
	if (x > n || y > n || x < 0 || y < 0) {
		return 0;
	}
	return 1;
}

int getDistance(Pos a, Pos b) {
	int dx = a.x - b.x;
	dx = dx < 0 ? -dx : dx;
	int dy = a.y - b.y;
	dy = dy < 0 ? -dy : dy;
	return dx + dy - 1;
}

void classify(int map[101][101][2], int n, int x, int y, int c) {
	if (map[x][y][1]) {
		return;
	}
	map[x][y][1] = c;
	int t = (island[c][0].x)++;
	island[c][t].x = (char)x;
	island[c][t].y = (char)y;

	if (isValid(map, n, x, y + 1))
		classify(map, n, x, y + 1, c);
	if (isValid(map, n, x, y - 1))
		classify(map, n, x, y - 1, c);
	if (isValid(map, n, x + 1, y))
		classify(map, n, x + 1, y, c);
	if (isValid(map, n, x - 1, y))
		classify(map, n, x - 1, y, c);
}
int main(void) {
	int n;
	int map[101][101][2];
	int i, j, k, l;

	scanf("%d", &n);
	for (i = 0; i < n; i++) {
		for (j = 0; j < n; j++) {
			scanf("%d", &map[i][j][0]);
			map[i][j][1] = 0;
		}
	}
	for (i = 0; i <= 5000; i++) {
		island[i][0].x = 1;
	}
	int island_number = 1;
	for (i = 0; i < n; i++) {
		for (j = 0; j < n; j++) {
			if (map[i][j][0] == 1 && map[i][j][1] == 0) {
				classify(map, n, i, j, island_number);
				++island_number;
			}
		}
	}
	int bflag = 0;
	int min = getDistance(island[1][1], island[2][1]);
	for (i = 1; i < island_number - 1; i++) {
		for (j = 1; j < island[i][0].x; j++) {
			for (k = i + 1; k < island_number; k++) {
				for (l = 1; l < island[k][0].x; l++) {
					int dst = getDistance(island[i][j], island[k][l]);
					if (min > dst) {
						min = dst;
					}
					if (min == 1)
						bflag = 1;
					if (bflag)
						break;
				}
				if (bflag)
					break;
			}
			if (bflag)
				break;
		}
		if (bflag)
			break;
	}
	printf("%d", min);
	return 0;
}