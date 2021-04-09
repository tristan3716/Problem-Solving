import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int x = Integer.parseInt(st.nextToken());
        int[] count = new int[4];
        int[] dp = new int[x + 1];
        for (int i = 0; i < 4; i++) {
            count[i] = Integer.parseInt(st.nextToken());
        }
        final int[] value = {1, 5, 10, 25};

        dp[0] = 1;
        int[] parents = new int[x + 1];
        int max = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = max; j >= 0; j--) {
                if (dp[j] != 0) {
                    for (int k = 1; k <= count[i]; k++) {
                        int next = j + value[i] * k;

                        if (next > x) {
                            break;
                        }
                        if (dp[next] < dp[j] + k) {
                            max = Math.max(max, next);
                            dp[next] = dp[j] + k;
                            parents[next] = next - value[i];
                        } else {
                            break;
                        }
                    }
                }
            }
        }

        int[] used = new int[26];
        if (dp[x] != 0) {
            while (x != 0) {
                used[x - parents[x]]++;
                x = parents[x];
            }
            System.out.printf("%d %d %d %d", used[1], used[5], used[10], used[25]);
        } else {
            System.out.print("0 0 0 0");
        }
    }
}
