package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p11952 {
    static int n, m, k, s;
    static int p, q;
    static boolean[] infected = new boolean[100001];
    static boolean[] near = new boolean[100001];
    static long[] distance = new long[100001];
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    private static class D implements Comparable<D> {
        int s;
        long w;

        public D(int s, long w) {
            this.s = s;
            this.w = w;
        }

        @Override
        public int compareTo(D d) {
            if (w < d.w) return -1;
            else return 1;
        }
    }

    private static void dfs(int s, int life) {
        near[s] = true;
        if (life == 0) return;

        for (int x : graph.get(s)) {
            dfs(x, life-1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n+1; i++) {
            graph.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine(), " ");
        p = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        Queue<Integer> qq = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            int t = Integer.parseInt(br.readLine());
            near[t] = infected[t] = true;
            qq.offer(t);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            graph.get(s).add(e);
            graph.get(e).add(s);
        }

        for (int i = 0; i < s; i++) {
            int qs = qq.size();
            for (int j = 0; j < qs; j++) {
                int c = qq.poll();
                for (int nx : graph.get(c)) {
                    if (!near[nx]) {
                        near[nx] = true;
                        qq.offer(nx);
                    }
                }
            }
        }

        final long INF = Long.MAX_VALUE / 3;
        Arrays.fill(distance, INF);

        PriorityQueue<D> pq = new PriorityQueue<>();
        pq.offer(new D(1, 0));
        while (!pq.isEmpty()) {
            D c = pq.poll();
            if (distance[c.s] < c.w) continue;
            distance[c.s] = c.w;

            for (int next : graph.get(c.s)) {
                if (!infected[next]) {
                    long w = distance[c.s] + (near[next] ? q : p);
                    if (distance[next] > w) {
                        distance[next] = w;
                        pq.offer(new D(next, distance[next]));
                    }
                }
            }
        }

        int w = (near[n] ? q : p);
        System.out.println(distance[n] - w);
    }
}