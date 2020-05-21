import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        final int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            sb.append('#').append(tc).append(' ');
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            long s = Integer.parseInt(st.nextToken());
            long t = Integer.parseInt(st.nextToken());
            long a = Integer.parseInt(st.nextToken());
            long b = Integer.parseInt(st.nextToken());

            if (t < s) {
                sb.append(-1).append('\n');
                continue;
            }
            if (s * b > t) {
                if ((t - s) % a == 0) {
                    sb.append((t - s) / a).append('\n');
                } else {
                    sb.append(-1).append('\n');
                }
                continue;
            }
            if (b == 1) {
                if ((t - s) % a == 0) {
                    sb.append((t - s) / a).append('\n');
                } else {
                    sb.append(-1).append('\n');
                }
                continue;
            }

            long min = Long.MAX_VALUE;

            long cb = 0;
            long factor = 1;
            long current = s;

            while(current <= t) {
				/* current : (((s + a)b + a)b ... + a) + a
						 ->  sb^p + a(hb^p + j*b^{p-1} + k*b^{p-2} + ... + l) */
                long diff = t - current;
                if (diff % a == 0) {
                    /* g : hb^p + j*b^{p-1} + k*b^{p-2} + ... + l */
                    long g = diff / a;

                    // b^p -> b^{p-1} -> b^{p-2} -> ... -> 0
                    // We'll get h, j, k, ..., l
                    long ca = 0;
                    for (long i = factor; i > 0; i /= b) {
                        ca += g / i;
                        g %= i;
                    }

                    min = Math.min(min, cb + ca);
                }

                cb++;
                factor *= b;
                current = s * factor;
            }

            if (min == Long.MAX_VALUE)
                sb.append(-1).append('\n');
            else
                sb.append(min).append('\n');
        }

        System.out.println(sb);
    }
}v