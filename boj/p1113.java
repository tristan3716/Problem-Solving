/**
 * @title 수영장 만들기 P5
 * @see https://www.acmicpc.net/problem/1113
 * @since 2020.02.09
 * @category bfs, simulation
 * @complexity O( 9(height) * n^2 (bfs) ) -> 108ms ( n = 50 )
 * @description
 *      땅의 높이를 1씩 높이면서 bfs,
 *      땅보다 낮으면서 물이 차지 않은 곳 = 주위가 땅으로 막혀있는 곳의 넓이를 counting
 */

import java.io.IOException;
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

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

class Point {
    int y;
    int x;

    public Point(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

public class Main {
    static int n;
    static int m;
    static int[][] arr;
    static Queue<Point> q = new LinkedList<>();
    static int[][] dir = {{-1,0}, {1,0}, {0,1}, {0,-1}};

    static boolean isIn(int i, int j){
        return (i >= 0 && j >= 0 && i < n+2 && j < m+2);
    }

    static void bfs(int height){
        q.offer(new Point(0, 0));
        arr[0][0] = height;
        while (!q.isEmpty()){
            Point cp = q.poll();
            for (int [] d : dir){
                int ny = cp.y + d[0];
                int nx = cp.x + d[1];
                if (isIn(ny, nx) && arr[ny][nx] < height) {
                    q.offer(new Point(ny, nx));
                    arr[ny][nx] = height;
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        IO io = new IO();
        n = io.nextInt();
        m = io.nextInt();
        arr = new int[n+2][m+2];
        for (int i = 1; i < n+1; ++i) {
            String str = io.next();
            for (int j = 1; j < m+1; ++j){
                arr[i][j] = str.charAt(j-1) - '0';
            }
        }

        int sum = 0;
        for (int h = 1; h <= 9; h++) {
            bfs(h);

            for (int i = 0; i < n+2; ++i) {
                for (int j = 0; j < m+2; ++j){
                    if (arr[i][j] < h){
                        sum += 1;
                        arr[i][j] += 1;
                    }
                }
            }
        }
        System.out.println(sum);
    }
}
