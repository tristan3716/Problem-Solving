import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int k = Math.max(n, 3);

        long[] d = new long[k+1];
        d[1] = 1;
        d[2] = 3;
        for (int i = 3; i < n+1; i++) {
            d[i] = (d[i - 1] + 2 * d[i - 2]) % 10007;
        }

        System.out.println(d[n]);
    }
}