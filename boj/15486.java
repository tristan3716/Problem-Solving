import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		int[] arr = new int[n];
		int[] date = new int[n];
		int[] dp = new int[n+1];

		for (int i = 0; i < n; ++i) {
			date[i] = sc.nextInt();
			arr[i] = sc.nextInt();
		}
		if (date[n-1] == 1) {
			dp[n-1] = arr[n-1];
		}
		for (int i = n - 2; i >= 0; --i) {
			if (i + date[i] <= n) {
				dp[i] = Math.max(dp[i+1], dp[i+date[i]]+arr[i]);
			}
			else {
				dp[i] = dp[i+1];
			}
		}
		System.out.println(dp[0]);
	}
}