#include <iostream>
#include <algorithm>

using namespace std;

        int main() {
        int t;
        cin >> t;
        int arr[1000];

        for (int tc = 1; tc <= t; ++tc) {
        cout << "#" << tc << " ";

        int n, k;
        cin >> n >> k;
        for (int i = 0; i < n; i++) {
        cin >> arr[i];
        }

        sort(arr, arr + n, less<int>());

        double sr = 0;

        for (int i = n - k; i < n; ++i) {
        sr = sr + arr[i];
        sr = sr / 2;
        }
        cout << fixed << sr;
        cout << "\n";
        }
        }