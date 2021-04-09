import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int prim(final int n, int[][] weight) throws RuntimeException {
        final int INF = Integer.MAX_VALUE / 3;
        boolean[] selected = new boolean[n];
        int cost = 0;
        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[0] = 0;
        for (int i = 0; i < n; i++) {
            int now = -1;
            int minDist = INF;
            for (int j = 0; j < n; j++) {
                if (!selected[j] && minDist > dist[j]) {
                    minDist = dist[j];
                    now = j;
                }
            }
            if (now < 0) {
                throw new RuntimeException();
            }
            
            cost += minDist;
            selected[now] = true;
            for (int j = 0; j < n; j++) {
                int w = weight[now][j];
                dist[j] = Math.min(dist[j], w);
            }
        }
        return cost;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] p = new int[n + 1][n + 1];
        for (int i = 0; i < n; i++) {
            p[n][i] = p[i][n] = Integer.parseInt(br.readLine());
        }
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                p[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(prim(n + 1, p));
    }
}
