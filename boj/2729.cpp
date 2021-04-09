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

int main() {
    int t;
    using namespace std;
    cin >> t;
    for (int i = 0; i < t; ++i) {
        string s1;
        string s2;
        cin >> s1 >> s2;

        Binary b1(s1);
        Binary b2(s2);

        cout << b1 + b2 << "\n";
    }
}