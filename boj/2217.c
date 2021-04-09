#include <stdio.h>
#include <stdlib.h>

int static compare(const void *a, const void *b) {
    return (*(int *)a < *(int *)b);
}

int main(void) {
    int rope_count;
    scanf("%d", &rope_count);
    int *arr;
    arr = (int *)malloc(sizeof(int) * rope_count);
    for (int i = 0; i < rope_count; ++i) {
        scanf("%d", &arr[i]);
    }

    qsort(arr, rope_count, sizeof(int), compare);

    int max = arr[0];

    for (int i = 1; i < rope_count; ++i) {
        int temp = (i + 1) * arr[i];
        if (max < temp) {
            max = temp;
        }
    }

    printf("%d", max);

    return 0;
}