package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p1701 {
    static int getPi(int start, char[] p) {
        int[] pi = new int[p.length];
        int j = start;
        int max = 0;

        for (int i = start + 1; i < p.length; i++) {
            while (j > start && p[i] != p[j]) {
                j = pi[j - 1] + start;
            }
            if (p[i] == p[j]) {
                pi[i] = ++j - start;
                max = Math.max(pi[i], max);
            }
        }

        return max;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] str = br.readLine().toCharArray();
        int n = str.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (ans >= n - i) {
                break;
            }
            ans = Math.max(ans, getPi(i, str));
        }
        System.out.println(ans);
    }
}