package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p17836 {
    private static class Solver {
        private static class Pos {
            int r, c;

            public Pos(int r, int c) {
                this.r = r;
                this.c = c;
            }
        }
        final int n, m, t;
        int [][] map;

        public Solver () throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken()) + 1;
            map = new int[n+2][m+2];

            for (int i = 0; i < n+2; i++) {
                map[i][0] = 1;
                map[i][m+1] = 1;
            }
            for (int i = 0; i < m+2; i++) {
                map[0][i] = 1;
                map[n+1][i] = 1;
            }
            for (int i = 1; i < n+1; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 1; j < m+1; j++) {
                    int t = Integer.parseInt(st.nextToken());
                    if (t == 1) {
                        map[i][j] = 1;
                    } else if (t == 2) {
                        map[i][j] = -9;
                    }
                }
            }
        }

        private int getDistance(int r, int c) {
            return Math.abs(n-r) + Math.abs(m-c);
        }

        private static final int [][] directions = {{0, -1}, {0, 1}, {1, 0}, {-1,0}};
        public int solve() {
            if (map[1][1] < 0) { // gram
                return getDistance(1, 1);
            }
            int gram = t;
            boolean getGram = false;
            Queue<Pos> q = new LinkedList<>();
            q.offer(new Pos(1, 1));
            map[1][1] = 1;
            int min = gram;

            while (!q.isEmpty()) {
                Pos c = q.poll();

                for (int [] d : directions) {
                    int nr = c.r + d[0];
                    int nc = c.c + d[1];

                    if (map[nr][nc] == 0 && map[c.r][c.c] < min) {
                        map[nr][nc] = map[c.r][c.c] + 1;
                        q.offer(new Pos(nr, nc));
                    } else if (map[nr][nc] < 0) { // gram
                        getGram = true;
                        gram = map[c.r][c.c] + 1 + getDistance(nr, nc);
                        min = Math.min(gram, min);

                        map[nr][nc] = map[c.r][c.c] + 1;
                    }
                }
            }

            if (getGram) {
                if (map[n][m] == 0) {
                    map[n][m] = gram;
                } else {
                    map[n][m] = Math.min(map[n][m], gram);
                }
            }

            if (map[n][m] > t) {
                return -1;
            }
            return map[n][m] - 1;
        }
    }

    public static void main(String[] args) throws IOException {
        Solver s = new Solver();
        int res = s.solve();
        System.out.println(res == -1 ? "Fail" : res);
    }
}
