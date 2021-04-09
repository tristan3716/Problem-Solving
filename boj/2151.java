import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		Solver s = new Solver();
		s.solve();
	}
}

class Solver {
	private static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	final int m, n;
	int[][] map;
	int[][][] dist;
	Queue<Pos> q;

	public Solver() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = m = Integer.parseInt(st.nextToken());

		q = new LinkedList<>();
		map = new int[n + 2][m + 2];
		dist = new int[n + 2][m + 2][2];
		for (int [][] dis : dist) {
			for (int [] di : dis) {
				Arrays.fill(di, Integer.MAX_VALUE);
			}
		}
		for (int i = 0; i < n + 2; i++) {
			map[i][0] = -1;
			map[i][m + 1] = -1;
		}
		for (int i = 0; i < m + 2; i++) {
			map[0][i] = -1;
			map[n + 1][i] = -1;
		}

		for (int i = 1; i < n + 1; i++) {
			char[] arr = br.readLine().toCharArray();
			for (int j = 1; j < m + 1; j++) {
				switch (arr[j - 1]) {
					case '*':
						map[i][j] = -1;
						break;
					case '!':
						map[i][j] = -2;
						break;
					case '#':
						q.offer(new Pos(i, j));
				}
			}
		}
	}

	private static final int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	void dfs(int r, int c, int d) {
		int nr = r + directions[d][0];
		int nc = c + directions[d][1];

		if (dist[nr][nc][d&1] == Integer.MAX_VALUE) {
			dist[nr][nc][d&1] = mirror;
			if (map[nr][nc] == -2) {
				q.offer(new Pos(nr, nc));
				dfs(nr, nc, d);
			} else if (map[nr][nc] == 0) {
				dfs(nr, nc, d);
			}
		}
	}

	private int mirror = 0;

	public void solve() {
		Pos start = q.poll();
		Pos end = q.poll();
		dist[start.r][start.c][1] = mirror + 1;
		dist[start.r][start.c][0] = mirror + 1;
		q.offer(start);

		while (!q.isEmpty()) {
			mirror++;
			int qs = q.size();
			for (int i = 0; i < qs; i++) {
				Pos c = q.poll();

				for (int d = 0; d < 4; d++) {
					dfs(c.r, c.c, d);
				}
			}
		}

		System.out.println((Math.min(dist[end.r][end.c][0], dist[end.r][end.c][1]) - 1));
	}
}