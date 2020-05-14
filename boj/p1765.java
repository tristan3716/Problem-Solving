package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p1765 {
    private static class Solver {
        final int n, m;
        DisjointSet ds;
        public Solver() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            n = Integer.parseInt(br.readLine());
            m = Integer.parseInt(br.readLine());
            ds = new DisjointSet(n);
            ArrayList<ArrayList<Integer>> enemies = new ArrayList<>();
            for (int i = 0; i < n+1; i++) { enemies.add(new ArrayList<>()); }

            for (int i = 0; i < m; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                char type = st.nextToken().charAt(0);
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                switch (type) {
                    case 'F':
                        ds.union(a, b);
                        break;
                    case 'E':
                        enemies.get(a).add(b);
                        enemies.get(b).add(a);
                        break;
                }
            }

            for (int i = 1; i < n+1; i++) {
                for (int x : enemies.get(i)) {
                    for (int y : enemies.get(x)) {
                        ds.union(i, y);
                    }
                }
            }

            Arrays.sort(ds.parent);
            int count = 0;
            int old = 0;
            for (int x : ds.parent) {
                if (x != old) {
                    count++;
                    old = x;
                }
            }
            System.out.println(count);
        }

        private static class DisjointSet {
            int n;

            int[] parent;
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

    public static void main(String[] args) throws IOException {
        Solver s = new Solver();
    }
}