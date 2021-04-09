package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class p16398 {
    private static int[] parent;
    private static void init(int n) {
        parent = new int[n+1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
    }
    private static int find(int x) {
        if (parent[x] == x)
            return x;
        else
            return parent[x] = find(parent[x]);
    }
    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b)
            parent[a] = b;
    }

    private static class Edge implements Comparable<Edge> {
        int s, e;
        long w;

        public Edge(int s, int e, long w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Edge edge) {
            return Long.compare(w, edge.w);
        }
    }

    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());

        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                long e = Long.parseLong(st.nextToken());
                if (i == j) continue;
                edges.add(new Edge(i, j, e));
            }
        }
        edges.sort(null);
        init(n);

        long sum = 0;
        for (Edge e : edges) {
            int pa = find(e.s);
            int pb = find(e.e);
            if (pa != pb) {
                sum += e.w;
                union(pa, pb);
            }
        }
        System.out.println(sum);
    }
}