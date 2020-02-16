/**
 * @title 오목 S3
 * @see https://www.acmicpc.net/problem/2615
 * @since 2020.02.01
 * @category implements
 * @complexity O(19*19*4) -> 0ms
 * @description
 *      비트 연산으로 짜볼라다 망했음
 */

#include <iostream>
#include <algorithm>

constexpr int n = 21;

constexpr int direction[][2] = {
                {-1, 1},
        /*   */ { 0, 1},
        { 1, 0},{ 1, 1}};

struct Result {
public:
    int id;
    int y;
    int x;
    Result() : id(-1) {}
    Result(int id, int y, int x) : id(id), y(y), x(x) {}
    friend std::ostream& operator<<(std::ostream& os, Result r) {
        if (r.id == -1) {
            os << 0;
            return os;
        }
        os << r.id << "\n";
        os << r.y << " " << r.x << "\n";
        return os;
    }
};

int raw[n][n] = {};

int main() {
    using namespace std;

    for (int i = 1; i < n-1; ++i) {
        for (int j = 1; j < n-1; ++j) {
            int t;
            scanf("%1d", &t);
            raw[i][j] = t;
        }
    }

    Result res;

    for (int j = 1; j < n-1; ++j) {
        for (int i = 1; i < n - 1; ++i) {
            for (int k = 0; k < 4; ++k) {
                const int &type = raw[i][j];
                if (type == 0) {
                    continue;
                }
                int ni = i;
                int nj = j;
                while (true) {
                    if (type != raw[ni][nj]) {
                        break;
                    }
                    ni = ni - direction[k][0];
                    nj = nj - direction[k][1];
                }
                int cnt = 0;

                while (true) {

                    ni = ni + direction[k][0];
                    nj = nj + direction[k][1];

                    if (type != raw[ni][nj]) {
                        break;
                    }
                    cnt++;

                }
                if (cnt == 5) {
                    res.id = type;
                    res.x = j;
                    res.y = i;
                    goto result;
                }
            }
        }
    }
    result:
    cout << res;
}