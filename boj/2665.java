import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Main {
    private static class Pos {
        int r;
        int c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public final static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        char[][] map = new char[n+2][n+2];
        int[][] dist = new int[n+2][n+2];
        for (int i = 0; i < n + 2; i++) {
            map[i][0] = 2;
            map[0][i] = 2;
            map[i][n+1] = 2;
            map[n+1][i] = 2;
            for (int j = 0; j < n + 2; j++) {
                dist[i][j] = Integer.MAX_VALUE / 2;
            }
        }

        for (int i = 1; i < n + 1; i++) {
            char[] raw = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                if (raw[j] == '0') {
                    raw[j] = 1;
                } else if (raw[j] == '1') {
                    raw[j] = 0;
                }
            }
            System.arraycopy(raw, 0, map[i], 1, n);
        }

        Deque<Pos> dq = new ArrayDeque<>();
        dq.push(new Pos(1, 1));
        dist[1][1] = 0;
        while (!dq.isEmpty()) {
            Pos c = dq.pollFirst();

            for (int[] d : Pos.directions) {
                int nr = c.r + d[0];
                int nc = c.c + d[1];
                int w = map[nr][nc];

                if (dist[nr][nc] > dist[c.r][c.c] + w) {
                    dist[nr][nc] = dist[c.r][c.c] + w;
                    if (w == 1) {
                        dq.offerFirst(new Pos(nr, nc));
                    } else if (w == 0){
                        dq.offerLast(new Pos(nr, nc));
                    }
                }
            }
        }

        System.out.println(dist[n][n]);
    }
}
