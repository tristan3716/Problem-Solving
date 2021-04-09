#include <iostream>

using namespace std;
using ull = unsigned long long;

ull alist32[] = {2, 7, 61};
ull alist64[] = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37};

inline unsigned long long addmod64(ull x, ull y, ull mod) {
    x %= mod;
    y %= mod;
    return (x >= mod - y? x - (mod - y) : x + y);
}

inline ull mulmod64(ull x, ull y, ull mod) {
    x %= mod;
    y %= mod;
    ull r = 0;
    while (y) {
        if (y % 2 == 1)
            r = addmod64(r, x, mod);
        x = addmod64(x, x, mod);
        y /= 2;
    }
    return r;
}

inline ull powmod32(ull x, ull y, ull m) {
    x %= m;
    ull r = 1ULL;
    while (y) {
        if (y & 1)
            r = (r * x) % m;
        x = (x * x) % m;
        y >>= 1;
    }
    return r;
}
 
inline ull powmod64(ull x, ull y, ull mod) {
    x %= mod;
    ull r = 1ULL;
    while (y) {
        if (y % 2 == 1)
            r = mulmod64(r, x, mod);
        x = mulmod64(x, x, mod);
        y /= 2;
    }
    return r;
}

inline bool miller_rabin32(ull n, ull a) {
    if (n % a == 0)
        return false;
    int cnt = -1;
    ull d = n - 1;
    while (!(d&1)) {
        d >>= 1;
        cnt++;
    }
    ull p = powmod32(a, d, n);
    if (p == 1 || p == n - 1)
        return true;
    while (cnt--) {
        p = (p * p) % n;
        if (p == n - 1)
            return true;
    }
    return false;
}

inline bool miller_rabin64(ull n, ull a) {
    if (n % a == 0)
        return false;
    int cnt = -1;
    ull d = n - 1;
    while (!(d&1)) {
        d >>= 1;
        cnt++;
    }
    ull p = powmod64(a, d, n);
    if (p == 1 || p == n - 1)
        return true;
    while (cnt--) {
        p = mulmod64(p, p, n);
        if (p == n - 1)
            return true;
    }
    return false;
}

bool is_prime(ull n) {
    if (n <= 1)
        return false;
    if (n % 2 == 0 && n != 2)
        return false;
    if (n < 10000ULL)  {
       for (ull i = 2; i*i <= n; i++)
           if (n % i == 0)
               return false;
       return true;
    }
    else if (n < 4294967296ULL) {
       for (ull a : alist32)
           if (!miller_rabin32(n, a))
               return false;
       return true;
    }
    else {
        for (ull a : alist64)
            if (!miller_rabin64(n, a))
                return false;
        return true;
    }
}

int main(void) {
    std::ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    int t;
    cin >> t;
    int count = 0;
    unsigned int k;
    while (t--) {
        cin >> k;
        count += is_prime(2*k+1);
    }
    cout << count;
    return 0;
}
