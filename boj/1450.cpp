#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

void nap(const vector<int> &v, int sum, int depth, int n, int &c, vector<int> &vr) {
    if (sum > c)
        return;
    if (depth > n){
        vr.push_back(sum);
        return;
    }
    nap(v, sum + v[depth], depth + 1, n, c, vr);
    nap(v, sum, depth + 1, n, c, vr);
}

void print_vector(const vector<int> &v) {
    for (size_t i = 0u; i < v.size(); ++i) {
        cout << v[i];
        if (i+1 < v.size()) {
            cout << " ";
        }
    }
}

int main() {
    int n, c;
    cin >> n >> c;
    vector<int> vw(n);
    vector<int> v1, v2;
    v1.reserve(n);
    v2.reserve(n);

    for (int i = 0; i < n; ++i) {
        cin >> vw[i];
    }
    nap(vw, 0, 0, n/2-1, c, v1);
    nap(vw, 0, n/2, n-1, c, v2);
    sort(v1.begin(), v1.end());
    sort(v2.begin(), v2.end());
    int cnt = 0;
    for (int i1 = 0, i2 = v2.size()-1; i1 < v1.size() && i2 < v2.size(); ++i1) {
        while (v1[i1] + v2[i2] > c) {
            --i2;
        }
        cnt += i2 + 1;
    }
    cout << cnt;
}
