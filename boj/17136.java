import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static class Solver {
		private static class Pos {
			int r, c;

			public Pos(int r, int c) {
				this.r = r;
				this.c = c;
			}
		}
		int cnt;
		int [][] map;

		public Solver () throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			map = new int[10][10];
			for (int i = 0; i < 10; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < 10; j++) {
					int t = Integer.parseInt(st.nextToken());
					map[i][j] = (t == 0 ? 0 : 1);
					if (map[i][j] == 1) {
						cnt++;
					}
				}
			}
		}

		private boolean isPossible(int r, int c, int size) {
			for (int i = r; i < r + size; i++) {
				for (int j = c; j < c + size; j++) {
					if (map[i][j] == 0)
						return false;
				}
			}
			return true;
		}
		private void place(int r, int c, int size) {
			for (int i = r; i < r + size; i++) {
				for (int j = c; j < c + size; j++) {
					map[i][j] = 0;
				}
			}
		}

		private void rollback(int r, int c, int size) {
			for (int i = r; i < r + size; i++) {
				for (int j = c; j < c + size; j++) {
					map[i][j] = 1;
				}
			}
		}

		private int[] av = {0, 5, 5, 5, 5, 5};
		private int min = Integer.MAX_VALUE;
		private void solve(int r, int total, int left) {
			if (min <= total) { return; }
			if (left == 0) {
				min = Math.min(min, total);
				return;
			}

			for (int i = r; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					if (map[i][j] == 1) {
						for (int k = 5; k > 0; k--) {
							if ((i + k) <= 10 && (j + k) <= 10 && av[k] > 0) {
								if (isPossible(i, j, k)) {
									place(i, j, k);
									av[k]--;
									solve(i, total + 1, left - (k *k ));
									av[k]++;
									rollback(i, j, k);
								}
							}
						}
                        return;
					}
				}
			}
		}

		public void solve() {
			solve(0, 0, cnt);

			if (min == Integer.MAX_VALUE) {
				System.out.println(-1);
			} else {
				System.out.println(min);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		Solver s = new Solver();
		s.solve();
	}
}