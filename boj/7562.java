import java.util.*;



public class Main {
	static class Pos {
		int r, c;
		int k;
		public Pos(int r, int c, int k){
			this.r = r;
			this.c = c;
			this.k = k;
		}
	}

	static boolean isIn(int r, int c, int n, int m){
		return (r >= 0 && c >= 0 && r < n && c < m);
	}
	static int[][] dir = {{-2,-1},{-1,-2},{-2,1},{-1,2},{1,-2},{2,-1},{2,1},{1,2}};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while (t-- != 0){
			int n = sc.nextInt();
			int sr = sc.nextInt();
			int s1c = sc.nextInt();
			int tr = sc.nextInt();
			int tc = sc.nextInt();
			Queue<Pos> q = new LinkedList<>();
			boolean[][] visit = new boolean[n][n];
			q.offer(new Pos(sr, s1c, 0));
			while (!q.isEmpty()){
				Pos c = q.poll();
				if (c.r == tr && c.c == tc){
					System.out.println(c.k);break;
				}
				if (visit[c.r][c.c]) continue;
				visit[c.r][c.c] = true;
				for (int[]x:dir){
					int nr = c.r+x[0];
					int nc = c.c+x[1];
					if(isIn(nr,nc,n,n) && !visit[nr][nc]){
						q.offer(new Pos(nr, nc, c.k+1));
					}
				}
			}
		}
	}
}