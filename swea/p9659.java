import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    private static final int p = 998244353;
    private static long[][] d = new long[55][55];
    private static int[][] terms = new int[55][3];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        Arrays.fill(d[0], 1);

        int t = Integer.parseInt(br.readLine()) + 1;
        for (int tc = 1; tc < t; tc++) {
            sb.append('#').append(tc).append(' ');
            int n = Integer.parseInt(br.readLine());

            for (int i = 2; i <= n; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                terms[i][0] = Integer.parseInt(st.nextToken());
                terms[i][1] = Integer.parseInt(st.nextToken());
                terms[i][2] = Integer.parseInt(st.nextToken());
            }

            int m = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine(), " ");

            for (int i = 0; i < m; i++) {
                int x = Integer.parseInt(st.nextToken());
                d[1][i] = x;
            }

            for (int i = 2; i <= n; i++) {
                int type = terms[i][0];
                int a = terms[i][1];
                int b = terms[i][2];
                for (int j = 0; j < m; j++) {
                    switch (type) {
                        case 1:
                            d[i][j] = d[a][j] + d[b][j];
                            break;
                        case 2:
                            d[i][j] = (a * d[b][j]) % p;
                            break;
                        case 3:
                            d[i][j] = (d[a][j] * d[b][j]) % p;
                            break;
                    }
                }
            }

            for (int i = 0; i < m; i++) {
                sb.append(d[n][i] % p).append(' ');
            }
            sb.deleteCharAt(sb.length()-1);
            sb.append('\n');
        }
        System.out.println(sb);
    }
}