import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int left = 0;
        int right = n - 1;
        int min = Integer.MAX_VALUE;
        int minLeft = 0;
        int minRight = 0;
        while (left < right) {
            int sum = arr[left] + arr[right];
            int diff = Math.abs(sum);
            if (min > diff) {
                min = diff;
                minLeft = left;
                minRight = right;
            }

            if (sum < 0) {
                left++;
            } else if (sum == 0) {
                break;
            } else {
                right--;
            }
        }

        System.out.println(arr[minLeft] + " " + arr[minRight]);
    }
}
