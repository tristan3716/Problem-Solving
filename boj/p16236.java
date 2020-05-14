package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class p16236 {
    private static class Pos implements Comparable<Pos> {
        int r, c;
        int dist;
        boolean isDead;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
            this.isDead = false;
        }

        public Pos(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
            this.isDead = false;
        }

        @Override
        public int compareTo(Pos o) {
            if (this.r == o.r) {
                return this.c - o.c;
            } else {
                return this.r - o.r;
            }
        }
    }

    static int n;
    static int m;
    static int[][] arr;
    static int sharkSize = 2;
    static int eatCount = 0;
    static Queue<Pos> q = new LinkedList<>();
    static PriorityQueue<Pos> pq = new PriorityQueue<>();
    static LinkedList<Pos> l = new LinkedList<>();
    static int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    static boolean isIn(int r, int c) {
        return (r >= 0 && c >= 0 && r < n && c < m);
    }

    static boolean[][] visit;

    public static boolean inspect() {
        Pos shark = q.peek();
        visit[shark.r][shark.c] = true;
        int minDistance = Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            Pos cur = q.poll();

            for (int[] direction : directions) {
                int nr = cur.r + direction[0];
                int nc = cur.c + direction[1];

                if (isIn(nr, nc) && !visit[nr][nc] && sharkSize >= arr[nr][nc]) {
                    visit[nr][nc] = true;
                    Pos x = new Pos(nr, nc, cur.dist + 1);
                    if (cur.dist <= minDistance) {
                        q.offer(x);
                        if (sharkSize > arr[nr][nc] && arr[nr][nc] != 0) {
                            minDistance = Math.min(cur.dist, minDistance);
                            pq.offer(x);
                        }
                    }
                }
            }
        }
        q.clear();
        q.offer(shark);

        for (boolean[] x : visit) {
            Arrays.fill(x, false);
        }

        return !pq.isEmpty();
    }

    public static int eat() {
        Pos fish = pq.poll();
        Pos shark = q.peek();
        shark.r = fish.r;
        shark.c = fish.c;
        arr[fish.r][fish.c] = 0;
        fish.isDead = true;
        eatCount++;
        if (eatCount == sharkSize) {
            sharkSize++;
            eatCount = 0;
        }

        l.removeIf(x -> x.isDead);
        pq.clear();
        return fish.dist;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = n;
        arr = new int[n][m];
        visit = new boolean[n][m];

        for (int i = 0; i < n; ++i) {
            String[] str = br.readLine().split(" ");
            for (int j = 0; j < m; ++j) {
                arr[i][j] = Integer.parseInt(str[j]);
                if (arr[i][j] != 0) {
                    if (arr[i][j] == 9) {
                        q.offer(new Pos(i, j, 0));
                        arr[i][j] = 0;
                    } else {
                        l.offer(new Pos(i, j));
                    }
                }
            }
        }

        int time = 0;
        while (true) {
            boolean isEatable = inspect();
            if (isEatable) {
                time += eat();
            } else {
                break;
            }
        }
        System.out.println(time);
    }
}