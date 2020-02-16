/**
 * @title 발전소 G1
 * @see https://www.acmicpc.net/problem/1102
 * @since 2019.09.05
 * @category dp, bitmask
 * @complexity O(n*2^p) -> 20ms
 * @description
 *      발전소의 가동 상태에 따른 cost 조사
 *      p개의 발전소가 켜질 때까지 재귀
 */

 #include <iostream>
 #include <string>
 #include <cstring>

 #ifdef _MSC_VER
 #	include <intrin.h>
 #	define __builtin_popcount __popcnt
 #endif

 namespace {
 	constexpr int INFINITE = 1000000000;

 	inline int min(int a, int b) {
 		return a < b ? a : b;
 	}
 }


 int solve(int state, int dp[], const unsigned int &p, const unsigned int &n, const int cost[][16]) {
 	int &res = dp[state];
 	if (res != -1) {
 		return res;
 	}
 	else if (__builtin_popcount(state) >= p) { // base
 		return 0;
 	}
 	else {
 		res = INFINITE;
 		for (auto i = 0u; i < n; ++i) {
 			if (!(state & (1 << i)))
 				continue;
 			for (auto j = 0u; j < n; ++j) {
 				if (!(state & (1 << j))) {
 					res = min(res, cost[i][j] + solve((state | (1 << j)), dp, p, n, cost));
 				}
 			}
 		}
 		return res;
 	}
 }

 int main() {
 	using namespace std;
 	ios_base::sync_with_stdio(false);
 	cin.tie(NULL);

 	int init = 0b00;
 	unsigned int n;
 	int cost[16][16];
 	int dp[1 << 16];
 	unsigned int p;
 	string in;

 	memset(dp, -1, sizeof(dp));
 	cin >> n;
 	for (auto i = 0u; i < n; ++i)
 		for (auto j = 0u; j < n; ++j)
 			cin >> cost[i][j];

 	cin >> in;
 	for (auto i = 0u; i < n; ++i)
 		if (in[i] == 'Y')
 			init |= (1 << i);

 	cin >> p;

 	int ans = solve(init, dp, p, n, cost);
 	cout << (ans == INFINITE ? -1 : ans);

 	return 0;
 }