package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p2917 {
    private static class Solver {
        private static class Pos {
            int r, c;

            public Pos(int r, int c) {
                this.r = r;
                this.c = c;
            }
        }
        private static class Path implements Comparable<Path> {
            int r, c;
            int w;

            public Path(int r, int c, int w) {
                this.r = r;
                this.c = c;
                this.w = w;
            }

            @Override
            public int compareTo(Path path) {
                return path.w - this.w;
            }
        }
        final int n, m;
        int [][] map;
        boolean [][] visit;
        int er, ec;
        Queue<Pos> trees = new LinkedList<>();
        PriorityQueue<Path> q = new PriorityQueue<>();
        public Solver () throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            map = new int[n+2][m+2];
            visit = new boolean[n+2][m+2];
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
                        case '+':
                            trees.add(new Pos(i, j));
                            map[i][j] = 1;
                            break;
                        case 'J':
                            er = i;
                            ec = j;
                            break;
                        case 'V':
                            q.offer(new Path(i, j, 0));
                            break;
                    }
                }
            }
        }

        private static final int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        public void solve() {
            int depth = 1;
            while (!trees.isEmpty()) {
                depth++;
                int size = trees.size();
                for (int i = 0; i < size; i++) {
                    Pos c = trees.poll();

                    for (int [] d: directions) {
                        int nr = c.r + d[0];
                        int nc = c.c + d[1];

                        if (map[nr][nc] == 0) {
                            map[nr][nc] = depth;
                            trees.offer(new Pos(nr, nc));
                        }
                    }

                }
            }

            Path p = q.peek();
            p.w = map[p.r][p.c];
            visit[p.r][p.c] = true;
            int min = p.w;
            while (!q.isEmpty()) {
                while (q.peek().w >= min) {
                    Path c = q.poll();

                    if (c.r == er && c.c == ec) {
                        System.out.println(min - 1);
                        return;
                    }
                    for (int [] d: directions) {
                        int nr = c.r + d[0];
                        int nc = c.c + d[1];

                        if (map[nr][nc] != -1 && !visit[nr][nc]) {
                            visit[nr][nc] = true;
                            q.offer(new Path(nr, nc, map[nr][nc]));
                        }
                    }
                }
                min--;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Solver s = new Solver();
        s.solve();
    }
}