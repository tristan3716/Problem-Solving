#include <stdio.h>
#include <math.h>

#define MAX_SIZE 100000000

void get_sieve(int n, char sieve[MAX_SIZE]) {
    int sqrt_n = sqrt((double)n);

    for (int i = 2; i <= n; ++i) {
        sieve[i] = 1;
    }
    for (int i = 2; i <= sqrt_n; ++i) {
        if (sieve[i] == 1) {
            for (int j = i * i; j <= n; j += i) {
                sieve[j] = 0;
            }
        }
    }
}

void prime_sum(int m, int n, char sieve[MAX_SIZE]) {
    unsigned long long sum = 0LL;
    int min = -1;
    for (int i = m; i <= n; ++i) {
        if (sieve[i]){
            if (min == -1){
                min = i;
            }
            sum += i;
        }
    }
    if (sum != 0) {
        printf("%llu\n", sum);
    }
    printf("%d", min);
}

int main(void) {
    int m;
    int n;
    scanf("%d %d", &m, &n);

    static char sieve[MAX_SIZE];
    get_sieve(n, sieve);

    prime_sum(m, n, sieve);

    return 0;
}
