package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p2931 {
    private static class PipelineSolver {
        final int n, m;
        char [][] map;
        boolean[][] visit;
        int mr = -1, mc = -1;
        public PipelineSolver() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            map = new char[n+2][m+2];
            visit = new boolean[n+2][m+2];

            for (int i = 1; i < n+1; i++) {
                char [] arr = br.readLine().toCharArray();
                for (int j = 1; j < m+1; j++) {
                    map[i][j] = arr[j-1];
                    if (map[i][j] == 'M') {
                        mr = i;
                        mc = j;
                    }
                }
            }
        }
        private static final int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        private int getDirection(int cd, char ch) {
            int nd = -1;
            switch (ch) {
                case '|':
                    if (cd == 0 || cd == 1) nd = cd;
                    break;
                case '-':
                    if (cd == 2 || cd == 3) nd = cd;
                    break;
                case '+':
                    nd = cd;
                    break;
                case '1':
                    if (cd == 3) nd = 0;
                    if (cd == 1) nd = 2;
                    break;
                case '2':
                    if (cd == 3) nd = 1;
                    if (cd == 0) nd = 2;
                    break;
                case '3':
                    if (cd == 0) nd = 3;
                    if (cd == 2) nd = 1;
                    break;
                case '4':
                    if (cd == 2) nd = 0;
                    if (cd == 1) nd = 3;
            }
            return nd;
        }

        private void dfs(int r, int c, int d) {
            visit[r][c] = true;

            if (d == -1) {
                for (int i = 0; i < 4; ++i) {
                    int nr = r + directions[i][0];
                    int nc = c + directions[i][1];

                    if (map[nr][nc] != 'Z' && map[nr][nc] != '.') {
                        int nd = getDirection(i, map[nr][nc]);
                        if (nd != -1) {
                            dfs(nr, nc, nd);
                            return;
                        }
                    }
                }
            } else {
                int nr = r + directions[d][0];
                int nc = c + directions[d][1];

                if (map[nr][nc] == '.') {
                    hr = nr;
                    hc = nc;
                    return;
                }

                int nd = getDirection(d, map[nr][nc]);
                if (nd != -1) {
                    dfs(nr, nc, nd);
                }
            }
        }

        int hr, hc;
        public void solve() {
            dfs(mr, mc, -1);
            System.out.print(hr + " " + hc + " ");
            inspect(hr, hc);
        }

        private void inspect(int r, int c) {
            int cc = 0;
            boolean[] conn = new boolean[4];


            int nr = r + directions[0][0];
            int nc = c + directions[0][1];
            switch (map[nr][nc]) {
                case '|':
                case '+':
                case '2':
                case '3':
                    cc++;
                    conn[0] = true;
            }

            nr = r + directions[1][0];
            nc = c + directions[1][1];
            switch (map[nr][nc]) {
                case '|':
                case '+':
                case '1':
                case '4':
                    cc++;
                    conn[1] = true;
            }

            nr = r + directions[2][0];
            nc = c + directions[2][1];
            switch (map[nr][nc]) {
                case '-':
                case '+':
                case '3':
                case '4':
                    cc++;
                    conn[2] = true;
            }

            nr = r + directions[3][0];
            nc = c + directions[3][1];
            switch (map[nr][nc]) {
                case '-':
                case '+':
                case '1':
                case '2':
                    cc++;
                    conn[3] = true;
            }

            switch (cc) {
                case 2:
                    if (conn[0] && conn[1]) {
                        System.out.println('|');
                    } else if (conn[0] && conn[2]) {
                        System.out.println('1');
                    } else if (conn[0] && conn[3]) {
                        System.out.println('4');
                    } else if (conn[1] && conn[2]) {
                        System.out.println('2');
                    } else if (conn[1] && conn[3]) {
                        System.out.println('3');
                    } else if (conn[2] && conn[3]) {
                        System.out.println('-');
                    }
                    break;
                case 4:
                    System.out.println('+');
                    break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        PipelineSolver ps = new PipelineSolver();
        ps.solve();
    }
}
