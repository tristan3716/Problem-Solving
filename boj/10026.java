import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static class Solver {
		final int n;
		char [][] arr;
		char [][] arr2;
		public Solver () throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			n = Integer.parseInt(br.readLine());
			arr = new char[n+2][n+2];
			arr2 = new char[n+2][n+2];
			for (int i = 1; i < n+1; i++) {
				char [] str = br.readLine().toCharArray();
				for (int j = 1; j < n + 1; j++) {
					switch (str[j-1]) {
						case 'B':
							arr2[i][j] = arr[i][j] = 'B';
							break;
						case 'R':
							arr[i][j] = 'R';
							arr2[i][j] = 'G';
							break;
						case 'G':
							arr[i][j] = arr2[i][j] = 'G';
							break;
					}
				}
			}
		}

		private final static int [][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
		private void dfs(int r, int c) {
			for (int [] d : directions) {
				int nr = r + d[0];
				int nc = c + d[1];
				if (currentArr[nr][nc] == current) {
					currentArr[nr][nc] = '.';
					dfs(nr, nc);
				}
			}
		}

		char current;
		char [][] currentArr;
		public int count(char [][] v) {
			currentArr = v;
			int count = 0;
			for (int i = 1; i < n + 1; i++) {
				for (int j = 1; j < n + 1; j++) {
					if (v[i][j] != '.') {
						current = v[i][j];
						dfs(i, j);
						count++;
					}
				}
			}
			return count;
		}
		public void solve() {
			int rgb = count(arr);
			int gb = count(arr2);
			System.out.println(rgb + " " + gb);
		}
	}
	public static void main(String[] args) throws IOException {
		Solver s = new Solver();
		s.solve();
	}
}