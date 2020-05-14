package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p8452 {
    static int n, m, Q;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        Query[] queries = new Query[Q];
        Edge[] edges = new Edge[m+1];
        int[] dist = new int[n+1];
        Arrays.fill(dist, 0x3fffffff);

        graph.add(null);
        for (int i = 1; i < n+1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(s, e);
        }

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            char ch = st.nextToken().charAt(0);
            int idx = Integer.parseInt(st.nextToken());
            queries[i] = new Query(ch, idx);
            if (ch == 'U') {
                edges[idx].u = true;
            }
        }

        for (int i = 1; i <= m; i++) {
            if (!edges[i].u) {
                graph.get(edges[i].s).add(edges[i].e);
            }
        }

        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        dist[1] = 0;
        while (!q.isEmpty()) {
            int current = q.poll();

            for (int next : graph.get(current)) {
                if (dist[next] > dist[current] + 1) {
                    dist[next] = dist[current] + 1;
                    q.offer(next);
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = Q-1; i >= 0; i--) {
            if (queries[i].ch == 'E') {
                result.add(dist[queries[i].i] == 0x3fffffff ? -1 : dist[queries[i].i]);
            } else {
                Edge edge = edges[queries[i].i];
                graph.get(edge.s).add(edge.e);

                if (dist[edge.e] > dist[edge.s] + 1) {
                    dist[edge.e] = dist[edge.s] + 1;
                    q.offer(edge.e);
                    while (!q.isEmpty()) {
                        int current = q.poll();

                        for (int next : graph.get(current)) {
                            if (dist[next] > dist[current] + 1) {
                                dist[next] = dist[current] + 1;
                                q.offer(next);
                            }
                        }
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        Collections.reverse(result);
        for (int x : result) {
            sb.append(x).append('\n');
        }

        System.out.print(sb);
    }

    private static class Edge {
        int s, e;
        boolean u;

        public Edge(int s, int e) {
            this.s = s;
            this.e = e;
        }
    }

    private static class Query {
        char ch;
        int i;

        public Query(char ch, int i) {
            this.ch = ch;
            this.i = i;
        }
    }
}
