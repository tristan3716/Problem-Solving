import java.util.*;

public class Main {
    /* Case 1. A -> B, Case 2. B -> A */
    /* Case 3. A -> C, Case 4. C -> A */
    /* Case 5. B -> C, Case 6. C -> B */
    private static int [] s = {0, 0, 1, 1, 2, 2};
    private static int [] e = {1, 2, 0, 2, 0, 1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int A = sc.nextInt();
        int B = sc.nextInt();
        int C = sc.nextInt();
        int [] max = {A, B, C};

        boolean[][] visit = new boolean[205][205]; // 8MB

        Queue<int []> q = new LinkedList<>();
        Set<Integer> result = new TreeSet<>();
        q.offer(new int[] {0, 0, C});

        while (!q.isEmpty()) {
            int [] c = q.poll();

            if (c[0] == 0) { result.add(c[2]); } // when a is 0, add c

            for (int i = 0; i < 6; i++) {
                int from = s[i];
                int to = e[i];
                int [] ns = {c[0], c[1], c[2]};

                if (ns[to] + ns[from] > max[to]) {
                    ns[from] -= (max[to] - ns[to]);
                    ns[to] = max[to];
                }
                else {
                    ns[to] += ns[from];
                    ns[from] = 0;
                }
                if (!visit[ns[0]][ns[1]]) {
                    visit[ns[0]][ns[1]] = true;
                    q.offer(ns);
                }
            }

        }

        StringBuilder sb = new StringBuilder();
        result.forEach(x -> sb.append(x).append(' '));
        System.out.println(sb);
    }

}