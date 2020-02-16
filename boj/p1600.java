/**
 * @title 말이 되고픈 원숭이 G5
 * @see https://www.acmicpc.net/problem/1600
 * @since 2020.02.09
 * @category bfs
 * @complexity O(12 * n^2) -> 1196ms (n = 200)
 * @description
 *      심플하게 12가지 이동방향을 가지는 bfs
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Move {
    int y;
    int x;
    int k; // LeftHorseMove
    int b;

    public Move(int r, int c, int k, int b) {
        this.y = r;
        this.x = c;
        this.k = k;
        this.b = b;
    }
}

public class Main {
    Scanner sc = new Scanner(System.in);
    int[][] arr;
    boolean[][][] visit;
    int k;
    int w, h;
    public static void main(String[] args) {
        Main main = new Main();
        main.solution();
    }
    int[][] MonkeyMove = {{-1,0}, {1,0}, {0,1}, {0,-1}};
    int[][] HorseMove = {{-2,-1},        {-2, 1},
            {-1,-2},                        {-1, 2},
            /*  */
            { 1,-2},                        { 1, 2},
            { 2,-1},        { 2, 1}};

    boolean isIn(int i, int j){
        return (i >= 0 && j >= 0 && i < h && j < w) && (arr[i][j] != 1);
    }

    void solution(){
        k = sc.nextInt();
        w = sc.nextInt();
        h = sc.nextInt();
        arr = new int[h][w];
        visit = new boolean[h][w][k+4];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        Queue<Move> q = new LinkedList<>();
        q.offer(new Move(0, 0, k, 0));
        boolean reach = false;

        while (!q.isEmpty()){
            Move c = q.poll();

            if (c.y == h-1 && c.x == w-1){
                reach = true;
                System.out.println(c.b);
                break;
            }
            if (visit[c.y][c.x][c.k]) {
                continue;
            }
            visit[c.y][c.x][c.k] = true;

            for (int [] d : MonkeyMove){
                int ny = c.y + d[0];
                int nx = c.x + d[1];

                if (isIn(ny, nx)){
                    if (!visit[ny][nx][c.k]){
                        q.offer(new Move(ny, nx, c.k, c.b + 1));
                    }
                }
            }
            if (c.k >= 1) {
                for (int [] d : HorseMove){
                    int ny = c.y + d[0];
                    int nx = c.x + d[1];

                    if (isIn(ny, nx)){
                        if (!visit[ny][nx][c.k]) {
                            q.offer(new Move(ny, nx, c.k - 1, c.b + 1));
                        }
                    }
                }
            }
        }
        if (!reach){
            System.out.println(-1);
        }
    }
}
