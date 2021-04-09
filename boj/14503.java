package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p14503 {
    private static class Solver {
        final static int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // S E N W
        int count = 1;
        final int n;
        final int m;
        int r;
        int c;
        int d;
        int [][] arr;

        public Solver() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String [] str = br.readLine().split(" ");
            n = Integer.parseInt(str[0]);
            m = Integer.parseInt(str[1]);

            str = br.readLine().split(" ");
            r = Integer.parseInt(str[0]);
            c = Integer.parseInt(str[1]);
            d = Integer.parseInt(str[2]);

            arr = new int[n][m];
            for (int i = 0; i < n; i++) {
                String [] t = br.readLine().split(" ");
                for (int j = 0; j < m; j++) {
                    arr[i][j] = Integer.parseInt(t[j]);
                }
            }
        }

        int solve() {
            next:
            while (true) {
                arr[r][c] = 2;

                for (int i = 0; i < 4; ++i) {
                    d = (d + 3) % 4;
                    int nr = r + directions[d][0];
                    int nc = c + directions[d][1];

                    if (arr[nr][nc] == 0) {
                        arr[nr][nc] = 2;
                        count++;
                        r = nr;
                        c = nc;
                        continue next;
                    }
                }

                int nr = r - directions[d][0];
                int nc = c - directions[d][1];
                if (arr[nr][nc] != 1) {
                    r = nr;
                    c = nc;
                } else {
                    break;
                }
            }
            return count;
        }
    }

    public static void main(String[] args) throws IOException {
        Solver s = new Solver();
        System.out.println(s.solve());
    }
}
