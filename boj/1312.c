#include <stdio.h>

int main(void) {
    int a, b, n;
    scanf("%d %d %d", &a, &b, &n);
    int t;
    while(n--) {
        a = a % b;
        a = a * 10;
        t = a / b;
    }
    printf("%d", t);
    
    return 0;
}