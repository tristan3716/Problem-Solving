#include <iostream>
#include <bitset>
#include <string>

int main(void) {
	using std::cin;
	using std::cout; 
	std::ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	std::string op;
	int operand;
	std::bitset<21> set;
	size_t m;
	cin >> m;
	for (auto i = 0u; i < m; i++) {
		cin >> op;
		switch (op[0])
		{
		case 'a':
			if (op[1] == 'd') { // add
				cin >> operand;
				set.set(operand);
			}
			else { // all
				set.set();
			}
			break;
		case 'r': // remove
			cin >> operand;
			set.reset(operand);
			break;
		case 'c': // check
			cin >> operand;
			cout << (set.test(operand) ? "1" : "0") << "\n";
			break;
		case 'e': // empty
			set.reset();
			break;
		case 't': // toggle
			cin >> operand;
			set.flip(operand);
			break;
		default:
			break;
		}
	}
}