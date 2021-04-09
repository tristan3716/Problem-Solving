#include <stdio.h>
#include <stdlib.h>

int main(void) 
{
	int m, n;
	int k;
	scanf("%d %d %d", &m, &n, &k);
	int **map_ice = (int **)calloc(m + 1, sizeof(int *));
	int **map_jungle = (int **)calloc(m + 1, sizeof(int *));
	int **map_ocean = (int **)calloc(m + 1, sizeof(int *));
	for (int i = 0; i <= m; i++) {
		map_ice[i] = (int *)calloc(n + 1, sizeof(int));
		map_jungle[i] = (int *)calloc(n + 1, sizeof(int));
		map_ocean[i] = (int *)calloc(n + 1, sizeof(int));
	}

	for (int i = 1; i <= m; i++) {
		for (int j = 1; j <= n; j++) {
			char ch;
			int fi = 0, fj = 0, fo = 0;
			scanf(" %c", &ch);
			//printf("input : %c (%d, %d)\n", ch, i, j);

			switch (ch) {
			case 'J' :
				fj = 1;
				break;
			case 'I' :
				fi = 1;
				break;
			case 'O':
				fo = 1;
				break;
			default:
				printf("Something Wrong!\n");
				break;
			}
			map_jungle[i][j] = fj - map_jungle[i - 1][j - 1] + map_jungle[i][j - 1] + map_jungle[i - 1][j];
			map_ice[i][j] = fi - map_ice[i - 1][j - 1] + map_ice[i][j - 1] + map_ice[i - 1][j];
			map_ocean[i][j] = fo - map_ocean[i - 1][j - 1] + map_ocean[i][j - 1] + map_ocean[i - 1][j];
		}
	}
	for (int i = 1; i <= k; i++) {
		int a, b, c, d;
		scanf("%d %d %d %d", &a, &b, &c, &d);
		int count_jungle = 0, count_ice = 0, count_ocean = 0;
		count_ice = map_ice[a - 1][b - 1] - map_ice[c][b - 1] - map_ice[a - 1][d] + map_ice[c][d];
		count_jungle = map_jungle[a - 1][b - 1] - map_jungle[c][b - 1] - map_jungle[a - 1][d] + map_jungle[c][d];
		count_ocean = map_ocean[a - 1][b - 1] - map_ocean[c][b - 1] - map_ocean[a - 1][d] + map_ocean[c][d];
		printf("%d %d %d\n", count_jungle, count_ocean, count_ice);
	}

	return 0;
}