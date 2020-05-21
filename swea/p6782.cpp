#include <iostream>
#include <algorithm>
#include <map>

using namespace std;
        using ll = long long;

        ll n;
        ll ans;
        map<ll, ll> m;

        int main() {
        ios_base::sync_with_stdio(false);
        cin.tie(NULL);
        cout.tie(NULL);
        int t;
        cin >> t;

        for (int tc = 1; tc <= t; ++tc) {
        cout << "#" << tc << " ";

        cin >> n;

        ll ans = 0;
        while (true) {
        if (m.count(n)) { ans = m[n];  break; }
        else if (n == 1) { ans++; break; }
        else if (n == 2) { break; }
        else {
        double xx = sqrt(n);
        if (abs(xx - (ll)xx) <= 10e-12) {
        ans++;
        n = (ll)xx;
        }
        else {
        ll p = (ll)ceil(sqrt(n));
        ans += p * p - n + 1;
        n = p;
        }
        }
        }

        cout << ans;

        cout << "\n";
        }
        }