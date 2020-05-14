package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p16973 {
    private static class MoveSolver {
        private static class Pos{
            int r, c;

            public Pos(int r, int c) {
                this.r = r;
                this.c = c;
            }
        }

        int[][] map;
        final int n;
        final int m;
        final int a;
        final int b;

        final int targetR;
        final int targetC;
        final int startR;
        final int startC;

        public MoveSolver() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            map = new int[n+1][m+1];
            for (int i = 1; i < n+1; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 1; j < m+1; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            st = new StringTokenizer(br.readLine(), " ");
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            startR = Integer.parseInt(st.nextToken());
            startC = Integer.parseInt(st.nextToken());

            targetR = Integer.parseInt(st.nextToken());
            targetC = Integer.parseInt(st.nextToken());
        }

        public int solve(){
            return bfs(startR, startC);
        }

        private final static int[][] directions = {{-1,0}, {1,0}, {0,-1}, {0,1}};
        private int bfs(final int startR, final int startC) {
            Queue<Pos> q = new LinkedList<>();
            q.offer(new Pos(startR, startC));
            map[startR][startC] = 2;

            while (!q.isEmpty()) {
                Pos c = q.poll();

                for (int i = 0; i < directions.length; ++i) {
                    int nr = c.r + directions[i][0];
                    int nc = c.c + directions[i][1];

                    if ((nr >= 1 && nc >= 1 && nr+a-1 <= n && nc+b-1 <= m) && map[nr][nc] == 0 && isMovable(nr, nc, i)) {
                        if (isReach(nr, nc)) {
                            return map[c.r][c.c] + 1 - 2;
                        }
                        map[nr][nc] = map[c.r][c.c] + 1;
                        q.offer(new Pos(nr, nc));
                    }
                }
            }

            return -1;
        }

        private boolean isReach(final int nr, final int nc) {
            return (nr == targetR && nc == targetC);
        }

        private boolean isMovable(int nr, int nc, int dir) {
            switch (dir){
                case 1:
                    nr += (a-1);
                case 0:
                    for (int i = 0; i < b; ++i) {
                        int cc = nc + i;
                        if (map[nr][cc] == 1){
                            return false;
                        }
                    }
                    break;
                case 3:
                    nc += (b-1);
                case 2:
                    for (int i = 0; i < a; ++i) {
                        int cr = nr + i;
                        if (map[cr][nc] == 1){
                            return false;
                        }
                    }
                    break;
            }
            return true;
        }
    }

    public static void main(String[] args) throws IOException {
        MoveSolver ms = new MoveSolver();

        int res = ms.solve();

        System.out.println(res);
    }
}