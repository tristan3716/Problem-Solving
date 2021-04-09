import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int weight = sc.nextInt();
        Deque<Integer> dq = new ArrayDeque<>();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < 6; ++i) {
            int t = sc.nextInt();
            int r = sc.nextInt();
            max = Math.max(max, r);
            dq.offer(r);
        }

        assert dq.peek() != null;
        while (dq.getFirst() != max) {
            dq.offer(dq.poll());
        }
        dq.pollFirst();

        assert dq.peek() != null;
        int height = -1;

        assert dq.peek() != null;
        if (dq.peekFirst() < dq.peekLast()){
            height = dq.pollLast();
        } else {
            height = dq.pollFirst();
        }
        dq.pollLast(); dq.pollFirst();
        
        assert dq.peek() != null;
        int smallSquare = dq.peekFirst() * dq.peekLast();

        System.out.println((max * height - smallSquare) * weight);
    }
}