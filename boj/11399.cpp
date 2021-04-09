#include <iostream>
#include <vector>
#include <algorithm>

int main() {
	int n;
	std::cin >> n;

	int sum = 0;
	std::vector<int> p;
	p.reserve(n);

	for (int i = 0; i < n; i++) {
		int temp;
		std::cin >> temp;

		p.push_back(temp);
	}

	std::sort(p.begin(), p.end());

	sum += p[0];
	for (int i = 1; i < n; i++) {
		p[i] = p[i] + p[i - 1];
		sum += p[i];
	}

	std::cout << sum;

	return 0;
}