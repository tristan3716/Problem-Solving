import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static int K, R, C;
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(bf.readLine());
        StringTokenizer tks = new StringTokenizer(bf.readLine());

        C = Integer.parseInt(tks.nextToken());
        R = Integer.parseInt(tks.nextToken());

        map = new int[R][C];
        visited = new boolean[K + 1][R][C];

        for (int i = 0; i < R; i++) {
            tks = new StringTokenizer(bf.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(tks.nextToken());
            }
        }

        bfs();

    }

    static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int[][] dir2 = {{-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}, {1, -2}, {2, -1}, {2, 1}, {1, 2}};

    static class Monkey {
        int r, c, cnt, k;

        public Monkey(int r, int c, int cnt, int k) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.k = k;
        }

        @Override
        public String toString() {
            return "Monkey [r=" + r + ", c=" + c + ", cnt=" + cnt + ", k=" + k + "]";
        }


    }

    private static void bfs() {
        Queue<Monkey> q = new LinkedList<>();
        q.offer(new Monkey(0, 0, 0, K));
        visited[K][0][0] = true;

        while (!q.isEmpty()) {
            Monkey f = q.poll();

            if (f.r == R - 1 && f.c == C - 1) {
                System.out.println(f.cnt);
                return;
            }

            if (f.k > 0) {
                for (int i = 0; i < dir2.length; i++) {
                    int nr = f.r + dir2[i][0];
                    int nc = f.c + dir2[i][1];

                    if (isIn(nr, nc) && !visited[f.k - 1][nr][nc] && map[nr][nc] == 0) {
                        q.offer(new Monkey(nr, nc, f.cnt + 1, f.k - 1));
                        visited[f.k - 1][nr][nc] = true;
                    }
                }
            }

            for (int i = 0; i < dir.length; i++) {
                int nr = f.r + dir[i][0];
                int nc = f.c + dir[i][1];

                if (isIn(nr, nc) && !visited[f.k][nr][nc] && map[nr][nc] == 0) {
                    q.offer(new Monkey(nr, nc, f.cnt + 1, f.k));
                    visited[f.k][nr][nc] = true;
                }
            }
        }
        System.out.println(-1);
    }

    private static boolean isIn(int nr, int nc) {
        return nr >= 0 && nr < R && nc >= 0 && nc < C;
    }

}