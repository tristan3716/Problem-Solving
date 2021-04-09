package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p9328 {
    static BufferedReader br;
    private static class MazeSolver {
        private static class Pos {
            int r, c;

            public Pos(int r, int c) {
                this.r = r;
                this.c = c;
            }
        }
        final int n;
        final int m;

        final char [][] map;
        Set<Character> keys;
        public MazeSolver() throws IOException {
            String[] str = br.readLine().split(" ");
            n = Integer.parseInt(str[0]);
            m = Integer.parseInt(str[1]);
            map = new char[n+4][m+4];
            for (int i = 0; i < n+4; i++) {
                map[i][0] = map[i][m+3] = '*';
            }
            for (int i = 0; i < m+4; i++) {
                map[0][i] = map[n+3][i] = '*';
            }
            for (int i = 1; i < n+3; i++) {
                map[i][1] = map[i][m+2] = '.';
            }
            for (int i = 1; i < m+3; i++) {
                map[1][i] = map[n+2][i] = '.';
            }
            for (int i = 2; i <= n+1; i++) {
                char[] crr = br.readLine().toCharArray();
                System.arraycopy(crr, 0, map[i], 2, m);
            }
            String x = br.readLine();
            keys = new HashSet<>();
            if (x.charAt(0) != '0') {
                x = x.toUpperCase();
                char[] krr = x.toCharArray();
                for (char k : krr) {
                    keys.add(k);
                }
            }
        }

        private final int [][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        public int solve() {
            int intelCount = 0;
            Queue<Pos> q = new LinkedList<>();
            Map<Character, List<Pos>> dml = new HashMap<>();

            q.offer(new Pos(1, 1));
            map[1][1] = '#';

            while (!q.isEmpty()) {
                Pos c = q.poll();

                for (int [] d : directions) {
                    int nr = c.r + d[0];
                    int nc = c.c + d[1];
                    if (map[nr][nc] != '#' && map[nr][nc] != '*') {
                        Pos next = new Pos(nr, nc);
                        if (Character.isAlphabetic(map[nr][nc])) { // key or door
                            if (Character.isLowerCase(map[nr][nc])) { // key
                                Character key = Character.toUpperCase(map[nr][nc]);
                                keys.add(key);
                                if (dml.containsKey(key)) {
                                    List<Pos> dl = dml.get(key);
                                    for (Pos door : dl) {
                                        q.offer(door);
                                    }
                                }
                                q.offer(next);
                            } else { // door
                                if (keys.contains(map[nr][nc])) {
                                    q.offer(next);
                                } else {
                                    if (dml.containsKey(map[nr][nc])) {
                                        List<Pos> dl = dml.get(map[nr][nc]);
                                        dl.add(next);
                                        dml.replace(map[nr][nc], dl);
                                    } else {
                                        List<Pos> dl = new ArrayList<>();
                                        dl.add(next);
                                        dml.put(map[nr][nc], dl);
                                    }
                                }
                            }
                            map[nr][nc] = '#';
                        } else {
                            if (map[nr][nc] == '$') {
                                intelCount++;
                            }
                            map[nr][nc] = '#';
                            q.offer(next);
                        }
                    }
                }
            }

            return intelCount;
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        while (t-- != 0) {
            MazeSolver ms = new MazeSolver();
            sb.append(ms.solve()).append('\n');
        }
        System.out.println(sb);
    }
}