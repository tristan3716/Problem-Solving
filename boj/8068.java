import java.io.IOException;
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class IO {
	static BufferedReader br;
	static StringTokenizer st;

	IO() {
		br = new BufferedReader(new InputStreamReader(System.in));
	}

	String next() throws IOException {
		while (st == null || !st.hasMoreTokens()) {
			st = new StringTokenizer(br.readLine());
		}

		return st.nextToken();
	}

	int nextInt() throws IOException {
		return Integer.parseInt(next());
	}
}

public class Main {
	static int n;
	static int m;
	static int[][] arr;
	static int[] qr = new int[40005];
	static int[] qc = new int[40005];
	static int qb = 0;
	static int qe = 0;
	
	static int[][] dir = {{-1,0}, {1,0}, {0,1}, {0,-1}};

	static boolean isIn(int i, int j){
		return (i >= 0 && j >= 0 && i < n+2 && j < m+2);
	}

	static void bfs(int height){
		qb = 0;
		qe = 0;
		qr[qe] = 0;
		qc[qe++] = 0;
		arr[0][0] = height;
		while (qb != qe){
			int y = qr[qb];
			int x = qc[qb++];
			for (int [] d : dir){
				int ny = y + d[0];
				int nx = x + d[1];
				if (isIn(ny, nx) && arr[ny][nx] < height) {
					qr[qe] = ny;
					qc[qe++] = nx;
					arr[ny][nx] = height;
				}
			}
		}
	}
	public static void main(String[] args) throws IOException {
		IO io = new IO();
		n = io.nextInt();
		m = io.nextInt();
		arr = new int[n+2][m+2];
		for (int i = 1; i < n+1; ++i) {
			for (int j = 1; j < m+1; j++){
				arr[i][j] = io.nextInt();
			}
		}

		int sum = 0;
		for (int h = 1; h <= 10000; h++) {
			bfs(h);

			for (int i = 0; i < n+2; ++i) {
				for (int j = 0; j < m+2; ++j){
					if (arr[i][j] < h){
						sum += 1;
						arr[i][j] = h;
					}
				}
			}
		}
		System.out.println(sum);
	}
}