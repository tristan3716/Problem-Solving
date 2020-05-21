import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] map;
    static int[][] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        map = new int[n+2][m+2];
        dist = new int[n+2][m+2];
        for (int i = 0; i < n+2; i++) {
            map[i][0] = 2;
            map[i][m+1] = 2;
            for (int j = 0; j < m+2; j++) {
                dist[i][j] = Integer.MAX_VALUE / 2;
            }
        }
        for (int i = 0; i < m+2; i++) {
            map[0][i] = 2;
            map[n+1][i] = 2;
        }

        for (int i = 1; i < n+1; i++) {
            char[] arr = br.readLine().toCharArray();
            int idx = 0;
            for (int j = 1; j < m+1; j++) {
                if (arr[idx++] == '1')
                    map[i][j] = 1;
            }
        }

        Deque<Pos> dq = new ArrayDeque<>();
        dq.offerFirst(new Pos(1, 1));
        dist[1][1] = 0;

        while (!dq.isEmpty()) {
            Pos c = dq.pollFirst();

            for (int[] d : Pos.dirs) {
                int nr = c.r + d[0];
                int nc = c.c + d[1];

                int w = map[nr][nc];
                if (dist[nr][nc] > dist[c.r][c.c] + w) {
                    dist[nr][nc] = dist[c.r][c.c] + w;
                    if (w == 0) {
                        dq.offerFirst(new Pos(nr, nc));
                    } else if (w == 1) {
                        dq.offerLast(new Pos(nr, nc));
                    }
                }
            }
        }

        System.out.println(dist[n][m]);
    }

    private static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }

        private static int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    }
}