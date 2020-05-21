import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; ++tc) {
            Solver solver = new Solver();
            sb.append('#').append(tc).append(' ').append(solver.solve()).append('\n');
        }
        System.out.println(sb);
    }

    private static class Solver {
        static int n, w, h;
        static int[][] source = new int[15][12];
        static int[] order = new int[4];
        static int[] nonZeroCountSource = new int[12];
        static int nzc = 0;
        Simulator simulator = new Simulator();
        int[] nz;

        public Solver() throws Exception {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < w; j++) {
                    source[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            simulator.newSimulation();
            nz = new int[nzc];
            int idx = 0;
            for (int i = 0; i < w; i++) {
                if (nonZeroCountSource[i] != 0) {
                    nz[idx++] = i;
                }
            }
        }

        private void combinationR(int c) {
            if (c == n) {
                simulator.changeBoard();
                simulator.simulate(order);
            } else {
                for (int i : nz) {
                    order[c] = i;
                    combinationR(c + 1);
                }
            }
        }

        public int solve() {
            combinationR(0);
            return simulator.result;
        }

        private static class Simulator {
            int[][] board = new int[15][12];
            int[] nonZeroCount = new int[12];
            int result;
            int resultSource;

            public void newSimulation() {
                Arrays.fill(nonZeroCountSource, 0);
                for (int i = 0; i < h; i++) {
                    for (int j = 0; j < w; j++) {
                        if (source[i][j] != 0) {
                            nonZeroCountSource[j]++;
                        }
                    }
                }
                nzc = 0;
                for (int i = 0; i < w; i++) {
                    if (nonZeroCountSource[i] != 0)
                        nzc += 1;
                }

                System.arraycopy(nonZeroCountSource, 0, nonZeroCount, 0, w);
                resultSource = sum();
            }
            public void changeBoard() {
                for (int i = 0; i < h; i++) {
                    System.arraycopy(source[i], 0, board[i], 0, w);
                }
                System.arraycopy(nonZeroCountSource, 0, nonZeroCount, 0, w);
                result = resultSource;
            }

            private int findExplosionPoint(int x) {
                for (int i = 0; i < h; i++) {
                    if (board[i][x] != 0) {
                        return i;
                    }
                }
                return -1;
            }

            private boolean isIn(int r, int c) {
                return (r >= 0 && c >= 0 && r < h && c < w);
            }
            private static final int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
            private void explosion(int r, int c) {
                int count = board[r][c];
                nonZeroCount[c]--;
                board[r][c] = 0;

                for (int [] d : directions) {
                    int nr = r;
                    int nc = c;
                    for (int i = 0; i < count - 1; ++i) {
                        nr = nr + d[0];
                        nc = nc + d[1];

                        if (!isIn(nr, nc)) continue;
                        if (board[nr][nc] != 0) {
                            explosion(nr, nc);
                        }
                    }
                }
            }

            private void drop() {
                for (int j = 0; j < w; j++) {
                    int up = h - 1;
                    line:
                    while (true) {
                        while (board[up][j] != 0) {
                            up--;
                            if (up < 1) {
                                break line;
                            }
                        }
                        int ud = up - 1;
                        while (board[ud][j] == 0) {
                            ud--;
                            if (ud < 0) {
                                break line;
                            }
                        }
                        if (board[up][j] != board[ud][j]) {
                            int t = board[up][j];
                            board[up][j] = board[ud][j];
                            board[ud][j] = t;
                        }

                        up--;
                        if (up < 1) {
                            break;
                        }
                    }
                }
            }

            private int sum() {
                int res = 0;
                for (int i = 0; i < w; i++) {
                    res += nonZeroCount[i];
                }
                return res;
            }

            public void simulate(int[] order) {
                for (int i = 0; i < n; i++) {
                    int c = order[i];
                    if (nonZeroCount[c] != 0) {
                        int r = findExplosionPoint(c);
                        explosion(r, c);
                        drop();
                    }
                }

                resultSource = result = Math.min(result, sum());
            }
        }
    }
}