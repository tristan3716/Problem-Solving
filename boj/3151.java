import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());

        int[] in = new int[n];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            in[i] = Integer.parseInt(st.nextToken());
        }

        long count = 0;
        Arrays.sort(in);
        for (int i = 0; i < n; i++) {
            int left = i + 1;
            int right = n - 1;
            int last = n;
            int m = -in[i];
            while (left < right) {
                int sum = in[left] + in[right];
                if (sum < m) {
                    left++;
                } else if (sum == m) {
                    if (in[left] == in[right]) {
                        count += right - left;
                    } else {
                        if (last > right) {
                            last = right;
                            while(last - 1 >= 0 && in[last - 1] == in[right]) {
                                last--;
                            }
                        }
                        count += right - last + 1;
                    }
                    left++;
                } else {
                    right--;
                }
            }
        }

        System.out.println(count);
    }
}
