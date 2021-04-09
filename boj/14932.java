package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p14932 {
    public static void main(String[] args) throws IOException {
        Solver s = new Solver();
        s.solve();
    }

    private static class Solver {
        final int n;
        int[][] dist;
        char[][] dir;
        int[][] id;
        DisjointSet ds;

        public Solver() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            n = Integer.parseInt(br.readLine());
            dist = new int[n][n];
            dir = new char[n][n];
            id = new int[n][n];

            ds = new DisjointSet(n * n);

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < n; j++) {
                    String s = st.nextToken();
                    dir[i][j] = s.charAt(s.length() - 1);
                    dist[i][j] = Integer.parseInt(s.substring(0, s.length() - 1));
                }
            }
        }

        private void dfs(int r, int c, int v) {
            id[r][c] = v;
            int d = dist[r][c];
            switch (dir[r][c]) {
                case 'D':
                    er = r + d;
                    ec = c;
                    if (id[r + d][c] == 0) {
                        dfs(r + d, c, v);
                    } else {
                        int pc = ds.find(id[r+d][c]);
                        int pv = ds.find(v);
                        if (pc != pv) {
                            ds.union(pc, pv);
                            sameSet = true;
                        }
                    }
                    break;
                case 'U':
                    er = r - d;
                    ec = c;
                    if (id[r - d][c] == 0) {
                        dfs(r - d, c, v);
                    } else {
                        int pc = ds.find(id[r-d][c]);
                        int pv = ds.find(v);
                        if (pc != pv) {
                            ds.union(pc, pv);
                            sameSet = true;
                        }
                    }
                    break;
                case 'R':
                    er = r;
                    ec = c + d;
                    if (id[r][c + d] == 0) {
                        dfs(r, c + d, v);
                    } else {
                        int pc = ds.find(id[r][c+d]);
                        int pv = ds.find(v);
                        if (pc != pv) {
                            ds.union(pc, pv);
                            sameSet = true;
                        }
                    }
                    break;
                case 'L':
                    er = r;
                    ec = c - d;
                    if (id[r][c - d] == 0) {
                        dfs(r, c - d, v);
                    } else {
                        int pc = ds.find(id[r][c-d]);
                        int pv = ds.find(v);
                        if (pc != pv) {
                            ds.union(pc, pv);
                            sameSet = true;
                        }
                    }
                    break;
            }
        }

        boolean sameSet;
        int sr = 0, sc = 0;
        int er = 0, ec = 0;
        public void solve() {
            int cnt = 0;
            int ccc = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (id[i][j] == 0) {
                        sr = i;
                        sc = j;
                        cnt++;
                        sameSet = false;
                        dfs(i, j, cnt);
                        if (!sameSet) { ccc++; }
                    }
                }
            }

            if (cnt == 1 && er == 0 && ec == 0) {
                System.out.println("THIEF LOVE IT!");
            } else if (ccc > 1) {
                System.out.println("TOO SAFE");
            } else {
                System.out.println((sr+1) + " " + (sc+1));
            }
        }


        private static class DisjointSet {
            int n;

            private int[] parent;
            private int[] rank;

            public DisjointSet(int n) {
                this.n = ++n;

                parent = new int[n];
                rank = new int[n];

                for (int i = 0; i < n; i++) {
                    parent[i] = i;
                    rank[i] = 0;
                }
            }

            public int find(int x) {
                if (parent[x] == x) {
                    return x;
                } else {
                    return parent[x] = find(parent[x]);
                }
            }

            public void union(int p, int q) {
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
}
