import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static class Package implements Comparable<Package>{
        int source;
        int destination;
        int count;

        public Package(int source, int destination, int count) {
            this.source = source;
            this.destination = destination;
            this.count = count;
        }

        @Override
        public int compareTo(Package o) {
            return this.destination - o.destination;
        }
    }

    private static int findMaximum(int[] caps, int s, int e) {
        int max = caps[s];
        int mi = s;
        for (int i = s + 1; i < e; i++) {
            if (max < caps[i]) {
                max = caps[i];
                mi = i;
            }
        }

        return mi;
    }

    private static void load(int[] caps, int s, int e, int amount) {
        for (int i = s; i < e; i++) {
            caps[i] += amount;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int capacity = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(br.readLine());
        PriorityQueue<Package> pq = new PriorityQueue<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int source = Integer.parseInt(st.nextToken()) - 1;
            int destination = Integer.parseInt(st.nextToken()) - 1;
            int count = Integer.parseInt(st.nextToken());
            
            pq.offer(new Package(source, destination, count));
        }

        int[] caps = new int[n];
        int loaded = 0;
        while (!pq.isEmpty()) {
            Package p = pq.poll();

            int s = p.source;
            int e = p.destination;

            int mi = findMaximum(caps, s, e);

            int left = capacity - caps[mi];
            if (left > 0) {
                int loadable = Math.min(p.count, left);

                if (loadable > 0) {
                    load(caps, s, e, loadable);
                    loaded += loadable;
                }
            }
        }

        System.out.println(loaded);
    }
}