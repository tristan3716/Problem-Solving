package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p2589 {
    private static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static final int [][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        char [][] map = new char[n+2][m+2];
        for (int i = 0; i < n+2; i++) {
            map[i][m+1] = 'W';
            map[i][0] = 'W';
        }
        for (int i = 0; i < m+2; i++) {
            map[n+1][i] = 'W';
            map[0][i] = 'W';
        }
        for (int i = 1; i < n+1; i++) {
            char [] arr = br.readLine().toCharArray();
            System.arraycopy(arr, 0, map[i], 1, m);
        }

        int [][] dist = new int [n+2][m+2];
        int res = Integer.MIN_VALUE;
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < m+1; j++) {
                if (map[i][j] == 'L') {
                    for (int [] x : dist) {
                        Arrays.fill(x, 0);
                    }
                    Queue<Pos> q = new LinkedList<>();
                    q.offer(new Pos(i, j));
                    dist[i][j] = 1;
                    while (!q.isEmpty()) {
                        Pos c = q.poll();

                        for (int [] d : directions) {
                            int nr = c.r + d[0];
                            int nc = c.c + d[1];

                            if (dist[nr][nc] == 0 && map[nr][nc] == 'L') {
                                dist[nr][nc] = dist[c.r][c.c] + 1;
                                res = Math.max(dist[nr][nc] - 1, res);
                                q.offer(new Pos(nr, nc));
                            }
                        }
                    }
                }
            }
        }

        System.out.println(res);
    }
}