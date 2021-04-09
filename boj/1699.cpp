#include <iostream>
#include <algorithm>
#include <cmath>
using namespace std;

int main() {
    int dp[100005]={0};
    int n;
    cin>>n;
    for (int i=1; i<=n; i++) {
        dp[i] = i;
    }
    for (int i = 1; i <= sqrt(n); i++) {
        dp[i * i] = 1;
    }
    for (int i = 4; i <= n; i++) {
        if (dp[i] == 1)
            continue;
        for (int j = 2; j <= i; j++) {
            int jj = j * j;
            if(jj > i) {
                break;
            }
            if(jj == n) {
                puts("1");
                return 0;
            }
            dp[i] = min(dp[i], dp[i - jj] + 1);
        }
    }
    cout<<dp[n];

    return 0;
}
