import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 0;
        int sum = arr[right];
        int length = Integer.MAX_VALUE;
        for (; left != n;) {
            if (sum < s) {
                if (right + 1 >= n) {
                    break;
                }
                right++;
                sum += arr[right];
            } else {
                length = Math.min(length, right - left + 1);
                sum -= arr[left];
                left++;
            }
        }

        System.out.println(length == Integer.MAX_VALUE ? 0 : length);
    }
}