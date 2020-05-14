package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p4354 {
    static void getPi(char[] p) {
        int pl = p.length;

        int[] pi = new int[pl];
        int j = 0;

        for (int i = 1; i < pl; i++) {
            while (j > 0 && p[i] != p[j]) {
                j = pi[j - 1];
            }
            if (p[i] == p[j]) {
                pi[i] = ++j;
            }
        }

        if (pl % (pl - pi[pl-1]) == 0) {
            System.out.println(pl / (pl - pi[pl-1]));
        } else {
            System.out.println(1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            char[] str = br.readLine().toCharArray();
            if (str.length == 1 && str[0] == '.') break;
            getPi(str);
        }
    }
}