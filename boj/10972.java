import java.util.Arrays;
import java.util.Scanner;

public class Main {
	private static void swap(int [] arr, int i, int j) {
		int t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}
	private static void reverse(int [] arr, int i, int j) {
		for (;i < j; i++, --j) {
			swap(arr, i, j);
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int [] arr = new int[n];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = sc.nextInt();
		}
		int i = arr.length - 1;

		if (i == 0) {
			System.out.println(-1);
			System.exit(0);
		}
		while (true) {
			int j = i;
			--i;
			if (arr[i] < arr[j]) {
				int k = arr.length;
				while (!(arr[i] < arr[--k]));
				swap(arr, i, k);
				reverse(arr, j, arr.length-1);
				for (int x : arr) {
					System.out.print(x + " ");
				}
				break;
			}
			if (i == 0) {
				System.out.println(-1);
				break;
			}
		}
	}
}