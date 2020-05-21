import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    private static class Solver {
        final int n;
        int [] raw;
        int [] color;
        final int [][] adj;
        boolean [] visit;

        public Solver() throws IOException {
            n = Integer.parseInt(br.readLine());
            raw = new int[n];
            color = new int[n];
            adj = new int[n][n];
            visit = new boolean[n];

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < n; i++) {
                raw[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < n; j++) {
                    adj[i][j] = Integer.parseInt(st.nextToken());
                }
            }
        }

        private int diff(int [] x, int [] y) {
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (x[i] != y[i]) count++;
            }
            return count;
        }

        boolean imp;
        public void dfs(int cur) {
            if (imp) { return; }

            for (int i = 0; i < n; ++i) {
                if (adj[cur][i] == 1) {
                    if (color[cur] == color[i]) {
                        imp = true;
                    }
                    if (imp) { return; }
                    if (!visit[i]) {
                        visit[i] = true;
                        dfs(i);
                    }
                }
            }
        }
        int min = Integer.MAX_VALUE;
        public void solve(int cur) {
            if (cur == n) {
                imp = false;
                Arrays.fill(visit, false);
                int d = diff(color, raw);
                if (d >= min) { return; }
                for (int i = 0; i < n; i++) { // 분리된 그래프일 경우.
                    dfs(i);
                }
                if (!imp) {
                    min = d;
                }
            } else {
                for (int i = 1; i <= 4; i++) {
                    color[cur] = i;
                    solve(cur + 1);
                }
            }
        }

        public void solve() {
            solve(0);
        }
    }

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; ++tc) {
            sb.append('#').append(tc).append(' ');
            Solver s = new Solver();
            s.solve();
            sb.append(s.min).append('\n');
        }
        System.out.println(sb);
    }
}