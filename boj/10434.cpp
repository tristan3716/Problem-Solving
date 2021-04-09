#include <iostream>

bool is_prime(int n) {
	if (n <= 1)
		return false;
	if (n % 2 == 0 && n != 2)
		return false;
	if (n < 10000ULL) {
		for (int i = 2; i*i <= n; i++)
			if (n % i == 0)
				return false;
		return true;
	}
	return true;
}

int f(int x) {
	int res = 0;
	while (x > 0) {
		res += (x % 10) *  (x % 10);
		x /= 10;
	}
	return res;
}

int main(void) {
	int n;
	std::cin >> n;
	int tc = 0;
	for (; tc < n;) {
		std::cin >> tc;
		int i;
		std::cin >> i;
		bool flag = false;
		if (is_prime(i)) {
			int t = i;
			while (t != 4) {
				t = f(t);
				if (t == 1) {
					flag = true;
					break;
				}
			}
		}
		if (flag) {
			std::cout << tc << " " << i << " YES\n";
		}
		else {
			std::cout << tc << " " << i << " NO\n";	
		}
	}
}