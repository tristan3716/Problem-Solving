import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		DisjointSet ds = new DisjointSet(n);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			char t = st.nextToken().charAt(0);
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			switch (t) {
				case '0':
					ds.union(a, b);
					break;
				case '1':
					sb.append(ds.find(a) == ds.find(b) ? "YES" : "NO").append('\n');
			}
		}
		System.out.println(sb);
	}
}

class DisjointSet {
	int n;

	int[] parent;

	public DisjointSet(int n) {
		this.n = ++n;

		parent = new int[n];

		for (int i = 0; i < n; i++) {
			parent[i] = i;
		}
	}

	public int find(int x) {
		if (parent[x] == x) {
			return x;
		} else {
			return parent[x] = find(parent[x]);
		}
	}

	public void union(int p, int q) {
		p = find(p);
		q = find(q);
		if (p == q)
			return;
        parent[p] = q;
	}
}