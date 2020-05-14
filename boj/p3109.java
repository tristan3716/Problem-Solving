package src;

import java.io.*;

public class p3109 {
    private static boolean isIn(int i, int n) {
        return (i >= 0 && i < n);
    }
    private static boolean isReach(int j, int m) {
        return (j == m);
    }

    private static boolean reach = false;
    private static boolean dfs(int i, int j, boolean[][] visit, boolean[][] arr) {
        if (reach == true) {
            return false;
        }
        if (isReach(j, arr[0].length)) {
            reach = true;
            return true;
        }
        if (!isIn(i, arr.length) || visit[i][j] || arr[i][j]) {
            return false;
        }
        visit[i][j] = true;

        return (dfs(i-1, j+1, visit, arr) || dfs(i, j+1, visit, arr) || dfs(i+1, j+1, visit, arr));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] rc = br.readLine().split(" ");
        int r = Integer.parseInt(rc[0]);
        int c = Integer.parseInt(rc[1]);

        boolean [][] visit = new boolean[r][c];
        boolean [][] arr = new boolean[r][c];

        for (int i = 0; i < r; ++i) {
            String str = br.readLine();
            for (int j = 0; j < c; ++j) {
                if (str.charAt(j) == 'x') {
                    arr[i][j] = true;
                }
            }
        }

        int cnt = 0;
        for (int i = 0; i < r; ++i) {
            reach = false;
            if (dfs(i, 0, visit, arr)) {
                cnt++;
            }
        }

        System.out.println(cnt);
    }
}