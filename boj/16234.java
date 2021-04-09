package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p16234 {
    private static int n, l, r;
    private static int unionSize;
    private static int unionSum;
    private static int[][] map;
    private static int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    private static boolean[][] visited;
    private static int[] tx, ty;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        map = new int[n + 2][n + 2];

        tx = new int[10005];
        ty = new int[10005];

        for (int i = 0; i < n + 2; i++) {
            map[0][i] = 500;
            map[i][0] = 500;

            map[n + 1][i] = 500;
            map[i][n + 1] = 500;
        }

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int migrationCount = 0;

        while (true) {
            visited = new boolean[n+2][n+2];
            boolean migrationOccurred = false;
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (!visited[i][j]) {
                        unionSize = 0;
                        unionSum = 0;
                        findUnion(i, j);

                        migrationOccurred |= (unionSize != 1);
                        int avg = unionSum / unionSize;
                        for (int t = 0; t < unionSize; t++) {
                            map[tx[t]][ty[t]] = avg;
                        }
                    }
                }
            }
            if (migrationOccurred) {
                migrationCount++;
            } else {
                break;
            }
        }

        System.out.print(migrationCount);
    }

    private static void findUnion(int cr, int cc) {
        visited[cr][cc] = true;
        tx[unionSize] = cr;
        ty[unionSize] = cc;
        unionSize++;
        unionSum += map[cr][cc];

        for (int[] dir : directions) {
            int nr = cr + dir[0];
            int nc = cc + dir[1];

            if (!visited[nr][nc]) {
                int diff = Math.abs(map[nr][nc] - map[cr][cc]);

                if (l <= diff && diff <= r) {
                    findUnion(nr, nc);
                }
            }
        }
    }
}