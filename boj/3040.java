import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] arr = new int[9];
		int sum = 0;
		for (int i = 0; i < 9; ++i) {
			arr[i] = Integer.parseInt(br.readLine());
			sum += arr[i];
		}

		int tSum = sum - 100;
		
		boolean[] result = new boolean[9];
		
		find:
		for (int i = 0; i < 8; i++) {
			for (int j = i+1; j < 9; j++) {
				if (arr[i] + arr[j] == tSum) {
					result[i] = true;
					result[j] = true;
					break find;
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 9; ++i) {
			if (!result[i]) {
				sb.append(arr[i]).append('\n');
			}
		}
		System.out.println(sb);
	}
}