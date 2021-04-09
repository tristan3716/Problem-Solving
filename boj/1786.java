import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int[] getPi(char[] pattern) {
		int[] pi = new int[pattern.length];
		int j = 0;

		for (int i = 1; i < pattern.length; i++) {
			while (j > 0 && pattern[i] != pattern[j]) {
				j = pi[j - 1];
			}
			if (pattern[i] == pattern[j]) {
				pi[i] = ++j;
			}
			else {
				pi[i] = 0;
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
					sb.append((i - pattern.length() + 2)).append(' ');
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

	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String in = br.readLine();
		String pattern = br.readLine();

		int res = KMPSearcher(in, pattern);
		System.out.println(res);
		if (res != 0) {
			System.out.println(sb);
		}
	}
}