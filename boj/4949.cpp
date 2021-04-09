#include <iostream>
#include <string>
#include <stack>

bool IsPair(const char cc, const char co) {
	switch (cc) {
	case ')':
		return co == '(' ? true : false;
	case ']':
		return co == '[' ? true : false;
	}
	return false;
}

bool solution(std::string in) {
	std::stack<char> ps;
	for (char c : in) {
		switch (c) {
		case '(':
		case '[':
			ps.push(c);
			break;
		case ')':
		case ']':
			if (ps.empty() || !IsPair(c, ps.top()))
				return false;
			ps.pop();
			break;
		}
	}
	if (ps.empty())
		return true;
	else
		return false;
}

int main() {
	while (1) {
		std::string in;
		getline(std::cin, in);
		if (in[0] == '.')
			break;
		if (solution(in))
			std::cout << "yes" << std::endl;
		else
			std::cout << "no" << std::endl;
	}
	return 0;
}