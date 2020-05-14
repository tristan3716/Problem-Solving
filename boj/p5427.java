package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p5427 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    private static class EscapeSolver {
        final int n, m;
        char [][] map;
        int [][] dist;
        Queue<Pos> eq = new LinkedList<>();
        Queue<Pos> fq = new LinkedList<>();
        public EscapeSolver() throws IOException {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            map = new char[n+2][m+2];
            dist = new int[n+2][m+2];
            for (int i = 0; i < n+2; i++) {
                map[i][0] = 'E';
                map[i][m+1] = 'E';
            }
            for (int i = 0; i < m+2; i++) {
                map[0][i] = 'E';
                map[n+1][i] = 'E';
            }
            for (int i = 1; i < n+1; i++) {
                char [] arr = br.readLine().toCharArray();
                for (int j = 1; j < m+1; j++) {
                    char ch = arr[j-1];
                    switch (ch) {
                        case '*':
                            fq.offer(new Pos(i, j)); break;
                        case '@':
                            eq.offer(new Pos(i, j));
                            ch = '.';
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

                        if (map[nr][nc] == '.') {
                            map[nr][nc] = '*';
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

                        if (map[nr][nc] == '.') {
                            map[nr][nc] = '*';
                            dist[nr][nc] = dist[c.r][c.c] + 1;
                            eq.offer(new Pos(nr, nc));
                        } else if (map[nr][nc] == 'E') {
                            sb.append((dist[c.r][c.c] + 1));
                            return;
                        }
                    }
                }
            }
            sb.append("IMPOSSIBLE");
        }
    }

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());
        while (t-- != 0) {
            EscapeSolver es = new EscapeSolver();
            es.solve();
            sb.append('\n');
        }
        System.out.println(sb);
    }
}

class Pos {
    int r, c;

    public Pos(int r, int c) {
        this.r = r;
        this.c = c;
    }
}