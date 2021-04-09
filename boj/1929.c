#pragma warning (disable:4996)
#include <stdio.h>
#define true 0      //소수
#define false 1      //소수가 아닌수
#define MAX 1000000
int isPrime[MAX];
int main()
{
   int M, N;
   isPrime[0] = isPrime[1] = false;      //0과 1은 소수가 아니다
   scanf("%d %d", &M, &N);
   for (int i = 2; i <= MAX; i++)      //2부터 시작해서 특정한 숫자의 배수들은 소수가 아니다
   {
      if (isPrime[i] == false) continue;      //이미 소수가 아닌 걸로 판정된 숫자들은 그냥 지나가고
      for (int j = i + i; j <= MAX; j += i) {      //판정되지 않았다면 그의 배수들을 소수가 아닌 숫자로 판정한다
         isPrime[j] = false;
      }
   }
   
   for (int i = M; i < N + 1; i++) {               //판정되지 않은 숫자들(소수들)을 모두 출력한다
      if (isPrime[i] == true) printf("%d\n", i);
   }
   return 0;
}