import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int L;
    static int C;

    static StringBuilder sb = new StringBuilder();
    private static void printPassword(char[] chars) {
        for (char x : chars) {
            sb.append(x);
        }
        sb.append('\n');
    }
    private static void printAllPasswords(char[] chars, int k) {
        printAllPasswords(chars, new char[k], new boolean[chars.length], isVowel(chars), 0, 0, chars.length, 0, 0, chars.length);
        System.out.print(sb);
    }
    private static void printAllPasswords(
            final char[] chars,
            char[] result,
            boolean[] visited,
            final boolean[] isVowel,
            int current,
            int start,
            int left,
            int vowels,
            int consonants,
            final int max
    ) {
        if (current == L) {
            if (vowels >= 1 && consonants >= 2) {
                printPassword(result);
            }
        } else {
            for (int i = start; i < max; ++i) {
                if (vowels + left < 1 || consonants + left < 2) {
                    continue;
                }
                if (visited[i]) {
                    continue;
                }
                visited[i] = true;
                result[current] = chars[i];
                int nextVowels = isVowel[i] ? vowels + 1 : vowels;
                int nextConsonants = isVowel[i] ? consonants : consonants + 1;

                printAllPasswords(chars,
                        result,
                        visited,
                        isVowel,
                        current + 1,
                        i + 1,
                        left - 1,
                        nextVowels,
                        nextConsonants,
                        max);

                visited[i] = false;
            }
        }
    }

    private static boolean[] isVowel(char[] chars) {
        List<Character> vowelList = new ArrayList<>();
        boolean[] isVowel = new boolean[chars.length];
        vowelList.add('a');
        vowelList.add('e');
        vowelList.add('i');
        vowelList.add('o');
        vowelList.add('u');

        for (int i = 0; i < chars.length; i++) {
            if (vowelList.contains(chars[i])) {
                isVowel[i] = true;
            }
        }

        return isVowel;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        char[] chars = br.readLine().replaceAll(" ", "").toCharArray();
        Arrays.sort(chars);
        printAllPasswords(chars, L);
    }
}
