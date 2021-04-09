#include <iostream>
#include <unordered_map>
using namespace std;
int main(){
ios_base::sync_with_stdio(false);
cin.tie(NULL);
cout.tie(NULL);
unordered_map<int, int> m;
int q, w;
for(cin>>q;q--;){cin>>w;m[w]++;}
for(cin>>q;q--;){cin>>w;cout<<m[w]<<" ";}
}