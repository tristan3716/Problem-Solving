#include <iostream>
#include <queue>

int n, m;
int map[8][8];
bool visit[8][8];
int area;
int left_count;

typedef struct _Pos {
    int x;
    int y;
} Pos;

const int dx[4] = { 1, -1, 0, 0 };
const int dy[4] = { 0, 0, 1, -1 };

bool isValid(int x, int y) {
    if (x < 0 || y < 0 || x >= n || y >= m) {
        return false;
    }
    return true;
}

std::queue<Pos> q;

int bfs(int i, int j) {
    bool corrupted = false;
    int area = 0;
    if (map[i][j] != 1) {
        q.push({ i, j });
    }

    while (!q.empty()) {
        Pos t = q.front(); q.pop();

        if (visit[t.x][t.y])
            continue;
        visit[t.x][t.y] = true;
        left_count--;

        int x, y;
        for (auto i = 0; i < 4; ++i) {
            x = t.x + dx[i];
            y = t.y + dy[i];
            if (isValid(x, y) && map[x][y] != 1 && !visit[x][y]) {
                q.push({ x, y });
            }
        }

        if (map[t.x][t.y] == 2) {
            corrupted = true;
            break;
        }
        else {
            ++area;
        }
    }
    while (!q.empty()) {
        Pos t = q.front(); q.pop();

        if (visit[t.x][t.y])
            continue;
        visit[t.x][t.y] = true;
        left_count--;

        int x, y;
        for (auto i = 0; i < 4; ++i) {
            x = t.x + dx[i];
            y = t.y + dy[i];
            if (isValid(x, y) && map[x][y] != 1 && !visit[x][y]) {
                q.push({ x, y });
            }
        }
    }
    if (corrupted) {
        return 0;
    }
    return area;
}

void getArea() {
    left_count = n * m;
    int temp_area = 0;

    std::fill(&visit[0][0], &visit[n-1][m], 0);

    for (auto i = 0; i < n; ++i) {
        for (auto j = 0; j < m; ++j) {
            if (!visit[i][j]) {
                temp_area += bfs(i, j);
            }
            if (temp_area + left_count < area) {
                return;
            }
        }
    }
    if (temp_area > area) {
        area = temp_area;
    }
}

void makeWall(int cnt, int is, int js) {
    if (cnt == 3) {
        getArea();
    }
    else if (cnt > 3) {
        return;
    }
    else {
        int j = js;
        for (int i = is; i < n; ++i, j = 0) {
            for (; j < m; ++j) {
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    makeWall(cnt + 1, i, j);
                    map[i][j] = 0;
                }
            }
        }
    }
}

int main(void) {
    using namespace std;

    cin >> n >> m;
    for (auto i = 0; i < n; ++i) {
        for (auto j = 0; j < m; ++j) {
            cin >> map[i][j];
        }
    }

    makeWall(0, 0, 0);
    cout << area;

    return 0;
}