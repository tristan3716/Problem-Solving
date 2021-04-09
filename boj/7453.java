import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());

        int[] a = new int[n];
        int[] b = new int[n];
        int[] c = new int[n];
        int[] d = new int[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            a[i] = Integer.parseInt(st.nextToken());
            b[i] = Integer.parseInt(st.nextToken());
            c[i] = Integer.parseInt(st.nextToken());
            d[i] = Integer.parseInt(st.nextToken());
        }


        int[] A = new int[n * n];
        int ai = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A[ai++] = a[i] + b[j];
            }
        }
        int[] B = new int[n * n];
        int bi = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                B[bi++] = - c[i] - d[j];
            }
        }

        Arrays.sort(A);
        Arrays.sort(B);
        for (int i = 0; i < n * n; i++) {
            B[i] *= -1;
        }

        int aLeft = 0;
        int bLeft = 0;
        long count = 0;
        while (aLeft < n * n && bLeft < n * n) {
            int sum = A[aLeft] + B[bLeft];

            if (sum == 0) {
                int value = A[aLeft];
                long aCount = 0;
                long bCount = 0;
                while (aLeft < n * n && A[aLeft] == value) {
                    aCount++;
                    aLeft++;
                }
                while (bLeft < n * n && B[bLeft] == -value) {
                    bCount++;
                    bLeft++;
                }
                count += (aCount * bCount);
            } else if (sum < 0) {
                aLeft++;
            } else {
                bLeft++;
            }
        }

        System.out.println(count);
    }
}