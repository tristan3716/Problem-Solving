#include <iostream>
#include <string>
#include <algorithm>

int main() {
	using namespace std;
	string s;
	string s2;
	int t;
	int sum = 0;
	int hasZero = 0;
	std::cin >> s;
	s2 = s;
	while (!s.empty()) {
		t = static_cast<int>(s.back()) - '0';
		if (!t)
			hasZero = 1;
		sum += t;
		s.pop_back();
	}
	if (hasZero == 1) {
		if (sum % 3 == 0) {
			std::sort(s2.begin(), s2.end(), std::greater<int>());
			for (auto i : s2) {
				std::cout << i;
			}
			return 0;
		}
	}
	std::cout << -1;
	return 0;
}