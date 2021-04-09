import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static class Pos {
        int r;
        int c;
        int t;

        public Pos(int r, int c, int t) {
            this.r = r;
            this.c = c;
            this.t = t;
        }
        public static boolean isIn(int nr, int nc, int r, int c) {
            return nr < r && nr >= 0 && nc < c && nc >= 0;
        }
        public static int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        int hx = Integer.parseInt(st.nextToken()) - 1;
        int hy = Integer.parseInt(st.nextToken()) - 1;

        st = new StringTokenizer(br.readLine(), " ");
        int ex = Integer.parseInt(st.nextToken()) - 1;
        int ey = Integer.parseInt(st.nextToken()) - 1;

        int [][] map = new int[n][m];
        int [][][] dist = new int[n][m][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                map[i][j] = st.nextToken().charAt(0) - '0';
                dist[i][j][0] = Integer.MAX_VALUE / 3;
                dist[i][j][1] = Integer.MAX_VALUE / 3;
            }
        }

        Queue<Pos> q = new LinkedList<>();

        q.offer(new Pos(hx, hy, 1));
        dist[hx][hy][0] = 0;
        dist[hx][hy][1] = 0;

        while (!q.isEmpty()) {
            Pos c = q.poll();

            for (int[] d : Pos.directions) {
                int nr = c.r + d[0];
                int nc = c.c + d[1];
                int nw = dist[c.r][c.c][c.t] + 1;

                if (Pos.isIn(nr, nc, n, m)) {
                    if (map[nr][nc] != 1 && dist[nr][nc][c.t] > nw) {
                        dist[nr][nc][c.t] = nw;
                        q.offer(new Pos(nr, nc, c.t));
                    } else {
                        if (c.t == 1 && dist[nr][nc][0] > nw) {
                            dist[nr][nc][0] = nw;
                            q.offer(new Pos(nr, nc, 0));
                        }
                    }
                }
            }
        }

        int result = Math.min(dist[ex][ey][0], dist[ex][ey][1]);
        System.out.println(result >= Integer.MAX_VALUE / 3 ? -1 : result);
    }
}
