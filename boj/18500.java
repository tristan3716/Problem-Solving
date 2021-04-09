import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Main {
	private static class MineralSolver {
		private static class Mineral {
			int r, c;
			boolean isGround;

			public Mineral(int r, int c) {
				this.r = r;
				this.c = c;
			}
		}

		final int n;
		final int m;
		final int k;

		char [][] map;
		int [] commands;

		public MineralSolver() throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			String [] nm = br.readLine().split(" ");
			n = Integer.parseInt(nm[0]);
			m = Integer.parseInt(nm[1]);

			map = new char[n+2][m+2];

			for (int i = 1; i < n+1; i++) {
				char[] str = br.readLine().toCharArray();
				System.arraycopy(str, 0, map[i], 1, m);
			}

			k = Integer.parseInt(br.readLine());
			commands = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).map(x -> x = n - x + 1).toArray();
		}
		ArrayList<Mineral> list;
		boolean isFloat;
		boolean [][] visit;
		private final static int [][] directions = {{-1, 0}, {0, 1}, {0, -1}};
		private final static int [] down = {1, 0};

		private void dfs(int r, int c) {
			Mineral x = new Mineral(r, c);
			list.add(x);
			if (r == n) {
				x.isGround = true;
				isFloat = false;
			}
			for (int [] d : directions) {
				int nr = r + d[0];
				int nc = c + d[1];

				if (!visit[nr][nc] && map[nr][nc] == 'x') {
					visit[nr][nc] = true;
					dfs(nr, nc);
				}
			}
			int nr = r + down[0];
			int nc = c + down[1];
			if (nr == n + 1) {
				x.isGround = true;
			} else {
				if (map[nr][nc] == '.') {
					x.isGround = true;
				} else if (!visit[nr][nc]) {
					visit[nr][nc] = true;
					dfs(nr, nc);
				}
			}
		}

		private void drop() {
			list = new ArrayList<>();
			visit = new boolean[n+2][m+2];
			for (int i = 1; i < n; ++i) {
				for (int j = 1; j < m+1; j++) {
					if (map[i][j] == 'x' && !visit[i][j]) {
						visit[i][j] = true;
						isFloat = true;
						list.clear();
						dfs(i, j);

						if (isFloat) {
							boolean isGround = false;
							while (!isGround) {
								for (Mineral x : list) {
									map[x.r][x.c] = '.';
								}
								for (Mineral x : list) {
									x.r++;
									if (x.isGround && x.r+1 == n+1) {
										isGround = true;
									} else if (x.isGround && map[x.r+1][x.c] == 'x'){
										isGround = true;
									}
								}
							}
							for (Mineral x : list) {
								map[x.r][x.c] = 'x';
							}
							return;
						}
					}
				}
			}
		}

		public void solve() {
			boolean isLeftTurn = true;
			for (int c : commands) {
				if (isLeftTurn) {
					for (int i = 1; i < m+1; ++i) {
						if (map[c][i] == 'x') {
							map[c][i] = '.';
							break;
						}
					}
				} else {
					for (int i = m; i >= 1; --i) {
						if (map[c][i] == 'x') {
							map[c][i] = '.';
							break;
						}
					}
				}
				isLeftTurn = !isLeftTurn;

				drop();
			}
		}
		public void print() {
			for (int i = 1; i < n+1; i++) {
				for (char y : map[i]) {
					if (y == '.' || y == 'x')
					System.out.print(y);
				}
				System.out.println();
			}
		}
	}

	public static void main(String[] args) throws IOException {
		MineralSolver ms = new MineralSolver();
		ms.solve();
		ms.print();
	}
}