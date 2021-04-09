import java.io.*;
import java.util.*;

public class p4485 {
    private static class Pos implements Comparable<Pos> {
        int r, c;
        int cost;

        public Pos(int r, int c, int cost) {
            this.r = r;
            this.c = c;
            this.cost = cost;
        }

        @Override
        public int compareTo(Pos pos) {
            return this.cost - pos.cost;
        }
    }

    static int n;
    static int[][] arr = new int[127][127];
    static int[][] distance = new int[127][127];

    public static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private static boolean isIn(int r, int c) {
        return (r >= 0 && r < n && c >= 0 && c < n);
    }

    private static void dijkstra() {
        for (int i = 0; i < n; ++i) {
            Arrays.fill(distance[i], Integer.MAX_VALUE / 3);
        }
        PriorityQueue<Pos> q = new PriorityQueue<>();

        distance[0][0] = arr[0][0];
        q.offer(new Pos(0, 0, distance[0][0]));

        while (!q.isEmpty()) {
            Pos x = q.poll();
            int r = x.r;
            int c = x.c;
            int cost = x.cost;

            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                if (isIn(nr, nc)) {
                    int w = arr[nr][nc] + cost;

                    if (distance[nr][nc] > w) {
                        distance[nr][nc] = w;
                        q.offer(new Pos(nr, nc, w));
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int tc = 1;
        while (true) {
            n = Integer.parseInt(br.readLine());
            if (n == 0) {
                break;
            }

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dijkstra();

            sb.append("Problem ").append(tc).append(": ").append(distance[n-1][n-1]).append('\n');
        }
        System.out.println(sb);
    }
}