package src;

import java.util.*;

public class p17135 {
    static void makeCombination(final int[] src, final int r) {
        makeCombination(src, new int[r], 0, r, 0, src.length);
    }

    static int maxResult = 0;

    static void makeCombination(final int[] src, int[] res, final int start, final int r, int current, final int max) {
        if (r == current) {
            List<Pos> enemies = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == 1) {
                        enemies.add(new Pos(i, j));
                    }
                }
            }
            int deadMan = 0;
            while (true) {
                for (int i = 0; i < 3; i++) {
                    int archer = res[i];
                    PriorityQueue<Pos> targetedEnemies = new PriorityQueue<>();

                    for (Pos enemy : enemies) {
                        enemy.distance = Math.abs(archer - enemy.c) + Math.abs(enemy.r - n);

                        if (enemy.distance <= d) {
                            targetedEnemies.offer(enemy);
                        }
                    }

                    if (!targetedEnemies.isEmpty()) {
                        targetedEnemies.poll().isKilled = true;
                    }
                }

                for (Pos enemy : enemies) {
                    enemy.r++;
                }
                for (int e = 0; e < enemies.size(); e++) {
                    Pos enemy = enemies.get(e);
                    if (enemy.isKilled) {
                        enemies.remove(e--);
                        deadMan++;
                    } else if (enemy.r == n) {
                        enemies.remove(e--);
                    }
                }

                if (enemies.size() == 0) {
                    break;
                }
            }

            maxResult = Integer.max(maxResult, deadMan);

        } else {
            for (int i = start; i < max; ++i) {
                res[current] = src[i];
                makeCombination(src, res, i + 1, r, current + 1, max);
            }
        }
    }

    static int n;
    static int m;
    static int d;
    static int[][] map;

    private static class Pos implements Comparable<Pos> {
        int r, c;
        int distance;
        boolean isKilled;

        Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public int compareTo(Pos o) {
            if (this.distance == o.distance) {
                return this.c < o.c ? -1 : 1;
            }
            return this.distance <= o.distance ? -1 : 1;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        d = sc.nextInt();
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int val = sc.nextInt();
                map[i][j] = val;
            }
        }

        int[] raw = new int[m];
        for (int i = 0; i < m; ++i) {
            raw[i] = i;
        }
        makeCombination(raw, 3);

        System.out.println(maxResult);
    }
}