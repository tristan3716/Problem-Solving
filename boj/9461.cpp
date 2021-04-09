#include <iostream>

using namespace std;
using ll = long long;
ll dp[123] = { 0, 1, 1, 1 };

ll re(int n) {
	if (n <= 1)
		return n;
	else if (dp[n])
		return dp[n];
	else
		return (dp[n] = re(n - 2) + re(n - 3));
}

int main() {
	int t;
	cin >> t;
	while (t--) {
		int n;
		cin >> n;
		cout << re(n) << "\n";
	}
}