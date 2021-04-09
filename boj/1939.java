import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static boolean isReachable(
            ArrayList<Map<Integer, Integer>> graph,
            final int n,
            int from,
            int to,
            int weight) {
        boolean[] visited = new boolean[n + 1];

        Queue<Integer> q = new LinkedList<>();
        q.offer(from);
        while (!q.isEmpty()) {
            int current = q.poll();

            Map<Integer, Integer> links = graph.get(current);
            for (Map.Entry<Integer, Integer> x : links.entrySet()) {
                int next = x.getKey();
                int limit = x.getValue();
                if (!visited[next] && weight <= limit) {
                    visited[next] = true;
                    q.offer(next);
                }
            }
        }

        return visited[to];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        ArrayList<Map<Integer, Integer>> graph = new ArrayList<>();
        graph.add(null);
        for (int i = 0; i <= n; i++) {
            graph.add(new TreeMap<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            Map<Integer, Integer> mapA = graph.get(a);
            Map<Integer, Integer> mapB = graph.get(b);
            if (mapA.containsKey(b)) {
                int value = Math.max(mapA.get(b), c);
                mapA.replace(b, value);
                mapB.replace(a, value);
            } else {
                mapA.put(b, c);
                mapB.put(a, c);
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        int s = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        int low = 1;
        int high = 1000000000;
        int mid;

        int result = 0;

        while (low <= high) {
            mid = (low + high) / 2;

            if (isReachable(graph, n, s, t, mid)) {
                result = Math.max(result, mid);
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println(result);
    }
}
