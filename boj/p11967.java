package p11967;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

@SuppressWarnings("unchecked")
public class p11967 {
    private static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }

        static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        final int n = Integer.parseInt(st.nextToken());
        final int m = Integer.parseInt(st.nextToken());

        boolean[][] map = new boolean[n + 2][n + 2];
        boolean[][] visited = new boolean[n + 2][n + 2];
        boolean[][] can = new boolean[n + 2][n + 2];

        ArrayList<Pos>[][] graph = new ArrayList[n + 2][n + 2];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ArrayList<Pos> list = graph[x][y];
            if (list == null) {
                list = graph[x][y] = new ArrayList<>();
            }
            list.add(new Pos(a, b));
        }


        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(1, 1));
        map[1][1] = true;
        visited[1][1] = true;

        int count = 1;

        while (!q.isEmpty()) {
            Pos c = q.poll();

            for (int[] d : Pos.dirs) {
                int nr = c.r + d[0];
                int nc = c.c + d[1];

                if (!visited[nr][nc]) {
                    can[nr][nc] = true;
                }
            }

            ArrayList<Pos> cl = graph[c.r][c.c];

            if (cl != null) {
                for (Pos cc : cl) {
                    if (!visited[cc.r][cc.c] && !map[cc.r][cc.c]) {
                        if (can[cc.r][cc.c]) {
                            q.offer(new Pos(cc.r, cc.c));
                        }
                        count++;
                        map[cc.r][cc.c] = true;
                    }
                }
            }

            for (int[] d : Pos.dirs) {
                int nr = c.r + d[0];
                int nc = c.c + d[1];

                if (!visited[nr][nc] && map[nr][nc]) {
                    visited[nr][nc] = true;
                    q.offer(new Pos(nr, nc));
                }
            }

        }

        System.out.println(count);
    }
}
