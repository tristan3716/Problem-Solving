package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p2230 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(a);

        int l = 0, r = 1;
        int diff = Integer.MAX_VALUE;
        for (; r < n; ) {
            int d = a[r] - a[l];
            if (d < m) {
                r++;
            } else if (d == m) {
                diff = m;
                break;
            } else {
                diff = Math.min(diff, d);
                l++;
            }
        }
        System.out.println(diff);
    }
}