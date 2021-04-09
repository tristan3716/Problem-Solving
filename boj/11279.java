import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((integer, t1) -> t1 - integer);
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; ++i){
            int q = sc.nextInt();
            if (q == 0) {
                if (pq.isEmpty()) {
                    System.out.println(0);
                }
                else {
                    System.out.println(pq.poll());
                }
            }
            else {
                pq.offer(q);
            }
        }
    }
}
