/**
 * @title 유기농 배추 S1
 * @see https://www.acmicpc.net/problem/1012
 * @since 2020.02.10
 * @category graph, flood-fill, dfs, bfs
 * @complexity O(NM) -> 268ms
 * @description
 *      bfs/dfs를 통한 배추밭의 연결상태 조사
 * @see https://www.acmicpc.net/problem/2667
 */


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

class Pos {
    int r, c;

    public Pos(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

public class Main {
    static void fill(boolean[][] arr) {
        for (boolean[] x : arr) {
            Arrays.fill(x, false);
        }
    }
    static boolean isIn(final int r, final int c, final int n, final int m) {
        return (r >= 0 && c >= 0 && r < n && c < m);
    }
    static int[][] dir = {{-1,0}, {1,0}, {0,-1}, {0,1}};

    static boolean[][] arr = new boolean[51][51];
    static boolean[][] visit = new boolean[51][51];
    static void bfs(int i, int j, final int n, final int m) {
        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(i, j));
        while (!q.isEmpty()) {
            Pos c = q.poll();
            if (visit[c.r][c.c]) {
                continue;
            }
            visit[c.r][c.c] = true;
            for (int [] d : dir) {
                int ny = c.r + d[0];
                int nx = c.c + d[1];

                if (isIn(ny, nx, n, m) && arr[ny][nx] && !visit[ny][nx]) {
                    q.offer(new Pos(ny, nx));
                }
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int tc = sc.nextInt();
        while (tc-- != 0) {
            int m = sc.nextInt();
            int n = sc.nextInt();
            int k = sc.nextInt();
            for (int i = 0; i < k; ++i) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                arr[y][x] = true;
            }
            int cCnt = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (arr[i][j] == true && visit[i][j] == false) {
                        bfs(i, j, n, m);
                        cCnt++;
                    }
                }
            }
            System.out.println(cCnt);
            fill(arr);
            fill(visit);
        }
    }
}