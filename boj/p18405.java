package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p18405 {
    private static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws IOException {
        Queue<Pos> q = new LinkedList<>();
        LinkedList<Pos> collector = new LinkedList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int [][] map = new int[n+2][n+2];
        for (int i = 0; i < n+2; i++) {
            map[i][0] = -1;
            map[i][n+1] = -1;
            map[0][i] = -1;
            map[n+1][i] = -1;
        }

        for (int i = 1; i < n+1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j < n+1; j++) {
                int t = Integer.parseInt(st.nextToken());
                if (t != 0) {
                    map[i][j] = t;
                    collector.offer(new Pos(i, j));
                }
            }
        }
        collector.sort(Comparator.comparingInt(pos -> map[pos.r][pos.c]));
        collector.forEach(q::offer);
        st = new StringTokenizer(br.readLine(), " ");
        int s = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        final int [][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        for (int i = 0; i < s; i++) {
            int size = q.size();
            for (int j = 0; j < size; j++) {
                Pos c = q.poll();

                for (int [] d : directions) {
                    int nr = c.r + d[0];
                    int nc = c.c + d[1];

                    if (map[nr][nc] == 0) {
                        map[nr][nc] = map[c.r][c.c];
                        q.offer(new Pos(nr, nc));
                    }
                }
            }
        }
        System.out.println(map[x][y]);
    }
}
