import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int[] arr = new int[1005];
        for (int tc = 1; tc <= t; ++tc) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            for (int i = 0; i < n; ++i) {
                arr[i] = sc.nextInt();
            }
            int maxSum = -1;
            Arrays.sort(arr, 0, n);
            for (int i = 0; i < n-1; ++i) {
                for (int j = i+1; j < n; ++j) {
                    int sum = arr[i]+arr[j];
                    if (sum <= m) {
                        if (maxSum < sum) {
                            maxSum = sum;
                        }
                    }
                    else {
                        break;
                    }
                }
            }
            sb.append("#").append(tc).append(" ").append(maxSum).append("\n");
        }
        System.out.print(sb);
        sc.close();
    }
}