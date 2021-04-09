#include <iostream>

bool isHansu(int n)
{
	int last = n % 10;
	n /= 10;
	int before = n % 10;
	int diff = before - last;

	while (n >= 10) {
		last = n % 10;
		n /= 10;
		before = n % 10;
		if ((before - last) != diff)
			return false;
	}

	return true;
}

int a1065(int n)
{
	int count = 0;
	if (n < 100)
		return n;
	else
		count = 99;

	for (int i = 100; i <= n; i++) {
		if (isHansu(i)) {
			count += 1;
		}
	}

	return count;
}


int main() {
	int n;
	std::cin >> n;

	std::cout << a1065(n) << std::endl;

	return 0;
}