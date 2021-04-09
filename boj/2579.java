package p2579;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p2579 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] d = new int[n+1];
        int[] a = new int[n+1];
        for (int i = 1; i < n+1; i++) {
            a[i] = Integer.parseInt(br.readLine());
        }
        if (n == 1) {
            System.out.println(a[1]);
        } else if (n == 2) {
            System.out.println(a[1] + a[2]);
        } else {
            d[1] = a[1];
            d[2] = a[1] + a[2];
            for (int i = 3; i < n + 1; i++) {
                d[i] = Math.max(a[i - 1] + d[i - 3], d[i - 2]) + a[i];
            }
            System.out.println(d[n]);
        }
    }
}
