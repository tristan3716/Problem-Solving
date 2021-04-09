import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int encode(final String str) {
        int value = 0;
        for (char ch : str.toCharArray()) {
            value |= (1 << (ch - 'a'));
        }

        return value;
    }


    public static int bitNCR(final int value, final int r,
                              int[] input) {
        int bits = (1 << r) - 1;

        int highestBit = Integer.highestOneBit(value);
        int bitLength = (int) (Math.log10(highestBit)/Math.log10(2)) + 1;
        int maxReadable = 0;

        while (bits < (1 << bitLength)) {
            int newBit = 0;

            int c = 1 << (bitLength - 1);
            int idx = 0;
            while (c != 0) {
                if ((c & bits) != 0) {
                    newBit |= (1 << idx);
                }
                idx++;
                c = (c >> 1);
            }

            int readableWordCount = 0;

            for (int x : input) {
                int readableCharacterCount = Integer.bitCount(newBit | x);
                if (readableCharacterCount <= r) {
                    readableWordCount++;
                }
            }

            maxReadable = Math.max(readableWordCount, maxReadable);

            int t = bits | ((bits & -bits) - 1);
            bits = (t + 1) | (((~t & -~t) - 1) >> (Integer.numberOfTrailingZeros(bits) + 1));
        }

        return maxReadable;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        if (k < 5) {
            System.out.println(0);
            System.exit(0);
        }
        k -= 5;

        int[] input = new int[n];
        for (int i = 0; i < n; i++) {
            String str = br.readLine().replaceAll("[antic]", "");
            input[i] = encode(str);
        }

        int answer;
        if (k == 0) {
            answer = 0;
            for (int x : input) {
                if (x == 0) {
                    answer++;
                }
            }
        } else {
            int maxBit = encode("abcdefghijklmnopqrstuvwxyz".replaceAll("[antic]", ""));
            answer = bitNCR(maxBit, k, input);
        }

        System.out.println(answer);
    }
}
