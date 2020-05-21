import java.util.Scanner;

public class Solution {
    static Scanner sc = new Scanner(System.in);

    static int n, m;
    static int[][] map = new int[1001][1001];

    private static void parse() {
        n = sc.nextInt() + 1;
        m = sc.nextInt() + 1;

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                map[i][j] = sc.nextInt();
            }
        }
    }

    private static void sum() {
        for (int i = 2; i < n; i++) {
            map[i][1] += map[i-1][1];
            map[1][i] += map[1][i-1];
        }
        for (int i = 2; i < n; i++) {
            for (int j = 2; j < n; j++) {
                map[i][j] = map[i][j-1] + map[i-1][j] + map[i][j] - map[i-1][j-1];
            }
        }
    }

    private static int getSum(int r, int c) {
        return map[r][c] - map[r][c-m+1] - map[r-m+1][c] + map[r-m+1][c-m+1];
    }

    private static int findMax() {
        int max = 0;
        for (int i = m-1; i < n; i++) {
            for (int j = m-1; j < n; j++) {
                int t = getSum(i, j);
                max = t > max ? t : max;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        int T = sc.nextInt() + 1;
        for (int tc = 1; tc != T; ++tc) {

            parse();
            sum();
            int res = findMax();

            System.out.printf("#%d %d\n", tc, res);
        }
    }
}