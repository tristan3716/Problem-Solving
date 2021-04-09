package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class p5588 {
    private static long h(int r, int c) {
        return r * 1234567L + c;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashSet<Long> map = new HashSet<>();
        int m = Integer.parseInt(br.readLine());
        int[] sr = new int[m];
        int[] sc = new int[m];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        sr[0] = Integer.parseInt(st.nextToken());
        sc[0] = Integer.parseInt(st.nextToken());

        for (int i = 1; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            sr[i] = sr[0] - r;
            sc[i] = sc[0] - c;
        }

        int minR = Integer.MAX_VALUE;
        int maxR = Integer.MIN_VALUE;
        int minC = Integer.MAX_VALUE;
        int maxC = Integer.MIN_VALUE;
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int er = Integer.parseInt(st.nextToken());
            int ec = Integer.parseInt(st.nextToken());
            minR = Math.min(minR, er);
            maxR = Math.max(maxR, er);
            minC = Math.min(minC, ec);
            maxC = Math.max(maxC, ec);
            map.add(h(er, ec));
        }

        check:
        for (long x : map) {
            int tr = (int) (x / 1234567L);
            int tc = (int) (x % 1234567L);
            int dr = tr - sr[0];
            int dc = tc - sc[0];

            for (int i = 1; i < m; i++) {
                int nr = tr - sr[i];
                int nc = tc - sc[i];
                if (nr < minR || nc < minC || nr > maxR || nc > maxC) continue check;
                if (!map.contains(h(nr, nc))) continue check;
            }
            System.out.println(dr + " " + dc);
            break;
        }
    }
}