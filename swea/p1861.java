import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
    static class Node {
        int r, c;
        int length;

        public Node(int r, int c) {
            this(r, c, 0);
        }
        public Node(int r, int c, int length) {
            super();
            this.r = r;
            this.c = c;
            this.length = length;
        }
    }

    static class Result {
        int value = Integer.MAX_VALUE;
        int length = -1;
    }

    public static boolean isIn(int i, int j, int n) {
        return (i >= 0 && j >= 0 && i < n && j < n);
    }

    static int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};


    static int count;
    static int minValue;
    public static void bfs(int i, int j, int[][] arr, boolean[][] visit, int n) {
        Deque<Node> q = new LinkedList<>();
        q.offer(new Node(i, j));
        while (!q.isEmpty()) {
            Node c = q.poll();


            if (visit[c.r][c.c])
                continue;
            visit[c.r][c.c]= true;
            count++;
            minValue = Math.min(arr[c.r][c.c], minValue);


            for (int [] d : dir) {
                int nr = c.r + d[0];
                int nc = c.c + d[1];

                if (isIn(nr, nc, n) && !visit[nr][nc] && Math.abs(arr[nr][nc] - arr[c.r][c.c]) == 1) {
                    q.offer(new Node(nr, nc, c.length+1));
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();

        for (int t = 1; t <= tc; ++t) {
            int n = sc.nextInt();
            int[][] arr = new int[n][n];
            boolean[][] visit = new boolean[n][n];

            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }

            Result res = new Result();
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; j++) {
                    if (!visit[i][j]) {
                        count = 0;
                        minValue = Integer.MAX_VALUE;
                        bfs(i, j, arr, visit, n);

                        if (count == res.length) {
                            res.value = Math.min(minValue, res.value);
                        }
                        else {
                            if (res.length < count) {
                                res.value = minValue;
                                res.length = count;
                            }
                        }
                    }
                }
            }

            System.out.printf("#%d %d %d\n", t, res.value, res.length);
        }
    }
}