#include <iostream>
#include <math.h>
int main(){
    long long m;
    std::cin >> m;
    std::cout << m - (int)sqrt(m) - (int)sqrt(m / 2);
}
