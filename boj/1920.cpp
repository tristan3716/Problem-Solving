#include <algorithm>
#include <iostream>
using namespace std;

int n, m;
int a[100001];

int g(int x){
    int it = lower_bound(a, a+n, x) - a;
    if ( it == n || a[it] != x ) return 0;
    else return 1;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cin >> n;
    for (auto i = 0; i < n; ++i) {
        cin >> a[i];
    }
    sort(a, a+n);
    cin >> m;
    for (auto i = 0; i < m; ++i) {
        int t;
        cin >> t;
        cout << g(t) << "\n";
    }
}