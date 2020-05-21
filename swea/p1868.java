import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    final static int m = 302;
    final static char mine = 1;
    final static char x = 2;
    final static char near = 3;
    static char[][] map = new char[m][m];
    final static char[] wall = new char[m];
    final static char[] empty = new char[m];

    private static void init() throws IOException {
        n = Integer.parseInt(br.readLine()) + 1;
        for (int i = 1; i < n; i++) {
            System.arraycopy(empty, 0, map[i], 1, n);
        }
        for (int i = 1; i < n; i++) {
            char[] x = br.readLine().toCharArray();
            for (int j = 1; j < n; j++) {
                if (x[j-1] == '*') {
                    map[i][j] = mine;
                    for (int[] d : dir) {
                        int nr = i + d[0];
                        int nc = j + d[1];
                        if (map[nr][nc] == 0) {
                            map[nr][nc] = near;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < n+1; i++) {
            map[i][0] = x;
            map[i][n] = x;
        }
        System.arraycopy(wall, 0, map[0], 0, m);
        System.arraycopy(wall, 0, map[n], 0, m);
    }
    final private static int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}, {-1, -1}, {1, 1}, {-1, 1}, {1, -1}};
    private static void dfsEmpty(int r, int c) {
        if (map[r][c] == 0) {
            map[r][c] = x;
            for (int[] d : dir) {
                int nr = r + d[0];
                int nc = c + d[1];
                if (map[nr][nc] != x) {
                    dfsEmpty(nr, nc);
                }
            }
        } else {
            map[r][c] = x;
        }
    }
    private static int dfs() {
        int count = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                if (map[i][j] == 0) {
                    ++count;
                    dfsEmpty(i, j);
                }
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                if (map[i][j] == near) {
                    ++count;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        Arrays.fill(wall, x);
        System.arraycopy(wall, 0, map[0], 0, m);
        final int t = Integer.parseInt(br.readLine()) + 1;
        for (int tc = 1; tc != t; ++tc) {
            sb.append('#').append(tc).append(' ');
            init();
            sb.append(dfs()).append('\n');
        }
        System.out.println(sb);
    }
}