#include <iostream>
#include <string>

int main() {
	int a;
	std::string b;
	std::cin >> a >> b;
	std::cout << a * ((b[2])-'0') << std::endl;
	std::cout << a * ((b[1]) - '0') << std::endl;
	std::cout << a * ((b[0]) - '0') << std::endl;
	std::cout << a * std::stoi(b) << std::endl;
	return 0;
}