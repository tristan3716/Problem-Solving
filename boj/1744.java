import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        for (int i = 0; i < n - 1;) {
            if (arr[i] < 0 && arr[i + 1] <= 0) {
                arr[i] = arr[i] * arr[i + 1];
                arr[i + 1] = 0;
                i += 2;
            } else {
                break;
            }
        }

        for (int i = n - 1; i >= 1;) {
            if (arr[i] > 1 && arr[i - 1] > 1) {
                arr[i] = arr[i] * arr[i - 1];
                arr[i - 1] = 0;
                i -= 2;
            } else {
                break;
            }
        }

        int answer = Arrays.stream(arr).sum();
        System.out.println(answer);
    }
}
