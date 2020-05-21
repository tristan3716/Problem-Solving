import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Solution {
    public static int parseInt(CharSequence s, int beginIndex, int endIndex, int radix) {
        int result = 0;
        int digit;
        for (int i = beginIndex; i < endIndex; result -= digit) {
            digit = Character.digit(s.charAt(i), radix);
            result *= radix;
            ++i;
        }

        return -result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine()) + 1;

        for (int tc = 1; tc != t; ++tc) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int z = n / 4;

            String raw = br.readLine();
            raw += raw.substring(0, z - 1);

            TreeSet<Integer> ts = new TreeSet<>();
            for (int i = 0; i < n; i++) {
                int x = parseInt(raw, i, i + z, 16);
                ts.add(-x);
            }

            sb.append('#').append(tc).append(' ').append(-(int) ts.toArray()[k - 1]).append('\n');
        }

        System.out.println(sb);
    }
}
