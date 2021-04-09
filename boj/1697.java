import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int n;
    static int k;
    static int[] visit;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();

        visit = new int[100005];
        Queue<Integer> q = new LinkedList<>();
        q.offer(n);
        while (!q.isEmpty()) {
            int c = q.poll();
            if (c == k) {
                System.out.println(visit[c]);
                break;
            }

            if (c-1 >= 0 && visit[c-1] == 0) {
                visit[c-1] = visit[c]+1;
                q.offer(c-1);
            }
            if (c+1 < 100005 && visit[c+1] == 0) {
                visit[c+1] = visit[c]+1;
                q.offer(c+1);
            }
            if (c*2 < 100005 && visit[c*2] == 0) {
                visit[c*2] = visit[c]+1;
                q.offer(c*2);
            }
        }
    }

}