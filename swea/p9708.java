import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t ; tc++) {
            sb.append('#').append(tc).append(' ');
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n];
            int[] D = new int[1000001];
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arr);
            D[arr[n - 1]] = 1;
            int N = arr[n-1];

            for (int i = n - 2; i >= 0; i--) {
                int max = 0;
                for (int j = arr[i]; j <= N; j += arr[i]) {
                    max = Math.max(max, D[j] + 1);
                }
                D[arr[i]] = max;
            }
            int res = 0;
            for (int i = 0; i <= N; ++i) {
                res = Math.max(res, D[i]);
            }
            sb.append(res).append('\n');
        }
        System.out.println(sb);
    }
}