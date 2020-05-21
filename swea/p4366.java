import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine()) + 1;

        next:
        for (int tc = 1; tc != t; tc++) {
            char[] ba = br.readLine().toCharArray();
            char[] ta = br.readLine().toCharArray();

            long bin = Long.parseLong(String.valueOf(ba), 2);
            long ter = Long.parseLong(String.valueOf(ta), 3);

            int factorBinary = 1;
            for (int bi = ba.length - 1; bi >= 0; bi--, factorBinary *= 2) {
                char currentB = ba[bi];
                for (char nextB = '0'; nextB <= '1'; nextB++) {
                    if (currentB != nextB) {
                        int diffB = nextB - currentB;
                        long tempBin = bin + diffB * factorBinary;

                        int factorTernary = 1;
                        for (int ti = ta.length - 1; ti >= 0; ti--, factorTernary *= 3) {
                            char currentT = ta[ti];
                            for (char nextT = '0'; nextT <= '2'; nextT++) {
                                if (currentT != nextT) {
                                    int diffT = nextT - currentT;
                                    long tempTer = ter + diffT * factorTernary;

                                    if (tempBin == tempTer) {
                                        sb.append('#').append(tc).append(' ').append(tempBin).append('\n');
                                        continue next;
                                    }
                                }
                            }
                            ta[ti] = currentT;
                        }
                    }
                }
            }
        }

        System.out.println(sb);
    }
}