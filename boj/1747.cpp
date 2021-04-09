#include <iostream>
#include <cmath>

void eratos(int size, bool *era[])
{
	*era = (bool *)malloc(sizeof(bool) * 1003002);

	for (int i = 2; i <= size; i++)
		(*era)[i] = true;
	(*era)[1] = false;

	for (int i = 2; i * i <= size; i++)
	{
		if ((*era)[i])
			for (int j = i * 2; j <= size; j += i)
				(*era)[j] = false;
	}
}

bool is_pen(int n)
{
	int length = (int)log10(n) + 1;
	int *v = (int *)malloc(sizeof(int)*length);
	for (int i = length - 1; i >= 0; i--) {
		v[i] = n % 10;
		n /= 10;
	}
	for (int i = 0, j = length - 1; i <= j; i++, j--) {
		if (v[i] != v[j])
			return false;
	}
	return true;
}

bool is_prime(int n, bool *era)
{
	return era[n];
}

int a1747(int n)
{
	bool *era = NULL;
	eratos(1003002, &era);
	for (int i = n; ; i++) {
		if (is_pen(i) && is_prime(i, era))
			return i;
	}
	return 0;
}

int main() {
	int n;
	std::cin >> n;
	std::cout<<a1747(n);
	return 0;
}