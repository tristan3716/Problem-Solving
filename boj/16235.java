package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @since 02.22 10:35 ~ 11:22
 */
public class p16235 {
    private static class Solver {
        private static class Tree implements Comparable<Tree> {
            int r, c;
            int age;
            boolean isDead;

            public Tree(int r, int c, int age) {
                this.r = r;
                this.c = c;
                this.age = age;
                this.isDead = false;
            }

            @Override
            public int compareTo(Tree tree) {
                return age - tree.age;
            }
        }

        final int n;
        final int m;
        final int k;
        final int[][] sr;
        int[][] cur;
        Queue<Tree>[][] arr;

        @SuppressWarnings("unchecked")
        public Solver() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] str = br.readLine().split(" ");
            n = Integer.parseInt(str[0]);
            m = Integer.parseInt(str[1]);
            k = Integer.parseInt(str[2]);
            sr = new int[n][n];
            arr = new LinkedList[n][n];
            cur = new int[n][n];

            for (int i = 0; i < n; i++) {
                str = br.readLine().split(" ");
                for (int j = 0; j < n; j++) {
                    sr[i][j] = Integer.parseInt(str[j]);
                    arr[i][j] = new LinkedList<>();
                    cur[i][j] = 5;
                }
            }

            for (int i = 0; i < m; i++) {
                str = br.readLine().split(" ");
                int r = Integer.parseInt(str[0]) - 1;
                int c = Integer.parseInt(str[1]) - 1;
                int age = Integer.parseInt(str[2]);

                arr[r][c].offer(new Tree(r, c, age));
            }
        }


        private void spring() {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    PriorityQueue<Tree> pq = new PriorityQueue<>(arr[i][j]);
                    int size = pq.size();
                    for (int l = 0; l < size; l++) {
                        Tree t = pq.poll();
                        if (t.age <= cur[i][j]) {
                            cur[i][j] -= t.age;
                            t.age += 1;
                        } else {
                            t.isDead = true;
                        }
                    }
                }
            }
        }

        private void summer() {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    Queue<Tree> q = arr[i][j];
                    int size = q.size();
                    for (int l = 0; l < size; l++) {
                        Tree t = q.poll();
                        if (t.isDead) {
                            cur[i][j] += (t.age / 2);
                        } else {
                            q.offer(t);
                        }
                    }
                }
            }
        }

        private final static int[][] directions = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1}, /*    */ {0, 1},
                {1, -1}, {1, 0}, {1, 1}};

        private boolean isIn(int r, int c) {
            return (r >= 0 && c >= 0 && r < n && c < n);
        }

        private void fall() {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    Queue<Tree> q = arr[i][j];
                    int size = q.size();
                    for (int l = 0; l < size; l++) {
                        final Tree t = q.poll();
                        assert t != null;
                        if (t.age > 0) {
                            if (t.age % 5 == 0) {
                                for (int[] direction : directions) {
                                    int nr = t.r + direction[0];
                                    int nc = t.c + direction[1];

                                    if (isIn(nr, nc)) {
                                        arr[nr][nc].offer(new Tree(nr, nc, 1));
                                    }
                                }
                            }
                        }
                        q.offer(t);
                    }
                }
            }
        }

        private void winter() {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    cur[i][j] += sr[i][j];
                }
            }
        }

        private int count() {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    sum += arr[i][j].size();
                }
            }
            return sum;
        }

        public int solve() {
            for (int t = 0; t < k; ++t) {
                spring();
                summer();
                fall();
                winter();
            }
            return count();
        }
    }

    public static void main(String[] args) throws IOException {
        Solver s = new Solver();
        System.out.println(s.solve());
    }
}