import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();

    private static int d, w, k;
    private static boolean[][] map = new boolean[20][13];
    private static boolean[][] raw = new boolean[20][13];

    private static boolean init() {
        minimumInjectionCount = Integer.MAX_VALUE;
        for (int i = 0; i < 20; i++) {
            Arrays.fill(map[i], false);
        }

        return true;
    }

    private static boolean testEachLine(final int i) {
        boolean old = map[i][0];
        int streak = 1;
        for (int j = 1; j < d; j++) {
            if (old != map[i][j]) {
                if (j > d - k) {
                    return false;
                }
                old = !old;
                streak = 1;
            } else {
                if (++streak == k) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean isPass() {
        for (int i = 0; i < w; i++) {
            boolean result = testEachLine(i);

            if (!result) return false;
        }

        return true;
    }

    private static void injection(final int x, final boolean type) {
        for (int i = 0; i < w; i++) {
            map[i][x] = type;
        }
    }

    private static void restore(final int x) {
        for (int i = 0; i < w; i++) {
            map[i][x] = raw[i][x];
        }
    }

    private static int minimumInjectionCount = Integer.MAX_VALUE;
    private static void testRecursive(final int cd, final int injectionCount) {
        if (injectionCount >= minimumInjectionCount) return;
        if (cd == d) {
            final boolean testResult = isPass();
            if (testResult) {
                minimumInjectionCount = injectionCount;
            }
            return;
        }

        testRecursive(cd + 1, injectionCount);

        injection(cd, false);
        testRecursive(cd + 1, injectionCount + 1);

        injection(cd, true);
        testRecursive(cd + 1, injectionCount + 1);

        restore(cd);
    }

    public static void main(String[] args) throws IOException {
        final int t = Integer.parseInt(br.readLine()) + 1;
        for (int tc = 1; tc < t && init(); tc++) {
            st = new StringTokenizer(br.readLine(), " ");
            d = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());


            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < w; j++) {
                    map[j][i] = (st.nextToken().charAt(0) == '1');
                }
            }

            if (k == 1) {
                sb.append('#').append(tc).append(' ').append(0).append('\n');
                continue;
            }

            for (int i = 0; i < w; i++) {
                System.arraycopy(map[i], 0, raw[i], 0, d);
            }

            testRecursive(0, 0);

            sb.append('#').append(tc).append(' ').append(minimumInjectionCount).append('\n');
        }

        System.out.print(sb);
    }
}