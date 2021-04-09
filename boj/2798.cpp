#include <iostream>
#include <vector>

int main() {
	int n, m;
	int t;
	std::vector<int> v;
	std::cin >> n >> m;
	for (int i = 0; i < n; i++) {
		std::cin >> t;
		v.push_back(t);
	}
	int sum, max = 0;
	for (int i = 0; i < n; i++) {
		for (int j = 1; j < n; j++) {
			if (i == j)
				break;
			for (int k = 2; k < n; k++) {
				if (j == k || i == k)
					break;
				sum = v[i] + v[j] + v[k];
				if (sum <= m && sum > max)
					max = sum;
			}
		}
	}

	std::cout<< max;
	return 0;
}