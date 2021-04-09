import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int maxValue = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            char[] in = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                arr[i][j] = in[j] - '0';
            }
        }

        solve(0, 0, 0, 0, 0, new boolean[n][m], arr, n, m);

        System.out.println(maxValue);
    }

    private static void solve(int depth, int prev, int cs, int cr, int cc, boolean[][] det, int[][] arr, int n, int m) {
        if (cc == m) {
            solve(depth + 1, 0, cs, cr + 1, 0, det, arr, n, m);
        } else if (cr == n) {
            maxValue = Math.max(maxValue, cs);
        } else if (!det[cr][cc]) {
            int cv = arr[cr][cc];
            det[cr][cc] = true;
            int sum = cv;
            int i = cr + 1;
            for (; i < n; i++) {
                if (!det[i][cc]) {
                    det[i][cc] = true;
                    sum = sum * 10 + arr[i][cc];
                    solve(depth + 1, sum, cs + sum, cr, cc + 1, det, arr, n, m);
                } else {
                    break;
                }
            }
            for (int j = cr; j < i; j++) {
                det[j][cc] = false;
            }

            i = cc + 1;
            sum = cv;
            for (; i < m; i++) {
                if (!det[cr][i]) {
                    det[cr][i] = true;
                    sum = sum * 10 + arr[cr][i];
                    solve(depth + 1, sum, cs + sum, cr, cc + 1, det, arr, n, m);
                } else {
                    break;
                }
            }
            for (int j = cc; j < i; j++) {
                det[cr][j] = false;
            }

            solve(depth + 1, cv, cs + cv, cr, cc + 1, det, arr, n, m);
        } else {
            solve(depth + 1, 0, cs, cr, cc + 1, det, arr, n, m);
        }
    }
}