import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static void getPi(char[] p) {
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

		System.out.println(p.length - pi[p.length - 1]);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br.readLine();
		char[] str = br.readLine().toCharArray();
		getPi(str);
	}
}