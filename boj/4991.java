package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p4991 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static class Cleaner {
        private static class Pos {
            int r, c;

            public Pos(int r, int c) {
                this.r = r;
                this.c = c;
            }
        }
        final int n, m;
        final char[][] map;
        int [][] id;
        Deque<Pos> dusts = new LinkedList<>();

        final int v;
        final int fullBit;
        final int [][] dist;
        final int [][] cache;
        public Cleaner(int n, int m) throws IOException {
            this.n = n;
            this.m = m;
            map = new char[n+2][m+2];
            id = new int[n+2][m+2];
            visit = new int[n+2][m+2];
            for (int i = 0; i < n+2; i++) {
                map[i][0] = 'x';
                map[i][m+1] = 'x';
            }
            for (int i = 0; i < m+2; i++) {
                map[0][i] = 'x';
                map[n+1][i] = 'x';
            }
            int cnt = 1;
            for (int i = 1; i < n+1; i++) {
                char [] arr = br.readLine().toCharArray();
                for (int j = 1; j < m+1; j++) {
                    switch (arr[j-1]) {
                        case 'o':
                            id[i][j] = 1;
                            dusts.offerFirst(new Pos(i, j));
                            break;
                        case '*':
                            id[i][j] = ++cnt;
                            dusts.offerLast(new Pos(i, j));
                            break;
                    }
                    map[i][j] = arr[j-1];
                }
            }
            v = dusts.size();
            fullBit = (1 << v) - 1;
            dist = new int[v][v];
            cache = new int[v][1 << v];
        }

        private static final int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        int [][] visit;
        private boolean bfs(Pos start, int from) {
            int cc = 1;
            for (int [] d : visit) {
                Arrays.fill(d, -1);
            }
            Queue<Pos> q = new LinkedList<>();
            q.offer(start);
            visit[start.r][start.c] = 0;

            while (!q.isEmpty()) {
                Pos c = q.poll();

                for (int [] d : directions) {
                    int nr = c.r + d[0];
                    int nc = c.c + d[1];

                    if (map[nr][nc] != 'x' && visit[nr][nc] == -1) {
                        visit[nr][nc] = visit[c.r][c.c] + 1;
                        if (id[nr][nc] != 0) {
                            dist[from][id[nr][nc]-1] = visit[nr][nc];
                            cc++;
                        }
                        q.offer(new Pos(nr, nc));
                    }
                }
            }
            return cc == v;
        }

        public int tsp(int cur, int visited) {
            if (cache[cur][visited] != 0)
                return cache[cur][visited];

            if (visited == fullBit) {
                return 0;
            }

            int result = 0x3fffffff;
            for (int next = 0; next < v; next++) {
                if ((visited & (1 << next)) != 0) continue;
                if (dist[cur][next] == 0) continue;

                result = Math.min(result, tsp(next, visited | (1 << next)) + dist[cur][next]);
            }
            return (cache[cur][visited] = result);
        }

        public int solve() {
            int size = dusts.size();
            for (int i = 0; i < size; i++) {
                if (!bfs(dusts.pollFirst(), i))
                    return -1;
            }

            return tsp(0, 1);
        }
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            if (n == 0) {
                break;
            }
            Cleaner c = new Cleaner(n, m);
            sb.append(c.solve()).append('\n');
        }
        System.out.println(sb);
    }
}