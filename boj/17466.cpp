#include <stdio.h>

int factmod(int n, int p) {
    unsigned long long res = 1;
    while (n > 1) {
        res = (res * ((n/p) % 2 ?  p-1 : 1)) % p;
        for (int i = 2; i <= n%p; ++i)
            res = (res * i) % p;
        n /= p;
    }
    return res % p;
}

int main() {
   int n, p;
   scanf("%d %d", &n, &p);
   printf("%d", factmod(n, p));
   return 0;
}
