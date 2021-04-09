import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static int n;
	private static int m;
	private static int[][] map;
	private static ArrayList<dot> al;
	private static int min;
	private static int[][][] visit;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
		
		n= Integer.parseInt(stringTokenizer.nextToken());
		m= Integer.parseInt(stringTokenizer.nextToken());
		
		map = new int[n+1][m+1];
		visit = new int[2][n+1][m+1];
		for(int i=1;i<=n;i++) {
			String s = bufferedReader.readLine();
			for(int j=1;j<=m;j++) {
				int a =  Integer.parseInt(s.substring(j-1,j));
				map[i][j] =a;
				visit[0][i][j] = Integer.MAX_VALUE;
				visit[1][i][j] = Integer.MAX_VALUE;
			}
		}
		min = Integer.MAX_VALUE;
		
		bfs();
		
		System.out.println((min==Integer.MAX_VALUE?-1:min));
	}//main
	static void bfs() {
		Queue<dot> queue = new LinkedList<dot>();
		queue.offer(new dot(1, 1,0,1));
		while(!queue.isEmpty()) {
			dot now = queue.poll();
			int y = now.y; int x = now.x; int move = now.move; int cnt =now.cnt;
			if(move>min) {
				return;
			}
			if(y==n&&x==m) {
				min= Math.min(min, move+1);
				return;
			}
			if(visit[cnt][y][x]<=move) continue;
			visit[cnt][y][x] = move;
			
			for(int i=0;i<4;i++) {
				int ny = y+dy[i]; int nx = x+dx[i];
				//범위
				if(ny<=0||ny>n||nx<=0||nx>m) continue;
			

			    if(map[ny][nx]==1&&cnt==1&&visit[cnt-1][ny][nx]>move+1) { //방문하지 않았고  벽이고 아직 뚫을 수 있으면
					queue.offer(new dot(ny, nx, move+1, cnt-1));
				}
				else if (map[ny][nx] == 0) {
                    if (visit[cnt][ny][nx]>move+1) { //방문하지 않았고 일반 길이면
					    queue.offer(new dot(ny, nx,move+1,cnt));
                    }
				}

                
			}
			
		}
		
	}//bfs
	static class dot{
		int y,x,move,cnt;
		public dot(int y, int x, int move, int cnt) {
			super();
			this.y = y;
			this.x = x;
			this.move = move;
			this.cnt = cnt;
		}
	}//dot
}