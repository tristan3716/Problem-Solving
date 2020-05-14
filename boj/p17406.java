package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p17406 {
    private static class RotateSolver {
        final int n, m, k;
        int [][] map;
        int [][] commands;
        int [][] copied;

        public void solve() {
            makePermutation(commands, new int[k][3], new boolean[commands.length], commands.length, 0, commands.length);
        }

        private void rotate(int r, int c, int s, int [][] cp) {
            while (s > 0) {
                int temp = cp[r - s][c - s];
                for (int i = r - s; i < r + s; i++) { cp[i][c-s] = cp[i+1][c-s]; }
                for (int i = c - s; i < c + s; i++) { cp[r+s][i] = cp[r+s][i+1]; }
                for (int i = r + s; i > r - s; i--) { cp[i][c+s] = cp[i-1][c+s]; }
                for (int i = c + s; i > c - s + 1; i--) { cp[r-s][i] = cp[r-s][i-1]; }
                cp[r-s][c-s+1] = temp;
                s--;
            }
        }

        private int eval(int [][] cp) {
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                min = Math.min(min, Arrays.stream(cp[i]).sum());
            }

            return min;
        }

        private void print(int [][] arr) {
            System.out.println("state =================");
            for (int [] x : arr) {
                System.out.println(Arrays.toString(x));
            }
        }

        int min = Integer.MAX_VALUE;
        private void go(int [][] commands, int [][] cp) {
            for (int i = 0; i < k; i++) {
                rotate(commands[i][0], commands[i][1], commands[i][2], cp);
            }
            min = Math.min(min, eval(cp));
        }

        void makePermutation(final int[][] src, int[][] res, boolean[] visit, final int r, int current, final int max) {
            if (r == current) {
                for (int i = 0; i < n; i++) {
                    System.arraycopy(map[i], 0, copied[i], 0, m);
                }
                go(res, copied);
            } else {
                for (int i = 0; i < max; ++i) {
                    if (!visit[i]) {
                        visit[i] = true;
                        res[current] = src[i];
                        makePermutation(src, res, visit, r, current + 1, max);
                        visit[i] = false;
                    }
                }
            }
        }

        public RotateSolver() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            map = new int[n][m];
            copied = new int[n][m];
            commands = new int[k][3];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < m; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                commands[i][0] = Integer.parseInt(st.nextToken())-1;
                commands[i][1] = Integer.parseInt(st.nextToken())-1;
                commands[i][2] = Integer.parseInt(st.nextToken());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        RotateSolver rs = new RotateSolver();
        rs.solve();
        System.out.println(rs.min);
    }
}