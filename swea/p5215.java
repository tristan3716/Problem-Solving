import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int[] sco = new int[25];
        int[] cal = new int[25];
        int[] d = new int[10005];
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            Arrays.fill(d, 0);
            for (int i = 1; i <= n; i++) {
                st = new StringTokenizer(br.readLine());
                sco[i] = Integer.parseInt(st.nextToken());
                cal[i] = Integer.parseInt(st.nextToken());
                for (int j = l; j >= 0; j--) {
                    if (cal[i] <= j) {
                        d[j] = Math.max(d[j],
                                d[j - cal[i]] + sco[i]);
                    }
                }
            }

            sb.append('#').append(tc).append(' ').append(d[l]).append('\n');
        }
        System.out.print(sb);
    }
}