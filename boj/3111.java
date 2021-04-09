package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p3111 {
    private static class Censored {
        final char [] pattern;
        final char [] patternReverse;
        final int lastIndex;
        char [] source;
        char [] result;
        char [] resultReverse;

        public Censored () throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            pattern = br.readLine().toCharArray();
            patternReverse = new char[pattern.length];
            int ii = 0;
            for (int i = pattern.length - 1; i >= 0; i--) {
                patternReverse[i] = pattern[ii++];
            }
            source = br.readLine().toCharArray();
            lastIndex = pattern.length - 1;
            result = new char[source.length];
            resultReverse = new char[source.length];
        }
        public void solve() {
            int idx = 0;
            int idxR = source.length - 1;
            int idxS = 0;
            int idxSR = 0;
            boolean left = true;

            next:
            while (idx <= idxR) {
                if (left) {
                    result[idxS++] = source[idx++];

                    if (result[idxS-1] == pattern[lastIndex]) {
                        if (idxS - pattern.length < 0) continue;
                        for (int i = pattern.length - 1; i >= 0; --i) {
                            if (result[idxS - pattern.length + i] != pattern[i]) {
                                continue next;
                            }
                        }
                        idxS = idxS - pattern.length;
                        left = false;
                    }
                } else {
                    resultReverse[idxSR++] = source[idxR--];
                    if (resultReverse[idxSR-1] == patternReverse[lastIndex]) {
                        if (idxSR - pattern.length < 0) continue;
                        for (int i = pattern.length - 1; i >= 0; --i) {
                            if (resultReverse[idxSR - pattern.length + i] != patternReverse[i]) {
                                continue next;
                            }
                        }
                        idxSR = idxSR - pattern.length;
                        left = true;
                    }
                }
            }

            idxSR--;
            int size = resultReverse.length;
            next2:
            for (int k = 0; k < size; k++) {
                if (idxSR < 0) break;
                result[idxS++] = resultReverse[idxSR--];

                if (result[idxS - 1] == pattern[lastIndex]) {
                    if (idxS - pattern.length < 0) continue;
                    for (int i = pattern.length - 1; i >= 0; --i) {
                        if (result[idxS - pattern.length + i] != pattern[i]) {
                            continue next2;
                        }
                    }
                    idxS = idxS - pattern.length;
                }
            }            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < idxS; i++) {
                sb.append(result[i]);
            }
            System.out.print(sb);
        }
    }

    public static void main(String[] args) throws IOException {
        Censored cs = new Censored();
        cs.solve();
    }
}