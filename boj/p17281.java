package src;

import java.util.Scanner;

public class p17281 {
    private static class Solver {
        final int n;
        final int[][] sr;
        public Solver() {
            Scanner sc = new Scanner(System.in);
            n = Integer.parseInt(sc.nextLine());
            sr = new int[n][10];
            for (int i = 0; i < n; i++) {
                String [] str = sc.nextLine().split(" ");
                for (int j = 0; j < 9; j++) {
                    sr[i][j+1] = Integer.parseInt(str[j]);
                }
            }
        }
        private int evaluation(int [] state) {
            int score = 0;
            int current = 1;
            for (int i = 0; i < n; i++) {
                boolean [] base = new boolean[3];
                int out = 0;
                while (out != 3) {
                    switch (sr[i][state[current]]) {
                        case 0:
                            out++;
                            break;
                        case 1:
                            if (base[2]) {
                                score++;
                            }
                            base[2] = base[1];
                            base[1] = base[0];
                            base[0] = true;
                            break;
                        case 2:
                            if (base[2]) {
                                score++;
                            }
                            if (base[1]) {
                                score++;
                            }
                            base[2] = base[0];
                            base[1] = true;
                            base[0] = false;
                            break;
                        case 3:
                            if (base[2]) {
                                score++;
                            }
                            if (base[1]) {
                                score++;
                            }
                            if (base[0]) {
                                score++;
                            }
                            base[2] = true;
                            base[1] = false;
                            base[0] = false;
                            break;
                        case 4:
                            for (int j = 0; j < 3; j++) {
                                if (base[j]) {
                                    score++;
                                }
                                base[j] = false;
                            }
                            score++;
                    }
                    current++;
                    if (current == 10) {
                        current = 1;
                    }
                }
            }
            return score;
        }
        public int maxScore = 0;
        private void permu(int [] res, boolean [] visit, int current) {
            if (current == 10) {
                maxScore = Math.max(maxScore, evaluation(res));
            } else {
                for (int i = 1; i < 10; i++) {
                    if (!visit[i]) {
                        visit[i] = true;
                        res[i] = current;
                        permu(res, visit, current + 1);
                        visit[i] = false;
                    }
                }
            }
        }

        public void solve() {
            boolean [] visit = new boolean[10];
            int [] res = new int[10];
            visit[4] = true;
            res[4] = 1;
            permu(res, visit, 2);
        }
    }

    public static void main(String[] args) {
        Solver s = new Solver();
        s.solve();
        System.out.println(s.maxScore);
    }
}