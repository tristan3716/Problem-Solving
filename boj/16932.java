package p16932;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p16932 {
    private static final int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    private static boolean isIn(int r, int c, int n, int m) {
        return (r < n && c < m && r >= 0 && c >= 0);
    }
    private static int bfs(int[][] map, int sr, int sc, int n, int m, int id) {
        int size = 1;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{sr, sc});
        map[sr][sc] = id;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int[] dir : dirs) {
                int nr = cur[0] + dir[0];
                int nc = cur[1] + dir[1];
                if (isIn(nr, nc, n, m) && map[nr][nc] == 1) {
                    map[nr][nc] = id;
                    q.offer(new int[]{nr, nc});
                    size++;
                }
            }
        }

        return size;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                map[i][j] = st.nextToken().equals("1") ? 1 : 0;
            }
        }
        int[] size = new int[1000005];

        int id = 2;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1) {
                    size[id] = bfs(map, i, j, n, m, id);
                    id++;
                }
            }
        }

        int maxSize = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                List<Integer> ids = new LinkedList<>();
                ids.add(map[i][j]);
                int curSize = size[map[i][j]];
                if (curSize == 0) {
                    curSize += 1;
                    for (int[] dir : dirs) {
                        int nr = i + dir[0];
                        int nc = j + dir[1];
                        if (isIn(nr, nc, n, m)) {
                            if (!ids.contains(map[nr][nc])) {
                                ids.add(map[nr][nc]);
                                curSize += size[map[nr][nc]];
                            }
                        }
                    }
                }
                maxSize = Math.max(maxSize, curSize);
            }
        }

        System.out.println(maxSize);
    }
}
