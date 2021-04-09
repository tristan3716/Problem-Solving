import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	private static class Solver {
		int n, m;
		final char[][] map;
		int[][] idMap;
		private int[] size;

		public Solver() throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			map = new char[n+2][m+2];
			idMap = new int[n+2][m+2];
			for (int i = 0; i < n+2; i++) {
				map[i][0] = 'x';
				map[i][m+1] = 'x';
			}
			for (int i = 0; i < m+2; i++) {
				map[0][i] = 'x';
				map[n+1][i] = 'x';
			}
			for (int i = 1; i < n+1; i++) {
				char[] arr = br.readLine().toCharArray();
				System.arraycopy(arr, 0, map[i], 1, m);
			}
			size = new int[n * m + 1];
		}

		private static final int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

		int visitCount;
		private void dfs(int r, int c) {
			visitCount++;
			for (int[] d : directions) {
				int nr = r + d[0];
				int nc = c + d[1];

				if (idMap[nr][nc] == 0 && map[nr][nc] == '0') {
					idMap[nr][nc] = id;
					dfs(nr, nc);
				}
			}
		}

		int id = 1;

		private void labeling() {
			for (int i = 1; i < n+1; i++) {
				for (int j = 1; j < m+1; j++) {
					if (idMap[i][j] == 0) {
						idMap[i][j] = id;
						if (map[i][j] == '0') {
							visitCount = 0;
							dfs(i, j);
							size[id] = visitCount;
						}
						id++;
					}
				}
			}
		}

		public void spread() {
			StringBuilder sb = new StringBuilder();
			LinkedList<Integer> s = new LinkedList<>();
			for (int i = 1; i < n+1; i++) {
				for (int j = 1; j < m+1; j++) {
					int size = 0;
					if (map[i][j] != '0') {
						size = 1;
						s.clear();
						for (int[] d : directions) {
							int nr = i + d[0];
							int nc = j + d[1];

							if (map[nr][nc] == '0') {
								int id = idMap[nr][nc];
								if (!s.contains(id)) {
									s.add(id);
									size += this.size[id];
								}
							}
						}
					}
					sb.append(size % 10);
				}
				sb.append('\n');
			}
			System.out.print(sb);
		}

		public void solve() {
			labeling();
			spread();
		}
	}

	public static void main(String[] args) throws IOException {
		Solver s = new Solver();
		s.solve();
	}
}