import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        
        if (hasRelation(n, graph)) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    private static int abcde(
            final int current,
            final ArrayList<ArrayList<Integer>> graph,
            final boolean[] visited) {
        int maxDepth = 1;
        ArrayList<Integer> friends = graph.get(current);
        for (int next : friends) {
            if (!visited[next]) {
                visited[next] = true;
                maxDepth = Math.max(maxDepth, abcde(next, graph, visited) + 1);
                if (maxDepth >= 5) return maxDepth;
                visited[next] = false;
            }
        }

        return maxDepth;
    }

    private static boolean hasRelation(int n, ArrayList<ArrayList<Integer>> graph) {
        for (int i = 0; i < n; i++) {
            boolean[] visited = new boolean[n];
            visited[i] = true;
            int depth = abcde(i, graph, visited);
            if (depth >= 5) {
                return true;
            }
        }
        return false;
    }
}
