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