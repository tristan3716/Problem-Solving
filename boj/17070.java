import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static class Solver {
		final int n;
		int [][][] dp;
		final int [][] map;
		public Solver() throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			n = Integer.parseInt(br.readLine());
			
			dp = new int[n+2][n+2][3]; // 0:h, 1:v, 2:d
			dp[1][2][0] = 1;
			map = new int[n+2][n+2];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			st.nextToken(); st.nextToken();
			for (int j = 3; j < n + 1; j++) {
				map[1][j] = Integer.parseInt(st.nextToken());

				if (map[1][j] == 0)
					dp[1][j][0] = dp[1][j-1][0] + dp[1][j-1][2];
			}

			for (int i = 2; i < n+1; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 1; j < n+1; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 0)
						dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][2];
					if (map[i][j] == 0)
						dp[i][j][1] = dp[i-1][j][1] + dp[i-1][j][2];
					if (map[i][j] == 0 && map[i][j-1] == 0 && map[i-1][j] == 0)
						dp[i][j][2] = dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];

				}
			}

			System.out.println((dp[n][n][0] + dp[n][n][1] + dp[n][n][2]));
		}
	}

	public static void main(String[] args) throws IOException {
		Solver s = new Solver();
	}
}