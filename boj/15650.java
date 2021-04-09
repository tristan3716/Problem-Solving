import java.util.Scanner;

public class Main {
	static StringBuilder sb = new StringBuilder();
    static int n;
    static int k;
    static int [] source;
    static boolean [] visited;
	private static void print(int [] arr) {
		for (int x : arr)
			sb.append(x).append(" ");
		sb.append("\n");
	}
	public static void makePermutation() {
		makePermutation(new int[k], 0, 0);
	}
	public static void makePermutation(int [] temp, int current, int a) {
		if (current == k) {
			print(temp);
			return;
		}
		else {
			for (int i = a; i < n; ++i) {
				if (!visited[i]) {
	                temp[current] = source[i];
					visited[i] = true;
	                makePermutation(temp, current+1, i);
					visited[i] = false;
				}
			}
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		k = sc.nextInt();
		source = new int[n];
		visited = new boolean[source.length];
		for (int i = 0; i < n; ++i) {
			source[i] = i + 1;
		}
		makePermutation();
		System.out.println(sb);
		sc.close();
	}
}