package p4014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    private static boolean isBuildable(int line, boolean vertical) {
        int cr = vertical ? 0 : line;
        int cc = vertical ? line : 0;
        int dr = vertical ? 1 : 0;
        int dc = vertical ? 0 : 1;

        boolean trigger = false;
        int candidateLength = 1;

        int height = map[cr][cc];
        cr += dr;
        cc += dc;

        for (; cr < n && cc < n; cr += dr, cc += dc) {
            int diff = map[cr][cc] - height;

            if (Math.abs(diff) > 1) {
                return false;
            } else if (diff == 0) {
                candidateLength++;
            } else if (diff == 1) {
                if (trigger) {
                    return false;
                }
                if (candidateLength < x) {
                    return false;
                }
                candidateLength = 1;
                height = map[cr][cc];
            } else {
                if (trigger) {
                    return false;
                }
                trigger = true;
                candidateLength = 1;
                height = map[cr][cc];
            }

            if (trigger) {
                if (candidateLength >= x) {
                    trigger = false;
                    candidateLength = 0;
                }
            }
        }

        return !trigger;
    }

    private static int n, x;
    private static int[][] map = new int[20][20];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine()) + 1;
        for (int tc = 1; tc != t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());

            assert n >= 6;
            assert n <= 20;
            assert x >= 2;
            assert x <= 4;

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }


            int sum = 0;
            for (int i = 0; i < n; i++) {
                sum += isBuildable(i, true) ? 1 : 0;
                sum += isBuildable(i, false) ? 1 : 0;
            }

            sb.append('#').append(tc).append(' ').append(sum).append('\n');
        }
        System.out.println(sb);

    }
}
