import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int makeFullSet() {
        /* except (a n t i c) */
        int fullSet = (1 << 26) - 1;
        fullSet ^= 1;
        fullSet ^= (1 << 'n' - 'a');
        fullSet ^= (1 << 't' - 'a');
        fullSet ^= (1 << 'i' - 'a');
        fullSet ^= (1 << 'c' - 'a');

        return fullSet;
    }

    private static int convert(String raw) {
        char[] word = raw.replaceAll("[antic]", "").toCharArray();

        int bit = 0;
        for (char ch : word) {
            bit |= (1 << (ch - 'a'));
        }

        return bit;
    }

    private static boolean isAvailable(final int subSet, final int word) {
        return Integer.bitCount(subSet & word) >= Integer.bitCount(word);
    }

    private static int countAvailable(final int subSet, final int[] words) {
        int count = 0;
        for (int word : words) {
            if (isAvailable(subSet, word)) {
                count++;
            }
        }

        return count;
    }

    public static int solve(final int[] fullSet, final int[] words, final int k) {
        if (k == 0) {
            return countAvailable(0, words);
        }

        int bits = (1 << k) - 1;

        int answer = 0;
        while (bits < (1 << fullSet.length)) {
            int c = 1 << (fullSet.length - 1);
            int selected = 0;
            int idx = 0;
            while (c != 0) {
                if ((c & bits) != 0) {
                    selected |= fullSet[idx];
                }
                idx++;
                c >>= 1;
            }
            answer = Math.max(answer, countAvailable(selected, words));

            int t = bits | ((bits & -bits) - 1);
            bits = (t + 1) | (((~t & -~t) - 1) >> (Integer.numberOfTrailingZeros(bits) + 1));
        }
        return answer;
    }

    private static int[] toArray(final int bit) {
        int[] array = new int[Integer.bitCount(bit)];
        int idx = 0;
        for (int i = 0; i < 26; i++) {
            if ((bit & (1 << i)) != 0) {
                array[idx++] = (1 << i);
            }
        }

        return array;
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken()) - 5;

        int answer = 0;
        if (k >= 0) {
            int[] words = new int[n];
            for (int i = 0; i < n; i++) {
                String word = br.readLine();
                words[i] = convert(word);
            }

            int[] fullSet = toArray(makeFullSet());

            answer = solve(fullSet, words, k);
        }

        System.out.println(answer);
    }
}