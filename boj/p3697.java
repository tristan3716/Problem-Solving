package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class p3697 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static class Solver {
        PriorityQueue<PosC> pq = new PriorityQueue<>();
        private static final int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

        public Solver() throws IOException {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int[][] id = new int[n][m];
            int[][] map = new int[n][m];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < m; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    pq.offer(new PosC(i, j, map[i][j]));
                }
            }
            int result = 0;
            int currentId = 1;
            while (!pq.isEmpty()) {
                PosC start = pq.poll();
                if (id[start.r][start.c] != 0) continue;
                id[start.r][start.c] = currentId;
                int h = start.h;

                int count = 1;
                boolean isTop = true;
                Queue<Pos> q = new LinkedList<>();
                q.offer(new Pos(start.r, start.c));
                while (!q.isEmpty()) {
                    Pos c = q.poll();

                    for (int [] dir : directions) {
                        int nr = c.r + dir[0];
                        int nc = c.c + dir[1];

                        if (nr >= 0 && nc >= 0 && nr < n && nc < m) {
                            if (id[nr][nc] == currentId) {
                                continue;
                            }
                            if (id[nr][nc] != 0 && id[nr][nc] < currentId) {
                                isTop = false;
                                continue;
                            }
                            if (map[nr][nc] == h) {
                                count++;
                            } else if (map[nr][nc] <= h - d) {
                                continue;
                            }
                            id[nr][nc] = currentId;
                            q.offer(new Pos(nr, nc));
                        }
                    }
                }
                if (isTop) {
                    result += count;
                }
                currentId++;
            }

            System.out.println(result);
        }
    }

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());
        while (t-- != 0) {
            Solver s = new Solver();
        }
    }

    private static class PosC implements Comparable<PosC> {
        int r, c, h;

        public PosC(int r, int c, int h) {
            this.r = r;
            this.c = c;
            this.h = h;
        }

        @Override
        public int compareTo(PosC pos) {
            return pos.h - this.h;
        }
    }

    private static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}