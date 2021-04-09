#include <stdio.h>
#include <math.h>

#define MAX_SIZE 100000000

void get_sieve(int n, char sieve[MAX_SIZE]) {
    int sqrt_n = sqrt((double)n);
    sieve[0] = 0;
    sieve[1] = 0;
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

int count_prime(int n, char sieve[MAX_SIZE], int numbers[]) {
    int count = 0;
    for (int i = 0; i < n; ++i) {
        if (sieve[numbers[i]])
            count++;
    }
    return count;
}

int main(void) {
    int n;
    scanf("%d", &n);
    
    int numbers[1005];
    int max= 0;
    for (int i = 0; i < n; ++i) {
        scanf("%d", &numbers[i]);
        if (max < numbers[i])
            max = numbers[i];
    }

    static char sieve[MAX_SIZE];
    get_sieve(max, sieve);

    printf("%d", count_prime(n, sieve, numbers));

    return 0;
}
