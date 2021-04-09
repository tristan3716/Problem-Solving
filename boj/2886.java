import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static class Pos {
        int r;
        int c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public int distance(Pos other) {
            int dr = Math.abs(other.r - this.r);
            int dc = Math.abs(other.c - this.c);

            long v = dr * (long)dr + (long)dc * dc;
            if (v > Integer.MAX_VALUE) {
                throw new RuntimeException();
            }
            return dr * dr + dc * dc;
        }
    }
    private static class Distance implements Comparable<Distance> {
        int p;
        int c;
        int d;

        public Distance(int p, int c, int d) {
            this.p = p;
            this.c = c;
            this.d = d;
        }

        @Override
        public int compareTo(Distance distance) {
            return this.d - distance.d;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<Pos> chairs = new ArrayList<>();
        ArrayList<Pos> people = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] array = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                char ch = array[j];
                switch (ch) {
                    case 'L':
                        chairs.add(new Pos(i, j));
                        break;
                    case 'X':
                        people.add(new Pos(i, j));
                }
            }
        }
        int chairCount = chairs.size();
        int peopleCount = people.size();
        PriorityQueue<Distance> pq = new PriorityQueue<>();
        for (int i = 0; i < peopleCount; i++) {
            Pos p = people.get(i);
            for (int j = 0; j < chairCount; j++) {
                Pos c = chairs.get(j);
                pq.offer(new Distance(i, j, p.distance(c)));
            }
        }

        int conflict = 0;
        int[] occupied = new int[chairCount];
        boolean[] found = new boolean[peopleCount];
        boolean[] occurConflict = new boolean[chairCount];
        while (!pq.isEmpty()) {
            Distance d = pq.poll();
            if (found[d.p]) continue;
            if (occupied[d.c] == d.d) {
                if (!occurConflict[d.c]) {
                    occurConflict[d.c] = true;
                    conflict++;
                }
                found[d.p] = true;
            } else if (occupied[d.c] == 0){
                occupied[d.c] = d.d;
                found[d.p] = true;
            }
        }

        System.out.println(conflict);
    }
}
