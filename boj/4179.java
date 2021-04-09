package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p4179 {
    private static class EscapeSolver {
        private final char startCharacter = 'J';
        private final char endCharacter = 'E';
        private final char wallCharacter = '#';
        private final char emptyCharacter = '.';
        private final char fireCharacter = 'F';

        final int n, m;
        char [][] map;
        int [][] dist;
        Queue<Pos> eq = new LinkedList<>();
        Queue<Pos> fq = new LinkedList<>();
        public EscapeSolver() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            map = new char[n+2][m+2];
            dist = new int[n+2][m+2];
            for (int i = 0; i < n+2; i++) {
                map[i][0] = endCharacter;
                map[i][m+1] = endCharacter;
            }
            for (int i = 0; i < m+2; i++) {
                map[0][i] = endCharacter;
                map[n+1][i] = endCharacter;
            }
            for (int i = 1; i < n+1; i++) {
                char [] arr = br.readLine().toCharArray();
                for (int j = 1; j < m+1; j++) {
                    char ch = arr[j-1];
                    switch (ch) {
                        case fireCharacter:
                            fq.offer(new Pos(i, j)); break;
                        case startCharacter:
                            eq.offer(new Pos(i, j));
                            ch = emptyCharacter;
                            break;
                    }
                    map[i][j] = ch;
                }
            }
        }

        private static final int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        public void solve() {
            while (!eq.isEmpty()) {
                int qs = fq.size();
                for (int i = 0; i < qs; i++) {
                    Pos c = fq.poll();

                    for (int [] d : directions) {
                        int nr = c.r + d[0];
                        int nc = c.c + d[1];

                        if (map[nr][nc] == emptyCharacter) {
                            map[nr][nc] = fireCharacter;
                            fq.offer(new Pos(nr, nc));
                        }
                    }
                }
                qs = eq.size();
                for (int i = 0; i < qs; i++) {
                    Pos c = eq.poll();

                    for (int [] d : directions) {
                        int nr = c.r + d[0];
                        int nc = c.c + d[1];

                        if (map[nr][nc] == emptyCharacter) {
                            map[nr][nc] = fireCharacter;
                            dist[nr][nc] = dist[c.r][c.c] + 1;
                            eq.offer(new Pos(nr, nc));
                        } else if (map[nr][nc] == endCharacter) {
                            System.out.println((dist[c.r][c.c] + 1));
                            System.exit(0);
                        }
                    }
                }
            }
            System.out.println("IMPOSSIBLE");
        }
    }

    private static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        EscapeSolver es = new EscapeSolver();
        es.solve();
    }
}