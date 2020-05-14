package src;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class p1261 {
    private static class Solver {
        final int n;
        final int m;
        int [][] arr;
        int [][] dist;

        public Solver () {
            Scanner sc = new Scanner(System.in);
            int [] nm = Stream.of(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            m = nm[0];
            n = nm[1];
            arr = new int[n][m];
            for (int i = 0; i < n; i++) {
                arr[i] = Stream.of(sc.nextLine().split("")).mapToInt(Integer::parseInt).toArray();
            }
            dist = new int[n][m];
            for (int [] x : dist) {
                Arrays.fill(x, Integer.MAX_VALUE);
            }
        }
        private boolean isIn(int r, int c) {
            return (r >= 0 && c >= 0 && r < n && c < m);
        }
        final private static int [][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        public int solve() {
            int [] queueR = new int[500005];
            int [] queueC = new int[500005];
            int queueBegin = 0;
            int queueEnd = 0;
            queueR[queueEnd] = 0;
            queueC[queueEnd++] = 0;
            dist[0][0] = 0;

            while (queueBegin != queueEnd) {
                int r = queueR[queueBegin];
                int c = queueC[queueBegin++];

                for (int [] d : directions) {
                    int nr = r + d[0];
                    int nc = c + d[1];

                    if (isIn(nr, nc)) {
                        if (arr[nr][nc] == 1) {
                            if (dist[nr][nc] > dist[r][c] + 1) {
                                dist[nr][nc] = dist[r][c] + 1;
                                queueR[queueEnd] = nr;
                                queueC[queueEnd++] = nc;
                            }
                        } else {
                            if (dist[nr][nc] > dist[r][c]) {
                                dist[nr][nc] = dist[r][c];
                                queueR[queueEnd] = nr;
                                queueC[queueEnd++] = nc;
                            }
                        }
                    }
                }
            }
            return dist[n-1][m-1];
        }
    }

    public static void main(String[] args) {
        Solver as = new Solver();
        System.out.println(as.solve());
    }
}
