#include <iostream>
#include <vector>

using namespace std;

int min_distance = 12345678;

void solve(vector<vector<int> > &v, const int &mc, int cc, int ci, int cj, int depth) {
    if (cc == mc) {
        static int n = v.size();
        vector<vector<int> > distance1(n+2, vector<int>(n+2, 10000));
        vector<vector<int> > distance2(n+2, vector<int>(n+2, 10000));
        vector<vector<int> > distance3(n+2, vector<int>(n+2, 10000));
        vector<vector<int> > distance4(n+2, vector<int>(n+2, 10000));

        for (size_t i = 0; i < n; ++i) {
            for (size_t j = 0; j < n; ++j) {
                int ii = i + 1;
                int ji = j + 1;
                if (v[i][j] == 2) {
                    distance1[ii][ji] = 0;
                }
                else {
                    distance1[ii][ji] 
                        = min(distance1[ii][ji-1] == 10000?  9999 : (distance1[ii][ji-1])
                         ,distance1[ii-1][ji] == 10000 ? 9999 : (distance1[ii-1][ji])) 
                         + 1;
                }
                ii = n - i;
                ji = n - j;
                if (v[n-i-1][n-j-1] == 2) {
                    distance2[ii][ji] = 0;
                }
                else {
                    distance2[ii][ji] 
                        = min(distance2[ii][ji+1] == 10000 ? 9999 : (distance2[ii][ji+1]),
                         distance2[ii+1][ji] == 10000 ? 9999 : (distance2[ii+1][ji])) 
                         + 1;
                }

                ii = i + 1;
                ji = n - j;
                if (v[i][n-j-1] == 2) {
                    distance3[ii][ji] = 0;
                }
                else {
                    distance3[ii][ji] 
                        = min(distance3[ii][ji+1] == 10000 ? 9999 : (distance3[ii][ji+1])
                         ,distance3[ii-1][ji] == 10000 ? 9999 : (distance3[ii-1][ji])) 
                         + 1;
                }
                ii = n - i;
                ji = j + 1;
                if (v[n-i-1][j] == 2) {
                    distance4[ii][ji] = 0;
                }
                else {
                    distance4[ii][ji] 
                        = min(distance4[ii][ji-1] == 10000 ? 9999 : (distance4[ii][ji-1])
                         ,distance4[ii+1][ji] == 10000 ? 9999 : (distance4[ii+1][ji])) 
                         + 1;
                }
            }
        }
        
        for (size_t i = 0u; i < n+2; ++i) {
            for (size_t j = 0u; j < n+2; ++j) {
                distance4[i][j] = min(min(min(distance1[i][j], distance2[i][j]), distance3[i][j]), distance4[i][j]);
            }
        }
        int sum = 0;
        for (size_t i = 0u; i < n; ++i) {
            for (size_t j = 0u; j < n; ++j) {
                int ii = i + 1;
                int ji = j + 1;
                if (v[i][j] == 1) {
                    sum += distance4[ii][ji];
                }
            }
        }
        
        if (min_distance > sum) {
            min_distance = sum;
        }
        
    }

    int cx = -1, cy = -1;
    for (int i = ci; i < v.size(); ++i) {
        for (int j = 0; j < v[0].size(); ++j) {
            if (v[i][j] == 2) {
                if (ci <= i && cj < j) {
                    cy = i;
                    cx = j;
                    goto cal;
                }
            }
        }
        cj = -1;
    }
    if (cx == -1)
        return;
    cal:

    solve(v, mc, cc, cy, cx, depth+1);
    v[cy][cx] = 0;
    solve(v, mc, cc-1, cy, cx, depth+1);
    v[cy][cx] = 2;
}

int main() {
    int n, m;
    cin >> n >> m;
    vector<vector<int> > v(n, vector<int>(n));
    int c = 0;
    for (auto i = 0u; i < v.size(); ++i) {
        for (auto j = 0u; j < v.size(); ++j) {
            cin >> v[i][j];
            if (v[i][j] == 2) {
                ++c;
            }
        }
    }
    

    solve(v, m, c, 0, -1, 0);
    cout << min_distance;
}
