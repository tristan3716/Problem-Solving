import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static class BuildingEscape {
		private static class Pos {
			int l, r, c;
			int time;

			public Pos(int l, int r, int c, int time) {
				this.l = l;
				this.r = r;
				this.c = c;
				this.time = time;
			}
		}
		Pos start;
		final int L, R, C;
		char [][][] map;
		public BuildingEscape(int l, int r, int c) throws IOException {
			L = l;
			R = r;
			C = c;
			map = new char[L][R][C];

			for (int k = 0; k < L; k++) {
				for (int i = 0; i < R; i++) {
					char [] arr = br.readLine().toCharArray();
					System.arraycopy(arr, 0, map[k][i], 0, C);
					for (int j = 0; j < C; j++) {
						if (map[k][i][j] == 'S') {
							map[k][i][j] = '#';
							start = new Pos(k, i, j, 0);
						}
					}
				}
				br.readLine();
			}
		}

		private boolean isIn(int l, int r, int c) {
			return (l >= 0 && l < L && r >= 0 && r < R && c >=0 && c < C);
		}
		private final int [][] directions = {{1, 0, 0}, {0, -1, 0}, {0, 1, 0}, {0, 0, 1}, {0, 0, -1}, {-1, 0, 0}};
		public int solve() {
			Queue<Pos> q = new LinkedList<>();
			q.offer(start);
			while (!q.isEmpty()) {
				Pos c = q.poll();
				assert c != null;

				for (int [] d : directions) {
					int nl = c.l + d[0];
					int nr = c.r + d[1];
					int nc = c.c + d[2];

					if (isIn(nl, nr, nc) && map[nl][nr][nc] != '#') {
						if (map[nl][nr][nc] == 'E') {
							return c.time + 1;
						}
						map[nl][nr][nc] = '#';
						q.offer(new Pos(nl, nr, nc, c.time + 1));
					}
				}
			}

			return -1;
		}
	}
	public static void main(String[] args) throws IOException {
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int L = Integer.parseInt(st.nextToken());
			if (L == 0) { break; }
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());

			BuildingEscape be = new BuildingEscape(L, R, C);
			int res = be.solve();

			if (res == -1) {
				System.out.println("Trapped!");
			} else {
				System.out.printf("Escaped in %d minute(s).\n", res);
			}
		}
	}
}