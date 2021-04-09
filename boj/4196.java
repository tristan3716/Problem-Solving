package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p4196 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    private static class Solver {
        final int V, E;
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        ArrayList<Integer> cc = new ArrayList<>();
        Stack<Integer> s = new Stack<>();
        boolean[] visit;

        public Solver () throws IOException {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            visit = new boolean[V+1];
            for (int i = 0; i <= V; i++) { graph.add(new ArrayList<>()); }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph.get(a).add(b);
            }
        }

        void dfs(int cur) {
            for (int next : graph.get(cur)) {
                if (!visit[next]) {
                    visit[next] = true;
                    dfs(next);
                }
            }
            s.push(cur);
        }
        void dfs() {
            for (int i = 1; i <= V; i++) {
                if (!visit[i]) {
                    visit[i] = true;
                    dfs(i);
                }
            }
        }

        void dfsR(int cur, int id) {
            cc.set(id, cc.get(id) + 1);

            for (int next : graph.get(cur)) {
                if (!visit[next]) {
                    visit[next] = true;
                    dfsR(next, id);
                }
            }
        }
        void dfsR() {
            while (!s.isEmpty()) {
                int cur = s.pop();
                if (!visit[cur]) {
                    visit[cur] = true;
                    cc.add(0);
                    dfsR(cur, cc.size() - 1);
                }
            }
        }

        public void solve() {
            dfs();
            Arrays.fill(visit, false);
            dfsR();

            sb.append(cc.size()).append('\n');
        }
    }
    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());
        while (t-- != 0) {
            Solver s = new Solver();
            s.solve();
        }

        System.out.println(sb);
    }
}