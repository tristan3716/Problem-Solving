import java.io.IOException;
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

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
    static boolean isIn2(int i, int j){
        return (i >= 1 && j >= 1 && i < n+1 && j < m+1);
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
    static void bfs(int startR, int startC, boolean [][] visit, int height) {
        q.offer(new Point(startR, startC));

        while (!q.isEmpty()){
            Point cp = q.poll();

            for (int [] d : dir){
                int ny = cp.y + d[0];
                int nx = cp.x + d[1];
                if (isIn2(ny, nx) && !visit[ny][nx] && arr[ny][nx] > height) {
                    visit[ny][nx] = true;
                    q.offer(new Point(ny, nx));
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        //IO io = new IO();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()); //io.nextInt();
        //m = io.nextInt();
        m = n;
        arr = new int[n+2][m+2];
        for (int i = 1; i < n+1; ++i) {
            String[] str = br.readLine().split(" ");
            for (int j = 1; j < m+1; ++j){
                arr[i][j] = Integer.parseInt(str[j-1]);
            }
        }

        int max = 0;
        for (int h = 0; h <= 101; h++) {
            for (int i = 1; i < n+1; ++i) {
                for (int j = 1; j < m+1; ++j){
                    if (arr[i][j] < h){
                        arr[i][j] = h;
                    }
                }
            }

            int count = 0;
            boolean[][] visit = new boolean[n+2][m+2];
            for (int i = 1; i < n+1; ++i) {
                for (int j = 1; j < m+1; ++j){
                    if (!visit[i][j] && arr[i][j] > h){
                        bfs(i, j, visit, h);
                        count++;
                    }
                }
            }
            max = Math.max(max, count);
        }

        System.out.println(max);
    }
}
