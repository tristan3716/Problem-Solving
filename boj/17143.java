package src;

import java.util.*;

public class p17143 {
    private static class Solver {
        private static class Shark implements Comparable<Shark> {
            int r, c;
            int s, d;
            int z;
            boolean moved;

            public Shark(int r, int c, int s, int d, int z, int n, int m) {
                this.r = r;
                this.c = c;
                this.d = d;
                if (d < 3) {
                    int p = 2 * (n - 1);
                    this.s = s % p;
                } else {
                    int p = 2 * (m - 1);
                    this.s = s % p;
                }
                this.z = z;
            }

            @Override
            public int compareTo(Shark shark) {
                return this.z - shark.z;
            }
            private final static int[] dx = { 0, 0, 1, -1 };
            private final static int[] dy = { -1, 1, 0, 0 };
            // 1: Up, 2: Down, 3: Right, 4: Left
            public void move(int n, int m) {
                moved = true;
                for (int i = 0; i < s; i++) {
                    if (d < 3) {
                        if ((r == n - 1 && d == 2) || (r == 0 && d == 1)) {
                            d = (d == 1) ? 2 : 1;
                        }
                        r += dy[d - 1];
                    } else {
                        if ((c == m - 1 && d == 3) || (c == 0 && d == 4)) {
                            d = (d == 3) ? 4 : 3;
                        }
                        c += dx[d - 1];
                    }
                }
            }
        }

        final int n;
        final int m;
        final int k;
        Deque<Shark>[][] map;

        @SuppressWarnings("unchecked")
        public Solver() {
            Scanner sc = new Scanner(System.in);
            String[] str = sc.nextLine().split(" ");
            n = Integer.parseInt(str[0]);
            m = Integer.parseInt(str[1]);
            k = Integer.parseInt(str[2]);
            map = new Deque[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    map[i][j] = new LinkedList<>();
                }
            }
            for (int i = 0; i < k; i++) {
                str = sc.nextLine().split(" ");
                int r = Integer.parseInt(str[0])-1;
                int c = Integer.parseInt(str[1])-1;
                int s = Integer.parseInt(str[2]);
                int d = Integer.parseInt(str[3]);
                int z = Integer.parseInt(str[4]);
                Shark x = new Shark(r, c, s, d, z, n, m);
                map[r][c].offer(x);
            }
        }

        int currentPosition = 0;
        int sizeSum = 0;
        public void solve() {
            while (currentPosition != m) {
                for (int i = 0; i < n; i++) {
                    if (!map[i][currentPosition].isEmpty()) {
                        Shark x = map[i][currentPosition].pollFirst();
                        sizeSum += x.z;
                        break;
                    }
                }

                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        if (!map[i][j].isEmpty()) {
                            Shark x = map[i][j].peekFirst();
                            if (!x.moved) {
                                map[i][j].pollFirst();
                                x.move(n, m);
                                map[x.r][x.c].offerLast(x);
                            }
                        }
                    }
                }

                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        if (map[i][j].size() > 1) {
                            Shark x = map[i][j].peekFirst();
                            int s = map[i][j].size();
                            Iterator<Shark> it = map[i][j].iterator();
                            it.next();
                            for (int l = 1; l < s; l++) {
                                Shark c = it.next();
                                assert x != null;
                                if (x.compareTo(c) < 0) {
                                    x = c;
                                }
                            }
                            map[i][j].clear();
                            map[i][j].offer(x);
                        }
                    }
                }

                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        if (!map[i][j].isEmpty()) {
                            for (Shark x : map[i][j]) {
                                x.moved = false;
                            }
                        }
                    }
                }

                currentPosition++;
            }

            System.out.println(sizeSum);
        }
    }

    public static void main(String[] args) {
        Solver s = new Solver();
        s.solve();
    }
}