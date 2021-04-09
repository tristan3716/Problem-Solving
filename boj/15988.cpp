#include <iostream>

int main() {
	int t;
	std::cin >> t;
	long long solution[1000005];
	solution[1] = 1;
	solution[2] = 2;
	solution[3] = 4;
	for (int i = 4; i <= 1000000; i++) {
		solution[i] = (solution[i - 1] + solution[i - 2] + solution[i - 3]) % 1000000009;
	}

	for (int i = 0; i < t; i++) {
		int n;
		std::cin >> n;
		std::cout << solution[n] << std::endl;
	}
}