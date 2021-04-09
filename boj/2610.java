package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p2610 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()) + 1;
        int m = Integer.parseInt(br.readLine());
        int[][] dist = new int[101][101];
        int[] max = new int[101];
        for (int i = 1; i < n; i++) {
            dist[i][i] = 0;
            for (int j = i + 1; j < n; j++) {
                dist[i][j] = 0x3ffffff;
                dist[j][i] = 0x3ffffff;
            }
        }
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            dist[a][b] = 1;
            dist[b][a] = 1;
        }

        for (int k = 1; k < n; k++) {
            for (int i = 1; i < n; i++) {
                for (int j = 1; j < n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                if (dist[i][j] != 0x3ffffff && dist[i][j] > max[i]) {
                    max[i] = dist[i][j];
                }
            }
        }

        boolean[] committee = new boolean[101];
        int[] representative = new int[101];
        int cnt = 0;
        for (int i = 1; i < n; i++) {
            if (!committee[i]) {
                int t = i;
                for (int j = i; j < n; j++) {
                    if (dist[i][j] != 0x3ffffff) {
                        committee[j] = true;
                        if (max[t] > max[j]) {
                            t = j;
                        }
                    }
                }
                representative[cnt++] = t;
            }
        }

        Arrays.sort(representative, 0, cnt);

        StringBuilder sb = new StringBuilder();
        sb.append(cnt).append('\n');
        for (int i = 0; i < cnt; i++) {
            sb.append(representative[i]).append('\n');
        }
        System.out.print(sb);
    }
}