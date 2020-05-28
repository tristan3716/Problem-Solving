package p1953;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    private static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static int[][] map;

    private static int key(final int type, final int direction) {
        return direction * 100 + type;
    }

    private static final int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private static final Set<Integer> up = new HashSet<>(Arrays.asList(1, 2, 5, 6));
    private static final Set<Integer> down = new HashSet<>(Arrays.asList(1, 2, 4, 7));
    private static final Set<Integer> right = new HashSet<>(Arrays.asList(1, 3, 6, 7));
    private static final Set<Integer> left = new HashSet<>(Arrays.asList(1, 3, 4, 5));

    private static Set<Integer> getSet(final int direction) {
        switch (direction) {
            case 0:
                return down;
            case 1:
                return up;
            case 2:
                return right;
            case 3:
                return left;
        }

        return null;
    }

    private static int toInt(final char direction) {
        switch (direction) {
            case 'd':
                return 0;
            case 'u':
                return 1;
            case 'r':
                return 2;
            case 'l':
                return 3;
        }

        return -1;
    }

    private static final Map<Integer, Set<Integer>> table = new HashMap<>();

    private static void add(final int type, final char direction) {
        table.put(key(type, toInt(direction)), getSet(toInt(direction)));
    }

    static {
        add(1, 'u');
        add(1, 'd');
        add(1, 'l');
        add(1, 'r');

        add(2, 'd');
        add(2, 'u');

        add(3, 'r');
        add(3, 'l');

        add(4, 'r');
        add(4, 'u');

        add(5, 'd');
        add(5, 'r');

        add(6, 'd');
        add(6, 'l');

        add(7, 'u');
        add(7, 'l');
    }

    private static boolean isReachable(int cur, int d, int next) {
        return table.containsKey(key(cur, d)) && table.get(key(cur, d)).contains(next);
    }

    private static int bfs(final int n, final int m, final Pos s, final int l) {
        final boolean[][] visit = new boolean[n][m];

        Queue<Pos> q = new LinkedList<>();
        q.offer(s);
        visit[s.r][s.c] = true;

        int count = 1;
        for (int t = 1; t < l && !q.isEmpty(); t++) {
            int qs = q.size();
            for (int i = 0; i < qs; i++) {
                Pos c = q.poll();

                for (int j = 0; j < 4; j++) {
                    int nr = c.r + directions[j][0];
                    int nc = c.c + directions[j][1];

                    if (!visit[nr][nc]) {
                        if (isReachable(map[c.r][c.c], j, map[nr][nc])) {
                            count++;
                            visit[nr][nc] = true;
                            q.offer(new Pos(nr, nc));
                        }
                    }
                }
            }
        }

        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        final int t = Integer.parseInt(br.readLine()) + 1;
        for (int tc = 1; tc < t; tc++) {
            st = new StringTokenizer(br.readLine(), " ");
            final int n = Integer.parseInt(st.nextToken());
            final int m = Integer.parseInt(st.nextToken());
            final int sr = Integer.parseInt(st.nextToken()) + 1;
            final int sc = Integer.parseInt(st.nextToken()) + 1;
            final int l = Integer.parseInt(st.nextToken());

            map = new int[n + 2][m + 2];

            for (int i = 1; i < n + 1; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 1; j < m + 1; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            final int answer = bfs(n + 2, m + 2, new Pos(sr, sc), l);

            sb.append('#').append(tc).append(' ').append(answer).append('\n');
        }

        System.out.print(sb);
    }
}