/**
 * https://www.acmicpc.net/board/view/47799
 * 질문게시판 테스트용 제출
 */

#include <stdio.h>
#include <queue>
#include <algorithm>
using namespace std;
int n;
struct h {
    int y;
    int x;
};
int map[25][25] = {0};
int visited[25][25] = {0};
int cnt;
int dc[4] = {0,0,1,-1};
int dr[4] = {1,-1,0,0};

void BFS(int starty, int startx, int cnt) {
  queue<h> q;
  h h1;
  h1.y = starty;
  h1.x = startx;
  q.push(h1);

  
  while(!q.empty()) {
      int nowy = q.front().y;
      int nowx = q.front().x;
      q.pop();
      for(int i = 0; i < 4; i++) {
          int nexty = nowy + dc[i];
          int nextx = nowx + dr[i];
          if(nexty < 0 || nexty >= n || nextx < 0 || nextx >= n) continue;
          if(map[nexty][nextx] == 0 || visited[nexty][nextx] != 0) continue;
          h h2 = {nexty,nextx};
          visited[nexty][nextx] = cnt;
          q.push(h2);
      }
  }
}
int main() {
    scanf("%d", &n);
    for(int i = 0; i < n; i++) {
        for(int j = 0; j < n; j++) {
            scanf("%1d", &map[i][j]);
        }
    }
    cnt = 0;
    for(int i = 0; i < n; i++) {
        for(int j = 0; j < n; j++) {
            if(map[i][j] == 1 && visited[i][j] == 0) {
                visited[i][j] = cnt+1;
                BFS(i,j,++cnt);
            }
        }
    }
    
    // for(int i = 0; i < n; i++) {
    //   for(int j = 0; j < n; j++) {
    //         printf("%d ", visited[i][j]);
    //     }
    //     printf("\n");
    // }
    
    int* ans = new int[cnt]();
    
    for(int i = 0; i < n; i++) {
      for(int j = 0; j < n; j++) {
            if(visited[i][j] > 0) ans[visited[i][j]-1]++;
        }
    }
    sort(ans, ans+cnt);
    
    printf("%d\n", cnt);
    for(int i = 0; i < cnt; i++)
        printf("%d\n", ans[i]);
}