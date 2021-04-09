import java.util.*;

public class Main {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int A = sc.nextInt();
        int B = sc.nextInt();
        int C = sc.nextInt();
        int D = sc.nextInt();

        //Set<Long> visit = new HashSet<>();
        HashSet<Integer>[] visit = new HashSet[100056];
        for (int i = 0; i < 100056; ++i) {
            visit[i] = new HashSet<>();
        }

        Queue<int []> q = new LinkedList<>();
        q.offer(new int[] {0, 0, 0});
        visit[0].add(0);

        while (!q.isEmpty()) {
            int [] c = q.poll();

            if (c[0] == C && c[1] == D) {
                System.out.println(c[2]);
                System.exit(0);
            }

            if (!visit[A].contains(c[1])) {
                visit[A].add(c[1]);
                q.offer(new int[] {A, c[1], c[2] + 1});
            }
            if (!visit[c[0]].contains(B)) {
                visit[c[0]].add(B);
                q.offer(new int[] {c[0], B, c[2] + 1});
            }

            if (!visit[0].contains(c[1])) {
                visit[0].add(c[1]);
                q.offer(new int[] {0, c[1], c[2] + 1});
            }
            if (!visit[c[0]].contains(0)) {
                visit[c[0]].add(0);
                q.offer(new int[] {c[0], 0, c[2] + 1});
            }
            if (A >= c[0]+c[1]) {
                if (!visit[c[0]+c[1]].contains(0)) {
                    visit[c[0]+c[1]].add(0);
                    q.offer(new int[] {(c[0]+c[1]), 0, c[2]+1});
                }
            } else {
                if (!visit[A].contains(c[1] - (A - c[0]))) {
                    visit[A].add(c[1] - (A - c[0]));
                    q.offer(new int[]{A, c[1] - (A - c[0]), c[2] + 1});
                }
            }
            if (B >= c[0]+c[1]) {
                if (!visit[0].contains(c[0]+c[1])) {
                    visit[0].add(c[0]+c[1]);
                    q.offer(new int[] {0, (c[0]+c[1]), c[2]+1});
                }
            } else {
                if (!visit[c[0] - (B-c[1])].contains(B)) {
                    visit[c[0] - (B-c[1])].add(B);
                    q.offer(new int[] {(c[0] - (B-c[1])), B, c[2] + 1});
                }
            }
        }

        System.out.println(-1);
    }

}