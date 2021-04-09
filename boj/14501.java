import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		int[] a = new int[n];
		int[] t = new int[n];
		int[] dp = new int[n+1];

		for (int i = 0; i < n; ++i) {
			t[i] = sc.nextInt();
			a[i] = sc.nextInt();
		}
		if (t[n-1] == 1) {
			dp[n-1] = a[n-1];
		}
		for (int i = n - 2; i >= 0; --i) {
			if (i + t[i] <= n) {
				dp[i] = Math.max(dp[i+1], dp[i+t[i]]+a[i]);
			}
			else {
				dp[i] = dp[i+1];
			}
		}
		System.out.println(dp[0]);
	}
}