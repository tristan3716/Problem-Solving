package src;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class p5014 {
    private static class Solver {
        final int f;
        final int s;
        final int g;
        final int u;
        final int d;
        int [] visit;

        public Solver() {
            Scanner sc = new Scanner(System.in);
            f = sc.nextInt();
            s = sc.nextInt();
            g = sc.nextInt();
            u = sc.nextInt();
            d = sc.nextInt();
            visit = new int[f+1];
        }

        public int solve() {
            if (s == g) {
                return 0;
            }
            Queue<Integer> q = new LinkedList<>();
            q.offer(s);
            visit[s] = 0;
            while (!q.isEmpty()) {
                int c = q.poll();

                if (c + u < f + 1 && visit[c + u] == 0 && u != 0) {
                    if (c + u == g) {
                        return visit[c] + 1;
                    }
                    q.offer(c + u);
                    visit[c + u] = visit[c] + 1;
                }
                if (c - d > 0 && visit[c - d] == 0 && d != 0) {
                    if (c - d == g) {
                        return visit[c] + 1;
                    }
                    q.offer(c - d);
                    visit[c - d] = visit[c] + 1;
                }
            }
            return -1;
        }
    }

    public static void main(String[] args) {
        Solver s = new Solver();
        int res = s.solve();
        System.out.println(res == -1 ? "use the stairs" : res);
    }
}