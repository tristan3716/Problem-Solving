#include <iostream>
#include <set>
#include <vector>
#include <algorithm>
using namespace std;

using ll = long long;

#include <sys/mman.h>
#include <sys/stat.h>
class fio {
    size_t rsize;
    unsigned char* rbuf;
    int ridx;

public:
    fio() : ridx(0) {
        struct stat rstat;
        fstat(0, &rstat);
        rsize = rstat.st_size;
        rbuf = (unsigned char*)mmap(0, rsize, PROT_READ, MAP_FILE | MAP_PRIVATE, 0, 0);
    }

    inline bool isBlank() {
        return rbuf[ridx] == '\n' || rbuf[ridx] == ' ';
    }
    inline void consumeBlank() {
        while (isBlank()) ridx++;
    }

    inline int readInt() {
        int res = 0;
        bool flag = false;
        consumeBlank();
        if (rbuf[ridx] == '-') {
            flag = true;
            ridx++;
        }
        while (!isBlank() && ridx < rsize)
            res = 10 * res + rbuf[ridx++] - '0';
        return flag ? -res : res;
    }

    inline ll readLongLong() {
        ll res = 0;
        bool flag = false;
        consumeBlank();
        if (rbuf[ridx] == '-') {
            flag = true;
            ridx++;
        }
        while (!isBlank() && ridx < rsize)
            res = 10 * res + rbuf[ridx++] - '0';
        return flag ? -res : res;
    }

    inline char readChar() {
        consumeBlank();
        return rbuf[ridx++];
    }
};

int main() {
    ios_base::sync_with_stdio(false);
    cout.tie(0);
    //freopen("in", "r", stdin);
    fio io;
    int t = io.readInt();
    for (int i = 0; i < t; ++i) {
        int n = io.readInt();
        multiset<ll> dpq;
        
        for (int j = 0; j < n; ++j) {
            char op = io.readChar();
            ll val = io.readLongLong();
            if (op == 'I') {
                dpq.insert(val);
            }
            else if (!dpq.empty()) {
                if (val == 1LL) {
                    auto i = dpq.end();
                    --i;
                    dpq.erase(i);
                }
                else {
                    dpq.erase(dpq.begin());
                }
            }
        }
        if (dpq.empty()) {
            cout << "EMPTY\n";
        }
        else {
            auto i = dpq.end();
            --i;
            cout << *i << " ";
            cout << *dpq.begin() << "\n";
        }
    }
}
