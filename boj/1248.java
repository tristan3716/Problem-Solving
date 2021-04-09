import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static char convert(int x) {
        if (x < 0) {
            return '-';
        } else if (x == 0) {
            return '0';
        } else {
            return '+';
        }
    }

    private static boolean isPossible(final int[] candidates, final int current, final char[][] signs) {
        boolean possible = true;
        for (int i = 0; i < current + 1; i++) {
            int sum = 0;
            for (int j = 0, k = i; j < current + 1 - i; j++, k++) {
                sum += candidates[k];
                if (convert(sum) != signs[i][j]) {
                    possible = false;
                }
            }
        }
        return possible;
    }

    private static int[] suppose(final int sn, final int n, int current, int[] candidates, char[][] signs) {
        if (current == n) {
            return candidates;
        }

        for (int i = -10; i <= 10; i++) {
            candidates[current] = i;
            if (!isPossible(candidates, current, signs)) {
                continue;
            }
            int[] candidate = suppose(sn, n, current + 1, candidates, signs);
            if (candidate != null) {
                return candidate;
            }
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        final int sn = n * (n + 1) / 2;
        char[] str = br.readLine().toCharArray();

        char[][] signs = new char[n][n];
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                signs[i][j] = str[index++];
            }
        }

        int[] candidates = new int[n];
        int[] answer = suppose(sn, n, 0, candidates, signs);

        for (int x : answer) {
            System.out.printf("%d ", x);
        }
    }
}