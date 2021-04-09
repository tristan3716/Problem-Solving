#include <iostream>
 
using namespace std;
constexpr int p = 1'000'000'007;
constexpr int p2 = p-2;
using ll = long long;
 
ll modExp(ll a, int b) {
    ll r = 1;
    ll c = a;
    while (b != 0) {
        if ((b & 1) != 0) {
            r = (r * c) % p;
        }
        c = (c * c) % p;
        b >>= 1;
    }
    return r;
}
 
ll fact[4000001];
void pre() {
    fact[0] = 1;
    for (int i = 1; i < 4000001; i++) {
        fact[i] = (fact[i - 1] * i) % p;
    }
}
 
int t=2, n, r;
ll x, y, res;
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
 
    pre();
    //cin >> t;
    //++t;
    for (int tc = 1; tc != t; tc++) {
        //cout << "#" << tc << " ";
 
        cin >> n >> r;
        x = fact[n];
        y = (fact[r] * fact[n - r]) % p;
        res = (x * modExp(y, p2)) % p;
 
        cout << res << "\n";
    }
}