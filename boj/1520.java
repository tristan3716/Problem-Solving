import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static class Solver {
		final int n, m;
		final int [][] map;
		int [][] dp;

		public Solver () throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			map = new int[n+2][m+2];
			dp = new int[n+2][m+2];
			for (int [] d : dp) {
				Arrays.fill(d, -1);
			}
			dp[n][m] = 1;

			final int WALL = 1 << 15;
			for (int i = 0; i < n+2; i++) {
				map[i][0] = WALL;
				map[i][m+1] = WALL;
			}
			for (int i = 0; i < m+2; i++) {
				map[0][i] = WALL;
				map[n+1][i] = WALL;
			}
			for (int i = 1; i < n+1; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 1; j < m+1; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
		}

		private static final int [][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		private int solve(int r, int c) {
			int sum = 0;
			for (int [] d : dir) {
				int nr = r + d[0];
				int nc = c + d[1];

				if (map[nr][nc] < map[r][c]) {
					if (dp[nr][nc] == -1) {
						sum += solve(nr, nc);
					} else {
						sum += dp[nr][nc];
					}
				}
			}

			return dp[r][c] = sum;
		}

		public int solve() {
			return solve(1, 1);
		}
	}
	public static void main(String[] args) throws IOException {
		Solver s = new Solver();
		System.out.println(s.solve());
	}
}