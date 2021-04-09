import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		final int n = Integer.parseInt(st.nextToken());
		final int m = Integer.parseInt(st.nextToken());
		final int[][] map = new int[n + 2][m + 2];
		final int[][][] dp = new int[n + 2][m + 2][2];

		for (int i = 1; i < n + 1; i++) {
			char[] t = br.readLine().toCharArray();
			for (int j = 0; j < m; j++) {
				if (t[j] == '1') {
					map[i][j+1] = 1;
				}
			}
		}

		int res = 0;
		pre:
		for (int i = 1; i < n+1; i++) {
			for (int j = 1; j < m+1; j++) {
				if (map[i][j] == 1) {
					res = 1;
					break pre;
				}
			}
		}
		for (int i = 1; i < n+1; i++) {
			for (int j = 1; j < m+1; j++) {
				if (map[i][j] == 1) {
					dp[i][j][0] = dp[i-1][j-1][0] + 1;
					dp[i][j][1] = dp[i-1][j+1][1] + 1;
				}
			}
		}

		for (int i = n; i > 0; i--) {
			for (int j = 1; j < m+1; j++) {
				if (dp[i][j][1] > res) {
					int l = dp[i][j][0];
					int r = dp[i][j][1];
					int min = Math.min(l, r) - 1;
					if (min < res) continue;
					for (int k = min; k > 0 && k >= res; k--) {
						if (dp[i-k][j-k][1] > k && dp[i-k][j+k][0] > k) {
							res = Math.max(res, k+1);
							break;
						}
					}
				}
			}
		}

		System.out.print(res);
	}
}