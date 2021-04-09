#include <iostream>

int s[4] = { 0, 1, 2, 4 };
int recursive(int n) {
	if (n < 4)
		return s[n];

	return recursive(n - 1) + recursive(n - 2) + recursive(n - 3);
}

int main() {
	int t;
	std::cin >> t;
	long long solution[76]; // 75가 한계
	solution[1] = 1;
	solution[2] = 2; // 1+1, 2
	solution[3] = 4; // 1+1+1, 2+1, 1+2, 3
	// 4 -> 1+1+1+1, 2+1+1, 3+1, 2+2, 1+1+2, 1+3
	// 5 -> 1+1+1+1+1, 2+1+1+1, 3+1+1, 2+2+1, 1+1+2+1, 1+1+3, 2+3
	for (int i = 4; i <= 75; i++) {
		solution[i] = solution[i - 1] + solution[i - 2] + solution[i - 3];
	}

	for (int i = 0; i < t; i++) {
		int n;
		std::cin >> n;
		std::cout << solution[n] << std::endl;
		//std::cout << recursive(n) << std::endl;
	}
}