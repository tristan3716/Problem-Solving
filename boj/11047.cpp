#include <stdio.h>

int main(void){
   int n;
   int k;
   int coin = 0;
   int arr[10];

   scanf("%d %d", &n, &k);

   for (int i = 0; i < n; i++) {
      scanf("%d", &arr[i]);
   }
   
   for (int i = n - 1; i >= 0; i--) {
      if (k >= arr[i]) {
         coin += k / arr[i];
         k = k % arr[i];
      }
   }
   printf("%d", coin);
}