import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.HashSet;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		HashSet<String> s = new HashSet<>();
		ArrayList<String> x = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			s.add(br.readLine());
		}
		for (int i = 0; i < m; i++) {
			String d = br.readLine();
			if (s.contains(d)) {
				x.add(d);
			}
		}

		StringBuilder sb = new StringBuilder();
		sb.append(x.size()).append('\n');
		x.sort(null);
		for (String a : x) {
			sb.append(a).append('\n');
		}

		System.out.println(sb);
	}
}
