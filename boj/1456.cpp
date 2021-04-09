#include <iostream>
#include <cmath>

using std::cout;
using std::cin;

int seive[10003005];

using ll = long long;

#define NOT_PRIME 1
#define PRIME 0

int main()
{
	ll a, b;
	cin >> a >> b;
	//a = 1LL;
	//b = 100000000000000LL;

	seive[0] = NOT_PRIME;
	seive[1] = NOT_PRIME;
	for (ll i = 2; i * i <= 10000005; i++)
	{
		if (seive[i] == PRIME) {
			for (ll j = i * 2; j <= 10000005; j += i) {
				seive[j] = NOT_PRIME;
			}
		}
	}
	ll cnt = 0;
	ll pcnt = 0;
	for (auto i = 1LL;
		i <= 10000005;
		++i)
	{
		if (seive[i] == PRIME) {
			ll q = i;
			ll t = i;
			do {
				if (q > b/t) {
					break;
				}
				q *= t;
				if (q >= a) {
					cnt++;
				}
			} while (1);
		}
	}
	cout << cnt;
	return 0;
}