package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p6087 {
    public static void main(String[] args) throws IOException {
        Solver s = new Solver();
        s.solve();
    }

    private static class Solver {
        private static class Pos {
            int r, c;

            public Pos(int r, int c) {
                this.r = r;
                this.c = c;
            }
        }
        final int m, n;
        int [][] map;
        int [][] dist;
        Queue<Pos> q;
        public Solver() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());

            q = new LinkedList<>();
            map = new int[n + 2][m + 2];
            dist = new int[n + 2][m + 2];
            for (int i = 0; i < n+2; i++) {
                map[i][0] = -1;
                map[i][m+1] = -1;
            }
            for (int i = 0; i < m+2; i++) {
                map[0][i] = -1;
                map[n+1][i] = -1;
            }

            for (int i = 1; i < n+1; i++) {
                char [] arr = br.readLine().toCharArray();
                for (int j = 1; j < m+1; j++) {
                    switch (arr[j-1]) {
                        case '*':
                            map[i][j] = -1; break;
                        case 'C':
                            q.offer(new Pos(i, j));
                        default:
                            map[i][j] = Integer.MAX_VALUE;
                    }
                }
            }
        }
        private static final int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

        void dfs(int r, int c, int d) {
            int nr = r + directions[d][0];
            int nc = c + directions[d][1];

            if (map[nr][nc] >= mirror && map[nr][nc] != -1) {
                map[nr][nc] = mirror;
                q.offer(new Pos(nr, nc));
                dfs(nr, nc, d);
            }
        }

        private int mirror = 0;
        public void solve() {
            Pos start = q.poll();
            Pos end = q.poll();
            map[start.r][start.c] = mirror + 1;
            q.offer(start);

            while (!q.isEmpty()) {
                mirror++;
                int qs = q.size();

                for (int i = 0; i < qs; i++) {
                    Pos c = q.poll();

                    for (int j = 0; j < 4; j++) {
                        dfs(c.r, c.c, j);
                    }
                }
            }

            System.out.println(map[end.r][end.c] - 1);
        }
    }
}
