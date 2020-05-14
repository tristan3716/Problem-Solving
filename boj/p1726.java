package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p1726 {
    private static class RobotSolver {
        private static class Pos {
            int r, c, d;
            int mv, cd;

            public Pos(int r, int c, int d, int mv, int cd) {
                this.r = r;
                this.c = c;
                this.d = d;
                this.mv = mv;
                this.cd = cd;
            }
        }
        final int n, m;
        int [][] map;
        int sr, sc, sd;
        int er, ec, ed;

        public RobotSolver() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            map = new int[n+2][m+2];
            for (int i = 0; i < n+2; i++) {
                map[i][0] = -1;
                map[i][m+1] = -1;
            }
            for (int j = 0; j < m+2; j++) {
                map[0][j] = -1;
                map[n+1][j] = -1;
            }

            for (int i = 1; i < n+1; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 1; j < m+1; j++) {
                    map[i][j] = -Integer.parseInt(st.nextToken());
                }
            }
            st = new StringTokenizer(br.readLine(), " ");
            sr = Integer.parseInt(st.nextToken());
            sc = Integer.parseInt(st.nextToken());
            sd = Integer.parseInt(st.nextToken());
            if (sd == 2) sd = 3;
            else if (sd == 3) sd = 2;

            st = new StringTokenizer(br.readLine(), " ");
            er = Integer.parseInt(st.nextToken());
            ec = Integer.parseInt(st.nextToken());
            ed = Integer.parseInt(st.nextToken());
            if (ed == 2) ed = 3;
            else if (ed == 3) ed = 2;
        }

        private final int [][] directions = {{0, 1, 1}, {1, 0, 2}, {0, -1, 3}, {-1, 0, 4}};
        private boolean isIn(int r, int c) {
            return (r >= 1 && c >= 1 && r < n+1 && c < m+1);
        }
        public int solve() {
            if (sr == er && sc == ec) {
                int diff = Math.abs(sd - ed);
                if (diff > 2) diff = 1;
                return diff;
            }
            Queue<Pos> q = new LinkedList<>();
            q.offer(new Pos(sr, sc, sd, 0, 0));
            map[sr][sc] = -1;

            while (!q.isEmpty()) {
                Pos c = q.poll();

                if (c.r == er && c.c == ec) {
                    continue;
                }

                for (int [] d : directions) {
                    int nd = d[2];
                    int diff = Math.abs(nd - c.d);
                    if (diff > 2) diff = 1;
                    int nr = c.r + d[0];
                    int nc = c.c + d[1];
                    int nmv = c.mv + 1;
                    int w = nmv + diff + c.cd;
                    for (int i = 0; i < 3; i++) {
                        if (map[nr][nc] == -1) {
                            break;
                        }
                        if (isIn(nr, nc)) {
                            if (map[nr][nc] != -1) {
                                if (nr == er && nc == ec) {
                                    int ediff = Math.abs(nd - ed);
                                    if (ediff > 2) ediff = 1;
                                    int ew = w + ediff;
                                    if (map[er][ec] > ew || map[er][ec] == 0) {
                                        map[er][ec] = ew;
                                    }
                                    continue;
                                }
                                if (map[nr][nc] > w || map[nr][nc] == 0) {
                                    map[nr][nc] = w;
                                    q.offer(new Pos(nr, nc, nd, nmv, c.cd + diff));
                                }
                            }
                        }

                        nr += d[0];
                        nc += d[1];

                    }
                }

            }

            return map[er][ec];
        }
    }

    public static void main(String[] args) throws IOException {
        RobotSolver rs = new RobotSolver();
        System.out.println(rs.solve());
    }
}