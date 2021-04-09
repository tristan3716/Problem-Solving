import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int makeCombinationByBits() {
		int minDiff = Integer.MAX_VALUE;

		int bits = (1 << r) - 1;
		final int length = n - 1;

		while (bits < (1 << length)) {
            if (minDiff == 0) break;
			int current = (1 << length);
			int id = 0;
			int ia = 0;
			int ib = 0;
			while (current != 0) {
				if ((current & bits) != 0) {
					setA[ia++] = source[id];
				} else {
					setB[ib++] = source[id];
				}
				id++;
				current >>= 1;
			}

			int sumA = 0;
			for (int i = 0; i < r; i++) {
				for (int j = i + 1; j < r; j++) {
					sumA += adjacentMatrix[setA[i]][setA[j]];
				}
			}
			int sumB = 0;
			for (int i = 0; i < r; i++) {
				for (int j = i + 1; j < r; j++) {
					sumB += adjacentMatrix[setB[i]][setB[j]];
				}
			}

			int diff = Math.abs(sumA - sumB);
			minDiff = Math.min(minDiff, diff);

			int t = (bits | ((bits & -bits) - 1));
			bits = ((t + 1) | (((~t & -~t) -1) >> (Integer.numberOfTrailingZeros(bits) + 1)));
		}

		return minDiff;
	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void parse() throws IOException {
		n = Integer.parseInt(br.readLine());
		r = n >> 1;
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				adjacentMatrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				adjacentMatrix[i][j] += adjacentMatrix[j][i];
			}
		}
	}

	final static int[] source = new int[20];
	final static int[] setA = new int[10];
	final static int[] setB = new int[10];
	public static int solve() {
		return makeCombinationByBits();
	}

	static int n;
	static int r;
	final static int[][] adjacentMatrix = new int[20][20];

	static StringBuilder result = new StringBuilder();
	public static void main(String[] args) throws IOException {
		for (int i = 0; i < 20; i++) { source[i] = i; }

		int t = 1;//Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; ++tc) {
			//result.append('#').append(tc).append(' ');
			parse();
			result.append(solve()).append('\n');

		}
		System.out.println(result);
	}
}