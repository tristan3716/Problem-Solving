#include <iostream>
#include <vector>
#include <queue>

using namespace std;
int n, m;
vector<vector<char> > map;
vector<vector<int> > id;
int parent[999];
int height[999];

struct pos {
	int r, c;
	int from;
	pos(int r, int c, int from) : r(r), c(c), from(from) {}
};
queue<pos> q;

void parse() {
	cin >> n >> m;

	map.resize(n + 2);
	id.resize(n + 2);
	for (int i = 0; i < n + 2; i++) {
		map[i].resize(m + 2);
		id[i].resize(m + 2);
		fill_n(id[i].begin(), m + 2, -1);
	}
	for (int i = 0; i < n + 2; i++) {
		map[i][0] = '.';
		map[i][m + 1] = '.';
	}
	for (int j = 0; j < m + 2; j++) {
		map[0][j] = '.';
		map[n + 1][j] = '.';
	}
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= m; j++) {
			cin >> map[i][j];
		}
	}
}

constexpr int direction_4[][2] = { 
	{-1, 0}, {1, 0}, {0, -1}, {0, 1} };
constexpr int direction_8[][2] = { 
	{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {1, -1}, {1, 1}, {-1, 1} };

bool isIn(int r, int c) {
	return (r >= 0 && c >= 0 && r < n + 2 && c < m + 2);
}

int islandIndex = 1;
void labeling(int r, int c) {
	for (const int* x : direction_8) {
		int nr = r + x[0];
		int nc = c + x[1];

		if (isIn(nr, nc) && id[nr][nc] == -1) {
			if (map[nr][nc] == 'x') {
				id[nr][nc] = islandIndex;
				labeling(nr, nc);
			}
			else {
				q.push({ nr, nc, islandIndex });
			}
		}
	}
}

void dfs(int r, int c, int p) {
	for (const int *x : direction_4) {
		int nr = r + x[0];
		int nc = c + x[1];

		if (isIn(nr, nc) && id[nr][nc] == -1) {
			if (map[nr][nc] == 'x') {
				id[nr][nc] = islandIndex;
				labeling(nr, nc);
				parent[islandIndex] = p;
				islandIndex++;
			}
			else {
				id[nr][nc] = 0;
				dfs(nr, nc, p);
			}
		}
	}
}

void bfs() {
	while (!q.empty()) {
		int size = q.size();
		for (int i = 0; i < size; ++i) {
			pos c = q.front(); q.pop();

			if (id[c.r][c.c] == -1) {
				dfs(c.r, c.c, c.from);
			}
		}
	}
}

int cnt[99];
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	parse();

	id[0][0] = 0;
	dfs(0, 0, 0);
	bfs();

	for (int i = islandIndex - 1; i >= 0; i--) {
		height[parent[i]] = max(height[parent[i]], height[i] + 1);
	}
	for (int i = 0; i < islandIndex; i++) {
		cnt[height[i]]++;
	}

	if (islandIndex == 1) {
		cout << -1;
	} else {
		for (int i = 0; cnt[i] != 0; ++i) {
			cout << cnt[i] << " ";
		}
	}
}