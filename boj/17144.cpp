#include <iostream>
#include <vector>

using namespace std;

int R, C, T;
vector<vector<int> > v;

#define DI 0
#define DJ 1
constexpr int dir[][2] = {	{-1, 0}, 
					{0, -1}, /* */  {0, 1}, 
							{ 1, 0} };
constexpr int dirUpper[][2] = {  {0,  1}, {-1, 0}, {0, -1}, { 1, 0} };
constexpr int dirLower[][2] = {  {0,  1}, { 1, 0}, {0, -1}, {-1, 0} };
constexpr int dirSize = sizeof(dir) / sizeof(dir[0]);

bool isValidPos(const int &i, const int &j) {
	return (i >= 0 && j >= 0 && i < R && j < C);
}

bool isAirPurifier(const int &i, const int &j) {
	return (v[i][j] == -1);
}

bool isDiffusible(const int &i, const int &j) {
	return isValidPos(i, j)
		&& !isAirPurifier(i, j);
}

void diffuse() {
	vector<vector<int> > next(R, vector<int>(C, 0));
	for (int i = 0; i < R; ++i) {
		for (int j = 0; j < C; ++j) {
			if (isAirPurifier(i, j)) {
				continue;
			}
			int &origin = v[i][j];
			int quantity = (v[i][j] / 5);

			for (int d = 0; d < dirSize; ++d) {
				int ni = i + dir[d][DI];
				int nj = j + dir[d][DJ];
				if (isDiffusible(ni, nj)) {
					next[ni][nj] += quantity;
					origin -= quantity;
				}
			}
		}
	}
	for (int i = 0; i < R; ++i) {
		for (int j = 0; j < C; ++j) {
			if (!isAirPurifier(i, j)) {
				v[i][j] += next[i][j];
			}
		}
	}
}

void operate(const int &si, const int &sj, const int d[][2]) {
	int ni = si;
	int nj = sj;
	int bi = si;
	int bj = sj;
	int currentDir = 0;
	int oldValue = 0;
	int temporayValue;

	bool isFirst = true;
	while (true) {
		if (!isFirst) {
			temporayValue = v[ni][nj];
			v[ni][nj] = oldValue;
			oldValue = temporayValue;
		}
		isFirst = false;

		ni = bi + d[currentDir][DI];
		nj = bj + d[currentDir][DJ];
		
		if (!isValidPos(ni, nj)) {
			currentDir++;
			isFirst = true;
			ni = bi;
			nj = bj;
			/* Turn condition */
			continue;
		}

		if (isAirPurifier(ni, nj)) {
			/* End condition */
			break;
		}

		bi = ni;
		bj = nj;
	}
}

int countDust() {
	int sum = 0;

	for (int i = 0; i < R; ++i) {
		for (int j = 0; j < C; ++j) {
			if (!isAirPurifier(i, j)) {
				sum += v[i][j];
			}
		}
	}

	return sum;
}

int main() {
	cin >> R >> C >> T;

	pair<int, int> app[2];
	int appCnt = 0;
	v = vector<vector<int> >(R, vector<int>(C, 0));
	for (int i = 0; i < R; ++i) {
		for (int j = 0; j < C; ++j) {
			cin >> v[i][j];
			if (v[i][j] == -1) {
				app[appCnt] = pair<int, int>(i, j);
				appCnt++;
			}
		}
	}
	
	for (int tp = 0; tp < T; ++tp) {
		diffuse();
		operate(app[0].first, app[0].second, dirUpper);
		operate(app[1].first, app[1].second, dirLower);
	}

	cout << countDust();
}