import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		int n, m;
		int [][] map;
		int maxSize = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n+1][m+1];
		for (int i = 1; i < n+1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j < m+1; j++) {
				int t = Integer.parseInt(st.nextToken());
				if (t == 0) {
					map[i][j] = Math.min(Math.min(map[i-1][j], map[i][j-1]), map[i-1][j-1]) + 1;
					maxSize = Math.max(map[i][j], maxSize);
				}
			}
		}
		System.out.println(maxSize);
	}
}
