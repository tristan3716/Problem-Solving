import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p16916 {
    static int[] getPi(char[] p) {
        int[] pi = new int[p.length];
        int j = 0;

        for (int i = 1; i < p.length; i++) {
            while (j > 0 && p[i] != p[j]) {
                j = pi[j - 1];
            }
            if (p[i] == p[j]) {
                pi[i] = ++j;
            }
        }

        return pi;
    }

    static int KMPSearcher(String text, String pattern) {
        char[] ct = text.toCharArray();
        char[] cp = pattern.toCharArray();
        int[] pi = getPi(cp);
        int j = 0;
        int count = 0;

        for (int i = 0; i < text.length(); i++) {
            while (j > 0 && ct[i] != cp[j]) {
                j = pi[j - 1];
            }
            if (ct[i] == cp[j]) {
                if (j == pattern.length() - 1) {
                    count++;
                    j = pi[j];
                }
                else {
                    j++;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String in = br.readLine();
        String pattern = br.readLine();

        int res = KMPSearcher(in, pattern);
        System.out.println(res == 0 ? 0 : 1);
    }
}
