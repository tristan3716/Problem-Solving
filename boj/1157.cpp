#include <iostream>

int cn[27];
int main() {
    using namespace std;
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    string x;
    cin >> x;
    for (char y : x) {
        if (y >= 'a')
            cn[y - 'a']++;
        else
            cn[y - 'A']++;
    }
    
    int max = -1;
    int ___m = -1;
    int mi;
    for (int i = 0; i < 26; ++i) {
        if (cn[i] > ___m) {
            if (cn[i] > max) {
                ___m = max;
                max = cn[i];
                mi = i;
            }
            else {
                ___m = cn[i];
            }
        }
    }

    if (max == ___m)
        cout << '?';
    else
        cout << (char)(mi + 'A');

    return 0;
}