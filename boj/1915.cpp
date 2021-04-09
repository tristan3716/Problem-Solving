#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
 
int main(){
    
    int n,m;
    cin>>n>>m;
    
    vector<vector<int>> dp(n+1,vector<int>(m+1,0));
    
    char c;
    for(int i=1;i<=n;i++){
        for(int j=1;j<=m;j++){
            cin>>c;
            dp[i][j]=c-48;
        }
    }
    
    int ans=0;
    for(int i=1;i<=n;i++){
        for(int j=1;j<=m;j++){
            if(dp[i][j]){
                dp[i][j]=min(dp[i-1][j-1],min(dp[i-1][j],dp[i][j-1]))+1;
                ans=max(ans,dp[i][j]);
            }
        }
    }
    
    cout<<ans*ans<<endl;
    return 0;
}