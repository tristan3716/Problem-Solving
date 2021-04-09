package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p3197 {
    public static void main(String[] args) throws IOException {
        SwanSolver ss = new SwanSolver();
        System.out.println(ss.solve());
    }

    private static class SwanSolver {
        final int n, m;
        int[][] map;
        static int[][] dist;
        int[][] ids;
        Queue<Pos> q = new LinkedList<>();
        DisjointSet ds;
        Pos [] swanPosition = new Pos[2];
        int [] swanId = new int[2];

        final int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        int id = 1;
        void dfs(int r, int c) {
            for (int [] d : directions) {
                int nr = r + d[0];
                int nc = c + d[1];

                if (map[nr][nc] == 0 && ids[nr][nc] == 0) {
                    q.offer(new Pos(nr, nc));
                    dist[nr][nc] = 0;
                    ids[nr][nc] = id;
                    dfs(nr, nc);
                }
            }
        }

        private static class Pos implements Comparable<Pos> {
            int r, c;

            public Pos(int r, int c) {
                this.r = r;
                this.c = c;
            }

            @Override
            public int compareTo(Pos pos) {
                return dist[r][c] - dist[pos.r][pos.c];
            }
        }

        void labeling(Pos [] swans) {
            for (int i = 1; i < n+1; i++) {
                for (int j = 1; j < m+1; j++) {
                    if (map[i][j] == 0 && ids[i][j] == 0) {
                        dist[i][j] = 0;
                        ids[i][j] = id;
                        dfs(i, j);
                        q.offer(new Pos(i, j));
                        id++;
                    }
                }
            }
            swanId[0] = ids[swans[0].r][swans[0].c];
            swanId[1] = ids[swans[1].r][swans[1].c];
            ds = new DisjointSet(id);
        }

        boolean meet() {
            return (ds.find(swanId[0]) == ds.find(swanId[1]));
        }

        int bfs01() {
            Deque<Pos> dq = new ArrayDeque<>(q);

            while (!dq.isEmpty()) {
                Pos c = dq.pollFirst();
                for (int [] d : directions) {
                    int nr = c.r + d[0];
                    int nc = c.c + d[1];

                    int we = dist[c.r][c.c] + map[nr][nc];
                    if (dist[nr][nc] > we) {
                        dist[nr][nc] = we;
                        if (map[nr][nc] == 0) {
                            dq.offerFirst(new Pos(nr, nc));
                        } else {
                            ids[nr][nc] = ids[c.r][c.c];
                            ds.union(ids[nr][nc], ids[c.r][c.c]);
                            if (meet()) { return we; }
                            dq.offerLast(new Pos(nr, nc));
                        }
                    } else if (dist[nr][nc] != Integer.MAX_VALUE && Integer.MAX_VALUE != dist[c.r][c.c]) {
                        if (dist[nr][nc] != -2 && dist[c.r][c.c] != -2) {
                            ds.union(ids[nr][nc], ids[c.r][c.c]);
                            if (meet()) {
                                return dist[nr][nc];
                            }
                        }
                    }
                }
            }

            return -1;
        }

        public SwanSolver() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            map = new int[n+2][m+2];
            dist = new int[n+2][m+2];
            ids = new int[n+2][m+2];
            for (int i = 0; i < n+2; i++) {
                Arrays.fill(dist[i], Integer.MAX_VALUE);
                dist[i][0] = ids[i][0] = map[i][0] = -2;
                dist[i][m+1] = ids[i][m+1] = map[i][m+1] = -2;
            }
            for (int i = 0; i < m+2; i++) {
                map[0][i] = dist[0][i] = ids[0][i] = -2;
                map[n+1][i] = dist[n+1][i] = ids[n+1][i] = -2;
            }
            int idx = 0;
            for (int i = 1; i < n+1; i++) {
                char [] s = br.readLine().toCharArray();
                for (int j = 1; j < m+1; j++) {
                    switch (s[j-1]) {
                        case 'X':
                            map[i][j] = 1;
                            break;
                        case 'L':
                            swanPosition[idx++] = new Pos(i, j);
                        case '.':
                            map[i][j] = 0;
                    }
                }
            }
        }

        public int solve() {
            labeling(swanPosition);
            return bfs01();
        }
    }

    private static class DisjointSet {
        int n;

        int[] parent;
        int[] rank;

        public DisjointSet(int n) {
            this.n = n;

            parent = new int[n];
            rank = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        public int find(int x){
            if (parent[x] == x) {
                return x;
            } else {
                return parent[x] = find(parent[x]);
            }
        }

        public void union(int p, int q){
            p = find(p);
            q = find(q);
            if (p == q)
                return;
            if (rank[p] < rank[q]) {
                parent[p] = q;
            } else {
                parent[q] = p;
            }
            if (rank[p] == rank[q])
                rank[p]++;
        }
    }
}
