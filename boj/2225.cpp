#include <iostream>
#include <vector>

using namespace std;

int main(){
    vector<vector<int> > v(205, vector<int>(205, 0));
    int n, k;
    cin >> n >> k;
    for (auto & x : v[1]) {
        x = 1;
    }

    for (int i = 1; i <= k; ++i) {
        for (int j = 0; j <= n; ++j) {
            for (int x = 0; x <= j; ++x) {
                v[i][j] = (v[i][j] % 1'000'000'000 + v[i-1][j-x] % 1'000'000'000) % 1'000'000'000;
            }
        }
    }
    cout << v[k][n];
}
