package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class p17398 {
    private static class DisjointSet {
        int n;
        int [] parent;
        long [] size;
        int [] rank;

        public DisjointSet(int n) {
            this.n = n;
            parent = new int[n+1];
            size = new long[n+1];
            rank = new int[n+1];
            for (int i = 1; i <= n; i++) {
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

            if (p == q) {
                return;
            }
            if (rank[p] < rank[q]) {
                parent[p] = q;
                size[q] += size[p];
            } else {
                parent[q] = p;
                size[p] += size[q];
            }
            if (rank[p] == rank[q])
                rank[p]++;
        }
    }

    static int n, m, q;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        int [][] commands = new int[m][2];
        int [] queries = new int[q];
        LinkedHashSet<Integer> s = new LinkedHashSet<>();

        DisjointSet ds = new DisjointSet(n + 1);
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            commands[i][0] = Integer.parseInt(st.nextToken());
            commands[i][1] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < q; i++) {
            queries[i] = Integer.parseInt(br.readLine()) - 1;
            s.add(queries[i]);
        }
        for (int i = 0; i < m; i++) {
            if (!s.contains(i)) {
                ds.union(commands[i][0], commands[i][1]);
            }
        }
        LinkedList<Integer> l = new LinkedList<>(s);

        long sum = 0;
        for (Iterator<Integer> it = l.descendingIterator(); it.hasNext();) {
            int x = it.next();
            int a = commands[x][0];
            int b = commands[x][1];
            int ap = ds.find(a);
            int bp = ds.find(b);
            if (ap != bp) {
                sum += (ds.size[ap] * ds.size[bp]);
            }
            ds.union(a, b);
        }
        System.out.println(sum);
    }
}