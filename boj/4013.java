package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p4013 {
    public static void main(String[] args) throws IOException {
        Solver s = new Solver();
        s.solve();
    }

    private static class Solver {
        final int V, E;
        final int S, P;
        ArrayList<ArrayList<Integer>> graphSCC = new ArrayList<>();
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        ArrayList<ArrayList<Integer>> graphR = new ArrayList<>();
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        boolean[] visit;
        int[] restaurantsPosition;
        int[] cash;
        int[] id;
        boolean[] hasRestaurantSCC;
        int[] cashSCC;
        int[] dp;
        int size;

        public Solver () throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            /* SCC Node's data */
            id = new int[V+1];
            cashSCC = new int[V+1];
            dp = new int[V+1];

            /* For make SCC */
            visit = new boolean[V+1];
            /* Raw node's data */
            hasRestaurantSCC = new boolean[V+1];
            cash = new int[V+1];

            for (int i = 0; i <= V; i++) {
                graph.add(new ArrayList<>());
                graphR.add(new ArrayList<>());
                graphSCC.add(new ArrayList<>());
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph.get(a).add(b);
                graphR.get(b).add(a);
            }

            for (int i = 0; i < V; i++) {
                cash[i] = Integer.parseInt(br.readLine());
            }

            st = new StringTokenizer(br.readLine(), " ");
            S = Integer.parseInt(st.nextToken());
            P = Integer.parseInt(st.nextToken());
            restaurantsPosition = new int[P];

            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < P; i++) {
                restaurantsPosition[i] = Integer.parseInt(st.nextToken());
            }
        }
        void dfs(int cur) {
            for (int next : graph.get(cur)) {
                if (!visit[next]) {
                    visit[next] = true;
                    dfs(next);
                }
            }
            stack.addLast(cur);
        }
        void dfs() {
            for (int i = 1; i <= V; i++) {
                if (!visit[i]) {
                    visit[i] = true;
                    dfs(i);
                }
            }
        }

        void dfsReverse(int cur, int sccId) {
            id[cur] = sccId;
            cashSCC[sccId] += cash[cur-1];
            for (int next : graphR.get(cur)) {
                if (!visit[next]) {
                    visit[next] = true;
                    dfsReverse(next, sccId);
                } else {
                    if (id[next] != id[cur]) {
                        if (!graphSCC.get(id[next]).contains(id[cur])) {
                            graphSCC.get(id[next]).add(id[cur]);
                        }
                    }
                }
            }
        }
        void dfsReverse() {
            Arrays.fill(visit, false);
            while (!stack.isEmpty()) {
                int cur = stack.pollLast();
                if (!visit[cur]) {
                    visit[cur] = true;
                    dfsReverse(cur, size);
                    size += 1;
                }
            }
        }

        int result = 0;
        int dfsSCC(int cur) {
            if (dp[cur] != -1) {
                return dp[cur];
            }

            int ret = Integer.MIN_VALUE;
            for (int next : graphSCC.get(cur)) {
                ret = Math.max(ret, dfsSCC(next));
            }

            if (ret == Integer.MIN_VALUE) {
                if (hasRestaurantSCC[cur]) {
                    return dp[cur] = cashSCC[cur];
                } else {
                    return dp[cur] = Integer.MIN_VALUE;
                }
            } else {
                return dp[cur] = ret + cashSCC[cur];
            }
        }

        void dfsSCC() {
            Arrays.fill(dp, -1);
            for (int x : restaurantsPosition) { hasRestaurantSCC[id[x]] = true; }

            dfsSCC(id[S]);

            System.out.println(dp[id[S]]);
        }

        public void solve() {
            dfs();
            dfsReverse();
            dfsSCC();
        }
    }
}
