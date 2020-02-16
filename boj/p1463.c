/**
 * @title 1로 만들기 S3
 * @see https://www.acmicpc.net/problem/1463
 * @since 2019.07.15
 * @category dp
 * @complexity O(3n) -> 4ms
 * @description
 *      갑분숏... 이때의 나 무슨 생각을 하고 있었을까
 *      기본적인 1차원 dp 연습문제. 1, 2, 3 각각의 연산에 대한 결과의 최솟값을 memoization
 */

#define m(x,y) (x)<(y)?x:y
#define f(x,y) for(x=2;x<=y;x++)
s[1000001],x,c,j;
main(i){
    scanf("%d",&x);
    f(i,x){
        s[i]=s[i-1]+1;
        f(j,3)
            if(!(i%j))
                s[i]=m(s[i],s[i/j]+1);
    }
    printf("%d",s[x]);
}