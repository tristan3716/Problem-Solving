package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class p16985 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static class BuildingEscape {
        private static class Pos {
            int l, r, c;

            public Pos(int l, int r, int c) {
                this.l = l;
                this.r = r;
                this.c = c;
            }
        }

        final int L, R, C;
        int[][][] map;

        public BuildingEscape() throws IOException {
            L = 5;
            R = 5;
            C = 5;
            map = new int[L][R][C];

            for (int k = 0; k < L; k++) {
                for (int i = 0; i < R; i++) {
                    String[] arr = br.readLine().split(" ");
                    for (int j = 0; j < C; j++) {
                        map[k][i][j] = -(arr[j].charAt(0) - '0');
                    }
                }
            }
        }

        private boolean isIn(int l, int r, int c) {
            return (l >= 0 && l < L && r >= 0 && r < R && c >= 0 && c < C);
        }

        private final int[][] directions = {{1, 0, 0}, {0, -1, 0}, {0, 1, 0}, {0, 0, 1}, {0, 0, -1}, {-1, 0, 0}};

        public int bfs(int [] order) {
            int[][][] copied = new int[L][R][C];
            int l = 0;
            for (int idx : order) {
                for (int i = 0; i < 5; i++) {
                    System.arraycopy(map[idx][i], 0, copied[l][i], 0, 5);
                }
                l++;
            }

            Queue<Pos> q = new LinkedList<>();

            if (copied[0][0][0] != -1)
                return Integer.MAX_VALUE;

            q.offer(new Pos(0, 0, 0));
            copied[0][0][0] = 1;
            while (!q.isEmpty()) {
                Pos c = q.poll();

                for (int[] d : directions) {
                    int nl = c.l + d[0];
                    int nr = c.r + d[1];
                    int nc = c.c + d[2];

                    if (isIn(nl, nr, nc) && copied[nl][nr][nc] == -1) {
                        if (nl == 4 && nr == 4 && nc == 4) {
                            return copied[c.l][c.r][c.c];
                        }
                        copied[nl][nr][nc] = copied[c.l][c.r][c.c] + 1;
                        q.offer(new Pos(nl, nr, nc));
                    }
                }
            }


            return Integer.MAX_VALUE;
        }

        private void rotate(final int idx) {
            int [][] temp = new int[5][5];
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    temp[i][j] = map[idx][4-j][i];
                }
            }
            for (int i = 0; i < 5; i++) {
                System.arraycopy(temp[i], 0, map[idx][i], 0, 5);
            }
        }

        private void ordering(int cur, int [] res, boolean [] used) {
            if (cur == 5) {
                min = Math.min(min, bfs(res));
            } else {
                for (int i = 0; i < 5; i++) {
                    if (!used[i]) {
                        used[i] = true;
                        res[cur] = i;
                        ordering(cur + 1, res, used);
                        used[i] = false;
                    }
                }
            }
        }

        int min = Integer.MAX_VALUE;
        private void solve(int cur) {
            if (cur == 5) {
                ordering(0, new int[5], new boolean[5]);
            } else {
                solve(cur+1);
                for (int i = 0; i < 3; i++) {
                    rotate(cur);
                    solve(cur+1);
                }
            }
        }

        public int solve() {
            solve(0);

            if (min == Integer.MAX_VALUE) {
                return -1;
            } else {
                return min;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BuildingEscape be = new BuildingEscape();

        int res = be.solve();

        if (res == -1) {
            System.out.print(-1);
        } else {
            System.out.print(res);
        }
    }
}