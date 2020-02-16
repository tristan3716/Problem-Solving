/**
 * @title 유닛 이동시키기 S1
 * @see https://www.acmicpc.net/problem/2194
 * @since 2020.02.15
 * @category simulation, bfs
 * @complexity O(n^2 * m^2) -> 236ms (n = 500, m = 10)
 * @description
 *      구현하면서 제법 고생한 문제.
 *      개념은 시작점으로 부터 bfs하여 도착점까지 도달한 거리.
 *      이동 주체의 크기가 A * B로 커졌으므로 충돌판정도 달리해야됨.
 *      좌측상단을 오브젝트의 좌표라 가정하고(문제에서 그렇게 주어짐)
 *      (0,0), (A,0), (0,B), (A,B) 만큼 차지한다는 생각을 하면서 문제를 풀면 됨
 *      이동 판정시 A*B가 아닌 한 줄만 해도 됨을 명심
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class IO {
    static BufferedReader br;
    static StringTokenizer st;

    IO() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    String next() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }

        return st.nextToken();
    }

    int nextInt() throws IOException {
        return Integer.parseInt(next());
    }
}

public class Main {
    private static class Pos{
        int r, c;
        int distance;

        public Pos(int r, int c, int distance) {
            this.r = r;
            this.c = c;
            this.distance = distance;
        }
    }

    static int map[][];
    static int n;
    static int m;
    static int a;
    static int b;

    static int targetR;
    static int targetC;

    public static void main(String[] args) throws IOException {
        IO io = new IO();
        n = io.nextInt();
        m = io.nextInt();
        a = io.nextInt();
        b = io.nextInt();
        int k = io.nextInt();
        map = new int[n+1][m+1];

        for (int i = 0; i < k; ++i) {
            map[io.nextInt()][io.nextInt()] = 1;
        }

        int startR = io.nextInt();
        int startC = io.nextInt();
        targetR = io.nextInt();
        targetC = io.nextInt();

        int res = bfs(startR, startC);
        System.out.println(res);
    }
    static boolean[][] visit;
    static int[][] directions = {{-1,0}, {1,0}, {0,-1}, {0,1}};
    private static int bfs(int startR, int startC) {
        visit = new boolean[n+1][m+1];
        int min = Integer.MAX_VALUE;
        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(startR, startC, 0));
        visit[startR][startC] = true;
        while (!q.isEmpty()) {
            Pos c = q.poll();

            for (int i = 0; i < directions.length; ++i) {
                int nr = c.r + directions[i][0];
                int nc = c.c + directions[i][1];

                if (isIn(nr, nc) && !visit[nr][nc] && isMovable(nr, nc, i)) {
                    if (isReach(nr, nc)) {
                        return c.distance + 1;
                    }
                    visit[nr][nc] = true;
                    q.offer(new Pos(nr, nc, c.distance + 1));
                }
            }
        }

        return -1;
    }
    private static boolean isReach(int nr, int nc) {
        return (nr == targetR && nc == targetC);
    }
    private static boolean isIn(int nr, int nc) {
        return (nr >= 1 && nc >= 1 && nr+a-1 <= n && nc+b-1 <= m);
    }

    private static boolean isMovable(int nr, int nc, int dir) {
        switch (dir){
            case 1:
                nr += (a-1);
            case 0:
                for (int i = 0; i < b; ++i) {
                    int cc = nc + i;
                    if (map[nr][cc] == 1){
                        return false;
                    }
                }
                break;
            case 3:
                nc += (b-1);
            case 2:
                for (int i = 0; i < a; ++i) {
                    int cr = nr + i;
                    if (map[cr][nc] == 1){
                        return false;
                    }
                }
                break;
        }

        return true;
    }
}