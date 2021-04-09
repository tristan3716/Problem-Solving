import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int find(final int[] p, final int x) {
        return p[x] == x ? x : (p[x] = find(p, p[x]));
    }

    private static void union(final int[] p, final int a, final int b) {
        int pa = find(p, a);
        int pb = find(p, b);

        if (pa != pb) {
            p[pb] = pa;
        }
    }

    private static void build(final ArrayList<ArrayList<Integer>> graph,
                              int[] depth,
                              int[][] pa,
                              int current) {
        for (int next : graph.get(current)) {
            if (depth[next] == 0) {
                pa[next][0] = current;
                depth[next] = depth[current] + 1;
                build(graph, depth, pa, next);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());

        final int MAX = (int) (Math.log10(n) / Math.log10(2) + 0.5) + 3;
        int[][] pp = new int[n + 1][20];
        int[] depth = new int[n + 1];
        int[] p = new int[n + 1];

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        graph.add(null);
        for (int i = 0; i < n + 1; i++) {
            p[i] = i;
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            st  = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            union(p, a, b);
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        int root = 1;
        depth[root] = 1;
        build(graph, depth, pp, root);

        for (int j = 0; j < MAX; j++) {
            for (int i = 1; i < n + 1; i++) { // ??
                if (pp[i][j] != 0) {
                    pp[i][j + 1] = pp[pp[i][j]][j];
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        int q = Integer.parseInt(br.readLine());
        for (int i = 0; i < q; i++) {
            st  = new StringTokenizer(br.readLine(), " ");
            final int A = Integer.parseInt(st.nextToken());
            final int B = Integer.parseInt(st.nextToken());
            int a = A;
            int b = B;
            if (depth[a] < depth[b]) {
                int t = a;
                a = b;
                b = t;
            }
            for (int j = MAX; j >= 0; j--) {
                if ((depth[a] - depth[b]) >= (1 << j)) {
                    a = pp[a][j];
                }
            }
            if (a != b) {
                for (int j = MAX - 1; j >= 0; j--) {
                    if (pp[a][j] != 0 && pp[a][j] != pp[b][j]) {
                        a = pp[a][j];
                        b = pp[b][j];
                    }
                }

                a = pp[a][0];
            }

            if (a == 0) {
                a = root;
            }

            sb.append(a).append('\n');
        }
        System.out.print(sb.toString());
    }
}
