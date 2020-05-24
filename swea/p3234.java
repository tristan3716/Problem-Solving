import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    private static int[] arr = new int[10];
    private static int[][] dp;

    private static int solve(final int current, final int left, final int right, int visit, final int n) {
        if (current == n) {
            return 1;
        } else if (dp[left][visit] != 0) {
            return dp[left][visit];
        } else {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                if ((visit & (1 << i)) != 0) continue;
                sum += solve(current + 1, left + arr[i], right, visit | (1 << i), n);

                if (left - (arr[i] + right) >= 0)
                    sum += solve(current + 1, left, right + arr[i], visit | (1 << i), n);
            }

            return (dp[left][visit] = sum);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        final int t = Integer.parseInt(br.readLine()) + 1;
        for (int tc = 1; tc != t; tc++) {
            final int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int sum = 0;
            for (int i = 0; i < 10; i++) {
                sum += arr[i];
            }
            dp = new int[sum + 1][(1 << n)];

            final int answer = solve(0, 0, 0, 0, n);

            sb.append('#').append(tc).append(' ').append(answer).append('\n');
        }

        System.out.print(sb);
    }
}