import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int n;
    private static boolean[] visited;
    private static boolean[][] graph;
    private static Map<Integer, Set<Integer>> subset = new TreeMap<>();
    private static boolean stab = true;

    private static void dfs(int start, int current) {
        visited[current] = true;
        for (int i = 0; i < n; i++) {
            if (!visited[i] && graph[current][i] && graph[i][current]) {
                for (int x : subset.get(start)) {
                    if (!graph[i][x] || !graph[x][i]) {
                        stab = false;
                        return;
                    }
                }
                subset.get(start).add(i);
                dfs(start, i);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        visited = new boolean[n];
        graph = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                graph[i][j] = st.nextToken().equals("0");
            }
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                Set<Integer> s = new TreeSet<>();
                s.add(i);
                subset.put(i, s);
                dfs(i, i);
            }
        }

        for (Map.Entry<Integer, Set<Integer>> x: subset.entrySet()) {
            if (x.getValue().size() == 1) {
                stab = false;
                break;
            }
        }

        if (stab) {
            StringBuilder sb = new StringBuilder();
            sb.append(subset.size()).append('\n');
            for (Map.Entry<Integer, Set<Integer>> x: subset.entrySet()) {
                for (int y : x.getValue()) {
                    sb.append(y + 1).append(' ');
                }
                sb.append('\n');
            }
            System.out.print(sb);
        } else {
            System.out.println(0);
        }
    }
}
