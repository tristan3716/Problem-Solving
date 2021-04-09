import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	private static int n;
	private static int[] arr;
	private static int[] copiedArr;

	private static int temporarySwapValue;

	private static boolean isOrdered(int[] ref) {
		for (int i = 0; i < n; ++i) {
			if (ref[i] - ref[i + 1] != -1)
				return false;
		}
		return true;
	}

	private static void flip(int[] ref, int s, int e) {
		if (s == e) {
			return;
		} else if (s > e) {
			s ^= e;
			e ^= s;
			s ^= e;
		}

		for (int i = s, j = e; i < j; ++i, --j) {
			temporarySwapValue = ref[i];
			ref[i] = ref[j];
			ref[j] = temporarySwapValue;
		}
	}


	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		arr = new int[n + 1];

		for (int i = 1; i <= n; ++i) {
			arr[i] = sc.nextInt();
		}
		copiedArr = arr.clone();
		int[] history = new int[4];
		int hidx = 0;
		int pass = 0;
		
		for (; pass < 2; ++pass) {
			if (isOrdered(arr)) {
				break;
			}
			int s = -1, e = -1;
			FirstLoop:
			for (int i = 1; i <= n; ++i) {
				if (i != arr[i]) {
					s = i;
					for (int j = i + 1; j <= n; ++j) {
						if (s == arr[j]) {
							e = j;
							break FirstLoop;
						}
					}
				}
			}
			flip(arr, s, e);
			
			history[hidx++] = s;
			history[hidx++] = e;
		}
		if (!isOrdered(arr)) {
            /* Try Reverse */
			hidx = 0;
			pass = 0;
			
			for (; pass < 2; ++pass) {
				if (isOrdered(copiedArr)) {
					break;
				}
				int s = -1, e = -1;
				FirstLoop:
				for (int i = n; i >= 1; --i) {
					if (i != copiedArr[i]) {
						e = i;
						for (int j = i - 1; j >= 0; --j) {
							if (e == copiedArr[j]) {
								s = j;
								break FirstLoop;
							}
						}
					}
				}
				flip(copiedArr, s, e);
				
				history[hidx++] = s;
				history[hidx++] = e;
			}
		}
        /* Print Result */
		switch(pass) {
			case 0:
				System.out.println(1 + " " + 1);
				System.out.println(1 + " " + 1);
				break;
			case 1:
				System.out.println(history[0] + " " + history[1]);
				System.out.println(1 + " " + 1);
				break;
			case 2:
				System.out.println(history[0] + " " + history[1]);
				System.out.println(history[2] + " " + history[3]);
				break;
		}
		
		sc.close();
	}
}
