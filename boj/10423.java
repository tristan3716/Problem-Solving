package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class p10423 {
    static int n, m, k;
    static int[] p;
    static ArrayList<Edge> l = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        p = new int[n+1];
        for (int i = 1; i <= n; i++) {
            p[i] = i;
        }
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < k; i++) {
            int t = Integer.parseInt(st.nextToken());
            p[t] = -1;
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            l.add(new Edge(s, e, w));
        }
        l.sort(null);
        int sum = 0;
        for (int i = 1, li = 0; i <= n-k;) {
            Edge c = l.get(li++);
            int ps = find(c.s);
            int pe = find(c.e);
            if (ps != pe) {
                sum += c.w;
                union(c.s, c.e);
                i++;
            }
        }
        System.out.println(sum);
    }

    private static int find(int x) {
        if (x == p[x] || p[x] == -1) return p[x];
        else return p[x] = find(p[x]);
    }
    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == -1) {
            p[b] = a;
        } else if (b == -1) {
            p[a] = b;
        } else {
            p[a] = b;
        }
    }

    private static class Edge implements Comparable<Edge> {
        int s, e, w;

        public Edge(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Edge edge) {
            return w - edge.w;
        }
    }
}