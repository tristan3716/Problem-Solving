/**
 * @title 욕심쟁이 판다
 * @problem https://www.acmicpc.net/problem/1937
 * @submission https://www.acmicpc.net/source/28205855
 * @date 2021년 4월 9일
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        final int[][] matrix = new int[n][n];
        final int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] == 0) {
                    answer = Math.max(answer, solve(matrix, dp, n, i, j));
                }
            }
        }

        System.out.println(answer);
    }

    private static final int[][] dir = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    private static boolean isIn(final int r, final int c, final int n) {
        return r >= 0 && c >= 0 && r < n && c < n;
    }

    private static int solve(
            final int[][] matrix,
            final int[][] dp,
            final int n,
            final int r,
            final int c) {
        if (dp[r][c] != 0) {
            return dp[r][c];
        }

        int max = 0;
        for (int[] d : dir) {
            int nr = r + d[0];
            int nc = c + d[1];

            if (isIn(nr, nc, n) && matrix[r][c] < matrix[nr][nc]) {
                max = Math.max(max, solve(matrix, dp, n, nr, nc));
            }
        }

        return (dp[r][c] = max + 1);
    }
}