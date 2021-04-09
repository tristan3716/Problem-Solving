#include <iostream>

int cycle(int n, int a1, int a2, int s1, int s2)
{
	if (a1 == s1 && a2 == s2) {
		return n;
	}
	return cycle(n + 1, a2, (a1 + a2) % 10, s1, s2);
}

int a1110(int n)
{
	int a1 = n / 10;
	int a2 = n % 10;
	int length = cycle(1, a2, (a1 + a2) % 10, n / 10, n % 10);
	return length;
}

int main() {
	int n;
	std::cin >> n;
	std::cout << a1110(n) << std::endl;

	return 0;
}