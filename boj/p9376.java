package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p9376 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    private static class Solver {
        int n, m;
        char [][] map;
        int [][][] cost;
        int [][] dist;

        LinkedList<Pos> prisoner = new LinkedList<>();
        private void parse() throws IOException {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            map = new char[n+4][m+4];
            cost = new int[n+4][m+4][3];
            dist = new int[n+4][m+4];
            for (int i = 0; i < n+4; i++) {
                map[i][m+3] = '*';
                map[i][0] = '*';
            }
            for (int i = 0; i < m+4; i++) {
                map[n+3][i] = '*';
                map[0][i] = '*';
            }
            for (int i = 1; i < n+3; i++) {
                map[i][m+2] = '.';
                map[i][1] = '.';
            }
            for (int i = 1; i < m+3; i++) {
                map[n+2][i] = '.';
                map[1][i] = '.';
            }

            prisoner.offer(new Pos(1, 1));
            for (int i = 2; i < n+2; i++) {
                char [] arr = br.readLine().toCharArray();
                System.arraycopy(arr, 0, map[i], 2, m);
                for (int j = 0; j < m; j++) {
                    if (arr[j] == '$') {
                        prisoner.offer(new Pos(i, j+2));
                        map[i][j+2] = '.';
                    }
                }
            }
        }

        final static int [][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        private void bfs(Pos s, int idx) {
            Deque<Pos> q = new LinkedList<>();
            for (int [] v : dist) {
                Arrays.fill(v, Integer.MAX_VALUE);
            }
            dist[s.r][s.c] = 0;
            q.offerFirst(s);

            while (!q.isEmpty()) {
                Pos c = q.poll();

                for (int [] d : directions) {
                    int nr = c.r + d[0];
                    int nc = c.c + d[1];

                    if (map[nr][nc] != '*') {
                        int w = map[nr][nc] == '#' ? 1 : 0;
                        if (dist[nr][nc] > dist[c.r][c.c] + w) {
                            dist[nr][nc] = dist[c.r][c.c] + w;

                            if (w == 0) {
                                q.offerFirst(new Pos(nr, nc));
                            } else {
                                q.offerLast(new Pos(nr, nc));
                            }
                            cost[nr][nc][idx] += dist[nr][nc];
                        }
                    }
                }
            }
        }

        private void escape() {
            for (int i = 0; i < 3; ++i) {
                bfs(prisoner.get(i), i);
            }
        }

        private int find() {
            int min = Integer.MAX_VALUE;
            for (int i = 1; i < n+1; i++) {
                for (int j = 1; j < m+1; j++) {
                    if (map[i][j] != '*') {
                        int v = cost[i][j][0] + cost[i][j][1] + cost[i][j][2];
                        if (map[i][j] == '#') {
                            v -= 2;
                        }
                        min = Math.min(min, v);
                    }
                }
            }
            return min;
        }

        public int solve() throws IOException {
            parse();
            escape();
            return find();
        }
    }
    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (t-- != 0) {
            Solver s = new Solver();
            sb.append(s.solve()).append('\n');
        }
        System.out.print(sb);
    }
}