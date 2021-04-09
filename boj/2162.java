import java.awt.Point;
import java.awt.geom.Line2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int[] p;
	static int[] sz;

	static int find(int x) {
		return p[x] == x ? x : (p[x] = find(p[x]));
	}

	static void union(int a, int b) {
		a = find(a);
		b = find(b);

		if (a != b) {
			p[a] = b;
			sz[b] += sz[a];
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		ArrayList<Line2D> l = new ArrayList<>();
		HashMap<Line2D, Integer> id = new HashMap<>();
		p = new int[n];
		sz = new int[n];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			Line2D line = new Line2D.Double(new Point(x1, y1), new Point(x2, y2));
			p[i] = i;
			sz[i] = 1;
			id.put(line, i);

			for (Map.Entry<Line2D, Integer> x : id.entrySet()) {
				if (x.getKey().intersectsLine(line)) {
					union(id.get(x.getKey()), i);
				}
			}
		}

		int[] cnt = new int[n];
		for (int i = 0; i < n; i++) {
			cnt[find(i)]++;
		}

		int gc = 0;
		int max = 0;
		for (int i = 0; i < n; i++) {
			if (cnt[i] != 0) {
				gc++;
				max = Math.max(max, cnt[i]);
			}
		}

		System.out.println(gc);
		System.out.println(max);
	}
}