import java.util.*;

public class Main {
    static List<Integer>[] graph;
    static boolean[] visit;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int start = sc.nextInt();
        int end = sc.nextInt();
        int m = sc.nextInt();

        graph = new List[n + 1];
        visit = new boolean[n + 1];
        for (int i = 1; i <= n; ++i) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; ++i) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            graph[s].add(e);
            graph[e].add(s);
        }

        Queue<int []> q = new LinkedList<>();
        q.offer(new int[]{start, 0});
        visit[start] = true;
        while (!q.isEmpty()) {
            int [] c = q.poll();

            if (c[0] == end) {
                System.out.println(c[1]);
                System.exit(0);
            }
            for (Integer x : graph[c[0]]) {
                if (!visit[x]) {
                    visit[x] = true;
                    q.offer(new int[]{x, c[1] + 1});
                }
            }
        }
        System.out.println(-1);
    }
}