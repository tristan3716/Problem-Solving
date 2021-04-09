import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static class IO {
		public BufferedReader br;
		public StringTokenizer st;

		public IO () {
			br = new BufferedReader(new InputStreamReader(System.in));
		}

		public String next() throws IOException {
			while (st == null || !st.hasMoreTokens()) {
				st = new StringTokenizer(br.readLine());
			}
			return st.nextToken();
		}

		public int nextInt() throws IOException {
			return Integer.parseInt(next());
		}
	}
	
	public static void main(String[] args) throws IOException {
		IO io = new IO();
		int n = io.nextInt();
		int m = io.nextInt();

		Map<Integer, String> dict = new HashMap<>();
		Map<String, Integer> idict = new HashMap<>();
		for (int i = 1; i <= n; ++i) {
			String name = io.next();
			dict.put(i, name);
			idict.put(name, i);
		}
		for (int i = 0; i < m; ++i) {
			String str = io.next();
			if (str.charAt(0) >= '0' && str.charAt(0) <= '9') {
				System.out.println(dict.get(Integer.parseInt(str)));
			}
			else {
				System.out.println(idict.get(str));
			}
		}
	}
}