package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class p4195 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static class Solver {
        DisjointSet ds;
        int id = 0;
        Map<String, Integer> map = new HashMap<>();
        public Solver() throws IOException {
            StringBuilder sb = new StringBuilder();
            int n = Integer.parseInt(br.readLine());
            ds = new DisjointSet(n);
            while (n-- != 0) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                String a = st.nextToken();
                String b = st.nextToken();
                int ai;
                int bi;
                if (map.containsKey(a)) {
                    ai = map.get(a);
                } else {
                    map.put(a, id);
                    ai = id++;
                }
                if (map.containsKey(b)) {
                    bi = map.get(b);
                } else {
                    map.put(b, id);
                    bi = id++;
                }
                ds.union(ai, bi);
                sb.append(ds.size[ds.find(ai)]).append('\n');
            }
            sb.deleteCharAt(sb.length()-1);
            System.out.println(sb);
        }
    }
    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());
        while (t-- != 0) {
            Solver s = new Solver();
        }
    }
    private static class DisjointSet {
        int n;

        private int[] parent;
        int[] size;
        private int[] rank;

        public DisjointSet(int n) {
            this.n = ++n;

            parent = new int[n];
            size = new int[n];
            rank = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
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
                size[q] += size[p];
            } else {
                parent[q] = p;
                size[p] += size[q];
            }
            if (rank[p] == rank[q])
                rank[q]++;
        }
    }
}
