package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p2638 {
    private static class CheeseSolver {
        final int n;
        final int m;

        int [][] map;
        Set<Pos> cheese = new HashSet<>();
        public CheeseSolver() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            map = new int[n][m];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < m; j++) {
                    int t = Integer.parseInt(st.nextToken());
                    if (t == 1) {
                        map[i][j] = 1;
                        cheese.add(new Pos(i, j));
                    }
                }
            }
        }
        private static class Pos {
            int r, c;

            public Pos(int r, int c) {
                this.r = r;
                this.c = c;
            }
        }

        private final int [][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        private boolean isIn(int r, int c) {
            return (r >= 0 && c >= 0 && r < n && c < m);
        }
        int current = 1;
        int[][] visit;
        private void dfs(int r, int c) {
            for (int [] d : directions) {
                int nr = r + d[0];
                int nc = c + d[1];
                if (isIn(nr, nc)) {
                    if (map[nr][nc] == 0) {
                        if (visit[nr][nc] == 0) {
                            visit[nr][nc] = 1;
                            dfs(nr, nc);
                        }
                    } else {
                        visit[nr][nc] += 1;
                    }
                }
            }
        }

        public void solve() {
            while (!cheese.isEmpty()) {
                visit = new int[n][m];
                visit[0][0] = 0;
                dfs(0, 0);

                cheese.forEach((x) -> {
                    if (visit[x.r][x.c] > 1) {
                        map[x.r][x.c] = 0;
                    }
                });
                cheese.removeIf(x -> map[x.r][x.c] == 0);

                current++;
            }
            System.out.println(current-1);
        }
    }

    public static void main(String[] args) throws IOException {
        CheeseSolver cs = new CheeseSolver();
        cs.solve();
    }
}