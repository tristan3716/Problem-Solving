package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class p13306 {
    private static class Query {}
    private static class Union extends Query {
        int c, p;

        public Union(int c, int p) {
            this.c = c;
            this.p = p;
        }
    }

    private static class Connect extends Query {
        int a, b;

        public Connect(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    private static class DisjointSet {
        int n;

        private int[] parent;
        private int[] rank;

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
            }
            else {
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
            }
            else {
                parent[q] = p;
            }
            if (rank[p] == rank[q])
                rank[p]++;
        }
    }

    private static class Solver {
        final int n, q;
        int [] parent;
        int [] cp;
        Query [] queries;
        DisjointSet ds;
        public Solver () throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            q = Integer.parseInt(st.nextToken());
            parent = new int[n+1];
            cp = new int[n+1];
            ds = new DisjointSet(n + 1);
            parent[1] = 1;
            for (int i = 2; i <= n; i++) {
                parent[i] = Integer.parseInt(br.readLine());
            }
            queries = new Query[n - 1 + q];
            for (int i = 0; i < n + q - 1; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                if (st.countTokens() == 2) {
                    st.nextToken();
                    int c = Integer.parseInt(st.nextToken());
                    queries[i] = new Union(c, parent[c]);
                } else {
                    st.nextToken();
                    int a = Integer.parseInt(st.nextToken());
                    int b = Integer.parseInt(st.nextToken());
                    queries[i] = new Connect(a, b);
                }
            }
            Collections.reverse(Arrays.asList(queries));
        }
        public void solve() {
            ArrayList<String> result = new ArrayList<>();
            for (int i = 0; i < n + q - 1; i++) {
                if (queries[i] instanceof Union) {
                    Union x = (Union) queries[i];
                    ds.union(x.c, x.p);
                } else {
                    Connect x = (Connect) queries[i];
                    result.add(ds.find(x.a) == ds.find(x.b) ? "YES" : "NO");
                }
            }
            Collections.reverse(result);
            StringBuilder sb = new StringBuilder();
            for (String x : result) {
                sb.append(x).append('\n');
            }
            System.out.println(sb);
        }
    }

    public static void main(String[] args) throws IOException {
        Solver s = new Solver();
        s.solve();
    }
}
