package p11404;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p11404 {
    static final int INF = 0x3ffffff;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()) + 1;
        int m = Integer.parseInt(br.readLine());
        int[][] dist = new int[101][101];
        int[] max = new int[101];
        for (int i = 1; i < n; i++) {
            dist[i][i] = 0;
            for (int j = i + 1; j < n; j++) {
                dist[i][j] = INF;
                dist[j][i] = INF;
            }
        }
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            dist[a][b] = Math.min(dist[a][b], c);
        }

        for (int k = 1; k < n; k++) {
            for (int i = 1; i < n; i++) {
                for (int j = 1; j < n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                if (dist[i][j] == INF) {
                    sb.append(0).append(' ');
                } else {
                    sb.append(dist[i][j]).append(' ');
                }
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
}
