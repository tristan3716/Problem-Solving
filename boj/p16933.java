package src;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class p16933 {
    private static class Move {
        int y;
        int x;
        int k; // LeftHorseMove
        int b;
        int d;

        public Move(int r, int c, int k, int b, int d) {
            this.y = r;
            this.x = c;
            this.k = k;
            this.b = b;
            this.d = d;
        }
    }

    Scanner sc = new Scanner(System.in);
    int[][] arr;
    boolean[][][][] visit;
    int k;
    int w, h;

    public static void main(String[] args) {
        p16933 p = new p16933();
        p.solution();
    }
    int[][] MonkeyMove = {{-1,0}, {1,0}, {0,1}, {0,-1}};

    boolean isIn(int i, int j){
        return (i >= 0 && j >= 0 && i < h && j < w);
    }

    void solution(){
        StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[h][w];
        visit = new boolean[h][w][k+1][2];
        for (int i = 0; i < h; i++) {
            char [] a = sc.nextLine().toCharArray();
            for (int j = 0; j < w; j++) {
                arr[i][j] = a[j] - '0';
            }
        }

        Queue<Move> q = new LinkedList<>();
        q.offer(new Move(0, 0, k, 0, 0));
        visit[0][0][k][0] = true;

        while (!q.isEmpty()){
            Move c = q.poll();

            if (c.y == h-1 && c.x == w-1){
                System.out.println((c.b + 1));
                System.exit(0);
            }

            for (int [] d : MonkeyMove){
                int ny = c.y + d[0];
                int nx = c.x + d[1];

                if (isIn(ny, nx)){
                    if (!visit[ny][nx][c.k][c.d] && arr[ny][nx] == 0){
                        visit[ny][nx][c.k][c.d] = true;
                        q.offer(new Move(ny, nx, c.k, c.b + 1, ~c.d&1));
                    }
                }
            }
            if (c.k >= 1 && c.d == 0) {
                for (int [] d : MonkeyMove){
                    int ny = c.y + d[0];
                    int nx = c.x + d[1];

                    if (isIn(ny, nx)){
                        if (!visit[ny][nx][c.k][0] && arr[ny][nx] == 1) {
                            visit[ny][nx][c.k][0] = true;
                            q.offer(new Move(ny, nx, c.k - 1, c.b + 1, ~c.d&1));
                        }
                    }
                }
            }
            if (!visit[c.y][c.x][c.k][c.d]) {
                visit[c.y][c.x][c.k][c.d] = true;
                c.d = ~c.d&1;
                c.b += 1;
                q.offer(c);
            }
        }

        System.out.println(-1);
    }
}