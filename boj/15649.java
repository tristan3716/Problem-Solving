import java.util.Scanner;

public class Main {
	static StringBuilder sb;
	private static void swap(int [] arr, int i, int j) {
		int t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}
	public static void makeSubSet(int [] arr, int depth, int length, int m) {
		if (depth == m) {
			for (int i = 0; i < depth; ++i) {
				//System.out.printf("%d ", arr[i]);
				sb.append(arr[i]).append(" ");
			}
			//System.out.println();
			sb.append("\n");
			return;
		}
		int [] copy = new int[arr.length];
		for (int i = 0; i < arr.length; ++i) {
			copy[i] = arr[i];
		}
		for (int i = depth; i < length; ++i) {
			swap(copy, depth, i);
			makeSubSet(copy, depth+1, length, m);
		}
	}
	
	public static void main(String[] args) {
		sb = new StringBuilder();
		int m;
		int n;
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		int [] arr = new int[n];
		
		for (int i = 0; i < arr.length; ++i) {
			arr[i] = i+1;
		}

		makeSubSet(arr, 0, arr.length, m);
		System.out.println(sb);
	}
}