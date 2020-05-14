package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p1007 {
    static double res;
    static int n;

    static int[] rs = new int[20];
    static int[] cs = new int[20];
    static boolean[] sel = new boolean[20];

    static void rec(int count, int depth) {
        if (count == n >>> 1) {
            double x = 0, y = 0;
            for (int i = 0; i < n; i++) {
                if (sel[i]) {
                    x -= rs[i];
                    y -= cs[i];
                } else {
                    x += rs[i];
                    y += cs[i];
                }
            }
            res = Math.min(res, Math.sqrt(x * x + y * y));
        } else if (depth != n) {
            sel[depth] = true;
            rec(count + 1, depth + 1);
            sel[depth] = false;
            rec(count, depth + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            n = Integer.parseInt(br.readLine());
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                rs[i] = Integer.parseInt(st.nextToken());
                cs[i] = Integer.parseInt(st.nextToken());
            }

            res = Double.MAX_VALUE;
            rec(0, 0);

            System.out.printf("%.12f\n", res);
        }
    }
}