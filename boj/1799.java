import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    private static int getA(int r, int c) {
        return r + c;
    }
    private static int getB(int r, int c, int n) {
        return r + (n - c - 1);
    }
    private static boolean isMarkAble(int r, int c, int n, boolean[] a, boolean[] b, int[][] arr) {
        return !a[getA(r, c)] && !b[getB(r, c, n)] && arr[r][c] == 1;
    }
    private static void mark(int r, int c, int n, boolean[] a, boolean[] b) {
        a[getA(r, c)] = true;
        b[getB(r, c, n)] = true;
    }
    private static void unmark(int r, int c, int n, boolean[] a, boolean[] b) {
        a[getA(r, c)] = false;
        b[getB(r, c, n)] = false;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        boolean[] a = new boolean[n * 2];
        boolean[] b = new boolean[n * 2];

        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve(0, n, 0, 0, a, b, arr);
        solve2(0, n, 0, 1, a, b, arr);

        System.out.println(maxCount + maxCount2);
    }

    private static int maxCount = 0;
    private static void solve(int cnt, int max, int cr, int cc, boolean[] a, boolean[] b, int[][] arr) {
        int nr;
        int nc;
        if (cr == max) {
            maxCount = Math.max(maxCount, cnt);
            return;
        }
        if (cc >= max - 2) {
            if (cr % 2 == 0) {
                nc = 1;
            } else {
                nc = 0;
            }
            nr = cr + 1;
        } else {
            nc = cc + 2;
            nr = cr;
        }

        if (isMarkAble(cr, cc, max, a, b, arr)) {
            mark(cr, cc, max, a, b);
            solve(cnt + 1, max, nr, nc, a, b, arr);
            unmark(cr, cc, max, a, b);
        }
        solve(cnt, max, nr, nc, a, b, arr);
    }
    private static int maxCount2 = 0;
    private static void solve2(int cnt, int max, int cr, int cc, boolean[] a, boolean[] b, int[][] arr) {
        int nr;
        int nc;
        if (cr == max) {
            maxCount2 = Math.max(maxCount2, cnt);
            return;
        }
        if (cc > max - 3) {
            nr = cr + 1;
            if (cr % 2 == 0) {
                nc = 0;
            } else {
                nc = 1;
            }
        } else {
            nc = cc + 2;
            nr = cr;
        }

        if (isMarkAble(cr, cc, max, a, b, arr)) {
            mark(cr, cc, max, a, b);
            solve2(cnt + 1, max, nr, nc, a, b, arr);
            unmark(cr, cc, max, a, b);
        }
        solve2(cnt, max, nr, nc, a, b, arr);
    }
}