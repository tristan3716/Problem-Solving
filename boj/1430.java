import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Main {
	static int n, r, ex, ey;
	static double d;
	static int rr;

	private static boolean near(Tower a, Tower b) {
		int dx = a.r - b.r;
		int dy = a.c - b.c;
		int distance = dx * dx + dy * dy;
		return distance <= rr;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken()) + 1;
		r = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		ex = Integer.parseInt(st.nextToken());
		ey = Integer.parseInt(st.nextToken());
		rr = r * r;

		Tower[] towers = new Tower[n];
		towers[0] = new Tower(ex, ey);

		for (int i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			towers[i] = new Tower(x, y);
		}

		boolean[][] edge = new boolean[101][101];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i != j) {
					if (near(towers[i], towers[j])) {
						edge[j][i] = edge[i][j] = true;
					}
				}
			}
		}

		Queue<Integer> q = new LinkedList<>();
		q.offer(0);
		double res = 0;
		while (!q.isEmpty()) {
			int qs = q.size();
			for (int i = 0; i < qs; i++) {
				int c = q.poll();

				for (int next = 1; next < n; next++) {
					if (edge[c][next]) {
						if (!towers[next].v) {
							towers[next].v = true;
							edge[next][c] = edge[c][next] = false;
							q.offer(next);
							res += d;
						}
					}
				}
			}
			d /= 2;
		}

		BigDecimal bd = new BigDecimal(res);
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		System.out.print(bd);
	}
}

class Tower {
	int r, c;
	boolean v = false;

	public Tower(int r, int c) {
		this.r = r;
		this.c = c;
	}
}