#include <algorithm>
#include <iostream>
#include <vector>
using namespace std;
using lint = long int;
using ull = unsigned long long;

inline ull gcd(ull a, ull b) {
    if (a == 0)
        return b;
    return gcd(b % a, a);
}
namespace miller_rabin {
    ull mul(ull x, ull y, ull mod) {
        return (__int128)x * y % mod;
    }
    ull ipow(ull x, ull y, ull p) {
        ull ret = 1, piv = x % p;
        while (y) {
            if (y & 1) ret = mul(ret, piv, p);
            piv = mul(piv, piv, p);
            y >>= 1;
        }
        return ret;
    }
    bool miller_rabin(ull x, ull a) {
        if (x % a == 0) return 0;
        ull d = x - 1;
        while (1) {
            ull tmp = ipow(a, d, x);
            if (d & 1)
                return (tmp != 1 && tmp != x - 1);
            else if (tmp == x - 1)
                return 0;
            d >>= 1;
        }
    }
    bool isprime(lint x) {
        for (auto &i : {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37}) {
            if (x == i) return 1;
            if (x > 40 && miller_rabin(x, i)) return 0;
        }
        if (x <= 40) return 0;
        return 1;
    }
}  // namespace miller_rabin

namespace pollard_rho {
    ull f(ull x, ull n, ull c) {
        return (c + miller_rabin::mul(x, x, n)) % n;
    }
    void rec(ull n, vector<ull> &v) {
        if (n == 1) return;
        if (n % 2 == 0) {
            v.push_back(2);
            rec(n / 2, v);
            return;
        }
        if (miller_rabin::isprime(n)) {
            v.push_back(n);
            return;
        }
        ull a, b, c;
        while (1) {
            a = rand() % (n - 2) + 2;
            b = a;
            c = rand() % 20 + 1;
            do {
                a = f(a, n, c);
                b = f(f(b, n, c), n, c);
            } while (gcd(abs(static_cast<long int> (a - b)), n) == 1);
            if (a != b) break;
        }
        lint x = gcd(abs(static_cast<long int> (a - b)), n);
        rec(x, v);
        rec(n / x, v);
    }
    vector<ull> factorize(ull n) {
        vector<ull> ret;
        rec(n, ret);
        sort(ret.begin(), ret.end());
        return ret;
    }
};  // namespace pollard_rho

template <typename T>
void print_vector(const vector<T> &v) {
    for (size_t i = 0u; i < v.size(); ++i) {
        cout << v[i];
        if (i + 1 < v.size()) {
            cout << "\n";
        }
    }
}
int main() {
    ull n;
    cin >> n;
    print_vector(pollard_rho::factorize(n));
}
