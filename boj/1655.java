import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> lowQ = new PriorityQueue<>((a, b) -> b - a);
        PriorityQueue<Integer> highQ = new PriorityQueue<>(Comparator.comparingInt(a -> a));
        for (int i = 0; i < n; i++) {
            int value = numbers[i];

            if (lowQ.size() == 0) {
                lowQ.offer(value);
            } else if (lowQ.size() == highQ.size()) {
                int highTop = highQ.peek();
                if (highTop < value) {
                    lowQ.offer(highQ.poll());
                    highQ.offer(value);
                } else {
                    lowQ.offer(value);
                }
            } else if (lowQ.size() > highQ.size()) {
                int lowTop = lowQ.peek();
                if (lowTop <= value) {
                    highQ.offer(value);
                } else {
                    highQ.offer(lowQ.poll());
                    lowQ.offer(value);
                }
            } else {
                int highTop = highQ.peek();
                if (highTop <= value) {
                    lowQ.offer(value);
                } else {
                    lowQ.offer(highQ.poll());
                    highQ.offer(value);
                }
            }

            sb.append(lowQ.peek()).append('\n');
        }

        System.out.print(sb);
    }
}
