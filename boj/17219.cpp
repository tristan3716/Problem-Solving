#include <iostream>
#include <unordered_map>
#include <string>

using namespace std;

string st, pw;
int n, k;
int main() {
    constexpr int p = 31;
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    unordered_map<string, string> m[p];

    cin >> n >> k;
    for (;n--;) {
        cin >> st >> pw;
        m[st[0] % p][st] = pw;
    }
    for (;k--;) {
        cin >> st;
        cout << m[st[0] % p][st] << '\n';
    }

    return 0;
}