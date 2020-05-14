package src;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class p14868 {
    private static class CivilizationSolver {
        private static class DisjointSet {
            int n;

            private int[] parent;
            private int[] size;
            private int[] rank;

            public DisjointSet(int n) {
                this.n = n;

                parent = new int[n];
                size = new int[n];
                rank = new int[n];

                for (int i = 0; i < n; i++) {
                    parent[i] = i;
                    size[i] = 1;
                    rank[i] = 0;
                }
            }

            public int find(int x){
                if (parent[x] == x) {
                    return x;
                }
                else {
                    return parent[x] = find(parent[x]);
                }
            }

            public int sizeOf(int x){
                return size[find(x)];
            }

            public void union(int p, int q){
                p = find(p);
                q = find(q);
                if (p == q)
                    return;
                if (rank[p] < rank[q]) {
                    parent[p] = q;
                    size[q] += size[p];
                }
                else {
                    parent[q] = p;
                    size[p] += size[q];
                }
                if (rank[p] == rank[q])
                    rank[p]++;
            }
        }

        int n;
        int k;
        DisjointSet ds;
        int [][] arr;

        private static class Pos {
            int r, c;

            public Pos(int r, int c) {
                this.r = r;
                this.c = c;
            }
        }
        private boolean isIn(int r, int c){
            return (r > 0 && c > 0 && r <= n && c <= n);
        }
        int [][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        Queue<Pos> q;
        Queue<Pos> unionQueue;
        private void bfs() {
            while (!q.isEmpty()) {
                Pos c = q.poll();
                for (int [] d : directions) {
                    int nr = c.r + d[0];
                    int nc = c.c + d[1];
                    if (isIn(nr, nc) && arr[nr][nc] == 0) {
                        arr[nr][nc] = arr[c.r][c.c];
                        unionQueue.offer(new Pos(nr, nc));
                    }
                }
            }
        }

        private void union() {
            while (!unionQueue.isEmpty()) {
                Pos c = unionQueue.poll();
                q.offer(c);

                for (int [] d : directions) {
                    int nr = c.r + d[0];
                    int nc = c.c + d[1];
                    if (isIn(nr, nc)) {
                        if (arr[nr][nc] != 0) {
                            ds.union(arr[nr][nc], arr[c.r][c.c]);
                        }
                    }
                }
            }
        }

        void parse() {
            Scanner sc = new Scanner(System.in);
            n = sc.nextInt();
            k = sc.nextInt();
            ds = new DisjointSet(k+1);
            arr = new int[n+1][n+1];
            q = new LinkedList<>();
            unionQueue = new LinkedList<>();

            for (int i = 1; i <= k; ++i) {
                int r = sc.nextInt();
                int c = sc.nextInt();
                arr[r][c] = i;
                unionQueue.offer(new Pos(r, c));
            }
        }

        void solution() {
            int pass = 0;
            while (true) {
                union();
                if (ds.sizeOf(1) == k) {
                    break;
                }
                pass++;
                bfs();
            }
            System.out.println(pass);
        }
    }

    public static void main(String[] args) {
        CivilizationSolver cs = new CivilizationSolver();
        cs.parse();
        cs.solution();
    }
}
