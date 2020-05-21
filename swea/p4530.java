import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine()) + 1;
        for (int tc = 1; tc < t; tc++) {
            sb.append('#').append(tc).append(' ');
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            char[] a = st.nextToken().toCharArray();
            char[] b = st.nextToken().toCharArray();
            for (int i = 0; i < a.length; i++) {
                if (a[i] > '3') a[i]--;
            }
            for (int i = 0; i < b.length; i++) {
                if (b[i] > '3') b[i]--;
            }

            long al = Long.parseLong(String.valueOf(a), 9);
            long bl = Long.parseLong(String.valueOf(b), 9);

            sb.append((Math.abs(bl - al)) + (a[0] == '-' ? (b[0] != '-' ? -1 : 0) : 0)).append('\n');
        }
        System.out.println(sb);
    }
}