import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] sizes = br.readLine().split(" ");
		int [] arr1 = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int [] arr2 = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		int [] arr = new int[Integer.parseInt(sizes[0]) + Integer.parseInt(sizes[1])];
		System.arraycopy(arr1, 0, arr, 0, arr1.length);
		System.arraycopy(arr2, 0, arr, arr1.length, arr2.length);
		
		Arrays.sort(arr);
		
		StringBuilder sb = new StringBuilder();
		for (int x : arr) {
			sb.append(x).append(' ');
		}
		System.out.println(sb);
	}
}