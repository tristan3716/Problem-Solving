import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    private static class Edge implements Comparable<Edge> {
        int start;
        int end;

        public Edge(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Edge edge) {
            if (this.end == edge.end) {
                return this.start - edge.start;
            }
            return this.end - edge.end;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            pq.offer(new Edge(s, e));
        }

        int count = 0;
        int lastEnd = 0;
        while (!pq.isEmpty()) {
            Edge e = pq.poll();

            if (e.start >= lastEnd) {
                lastEnd = e.end;
                count++;
            }
        }
        System.out.println(count);
    }
}