#include <iostream>
#include <bitset>
class Binary {
	const bool XOR(const bool& a, const bool& b) const {
		return !(a == b);
	}
	const bool AND(const bool& a, const bool& b) const {
		return (a && b);
	}
	const bool OR(const bool& a, const bool& b) const {
		return (a || b);
	}
public:
	std::bitset<82> raw;

	Binary() {
	}
	Binary(const std::string& s) {
		for (auto j = 0u; j < s.length(); ++j) {
			raw <<= 1;
			if (s.at(j) == '1') {
				raw.set(0);
			}
		}
	}

	Binary(int v) {
		int idx = 0;
		while (v > 0) {
			if ((v & 1) != 0) {
				raw.set(idx);
			}
			v /= 2;
			idx++;
		}
	}

	Binary reverse() const {
		Binary res;

		for (int i = begin(), j = 0; i != -1; --i, ++j) {
			if (raw.test(i)) {
				res.raw.set(j);
			}
		}

		return res;
	}

	const int toInt() const {
		int res = 0;
		int bit = 0b1;
		size_t idx = 0;
		for (; idx != this->end(); ++idx) {
			if (raw.test(idx)) {
				res += bit;
			}

			bit <<= 1;
		}

		return res;
	}

	const size_t begin() const {
		size_t i = rbegin();
		for (; i > 0; --i) {
			if (raw.test(i)) {
				break;
			}
		}
		return i;
	}
	const size_t rbegin() const {
		return (raw.size() - 1);
	}
	const size_t end() const {
		return raw.size();
	}

	const Binary operator+(const Binary& T) const {
		Binary binary;
		bool s = false;
		bool c = false;
		bool a, b;
		size_t idx = 0;
		for (; idx != this->end(); ++idx) {
			a = this->raw.test(idx);
			b = T.raw.test(idx);

			s = XOR(XOR(a, b), c);
			c = OR(AND(XOR(a, b), c), AND(a, b));

			binary.raw.set(idx, s);
		}

		return binary;
	}

	friend std::ostream& operator<<(std::ostream& os, const Binary& b) {
		int i = b.rbegin();
		for (; i > 0; --i) {
			if (b.raw.test(i)) {
				break;
			}
		}
		for (; i >= 0; --i) {
			if (b.raw.test(i) == true) {
				os << 1;
			}
			else {
				os << 0;
			}
		}
		return os;
	}
};

namespace {
	static const Binary& toBinary(int v) {
		Binary *b = new Binary();
		Binary &res = *b;
		int idx = 0;
		while (v > 0) {
			if ((v & 1) != 0) {
				res.raw.set(idx);
			}
			v /= 2;
			idx++;
		}

		return res;
	}
}

int main() {
	//int t;
	using namespace std;
	//cin >> t;
	//for (int i = 0; i < t; ++i) {
	//string s1;
	//string s2;
	//cin >> s1 >> s2;

	//Binary b1(s1);
	//Binary b2(s2);

	//cout << b1 + b2 << "\n";

	/*cout << toBinary(0).toInt() << "\n";
	cout << toBinary(1).toInt() << "\n";
	cout << toBinary(2).toInt() << "\n";
	cout << toBinary(3).toInt() << "\n";
	cout << toBinary(4).toInt() << "\n";
	cout << toBinary(5).toInt() << "\n";*/
	int value;
	cin >> value;
	cout << Binary(value).reverse().toInt();
	//}
}