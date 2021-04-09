package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p17142 {
    private static class LaboratorySolver {
        int [] qr;
        int [] qc;
        int qb;
        int qe;

        int n;
        int m;
        int [][] arr;
        int [][] copied;
        int empty;

        public void parse() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            arr = new int[n+2][n+2];
            copied = new int[n+2][n+2];

            qr = new int[2501];
            qc = new int[2501];
            for (int i = 0; i < n+2; ++i) {
                copied[0][i] = arr[0][i] = 1;
                copied[i][0] = arr[i][0] = 1;
                copied[n+1][i] = arr[n+1][i] = 1;
                copied[i][n+1] = arr[i][n+1] = 1;
            }
            for (int i = 1; i < n+1; ++i) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 1; j < n+1; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
        }
        final static int[][] directions = {{-1,0}, {1,0}, {0,1}, {0,-1}};

        int min;
        private void spread(int bits, int size) {
            for (int i = 1; i < n+1; ++i) { // init
                System.arraycopy(arr[i], 1, copied[i], 1, n+1);
            }

            qb = 0;
            qe = 0;
            int c = 1 << (size - 1);
            int idx = 0;
            while (c != 0) {
                if ((c & bits) != 0) {
                    int xr = vrr[idx][0];
                    int xc = vrr[idx][1];
                    copied[xr][xc] = 2;
                    qr[qe] = xr;
                    qc[qe++] = xc;
                }
                idx++;
                c >>= 1;
            }

            int max = 2;
            int spreadCount = 0;

            while (qb != qe) {
                int xr = qr[qb];
                int xc = qc[qb++];

                for (int [] direction : directions) {
                    int nr = xr + direction[0];
                    int nc = xc + direction[1];

                    if (copied[nr][nc] <= 0) {
                        if (copied[nr][nc] != -1) {
                            spreadCount++;
                        }
                        max = copied[xr][xc] + 1;
                        if (max >= min){
                            return;
                        }
                        if (spreadCount == empty) {
                            min = Math.min(min, max);
                            return;
                        }
                        copied[nr][nc] = max;

                        qr[qe] = nr;
                        qc[qe++] = nc;
                    }
                }
            }
        }

        int [][] vrr;
        public int solve() {
            List<int []> virus = new ArrayList<>();
            for (int i = 1; i < n+1; ++i) {
                for (int j = 1; j < n+1; j++) {
                    switch (arr[i][j]) {
                        case 0:
                            empty++;
                            break;
                        case 2:
                            virus.add(new int[] {i, j});
                            arr[i][j] = -1;
                            break;
                    }
                }
            }
            if (empty == 0) return 0; // only virus

            vrr = virus.toArray(new int[0][0]);

            min = Integer.MAX_VALUE;

            int bits = (1 << m) - 1;
            while (bits < (1 << vrr.length)) {
                spread(bits, vrr.length);

                int t = bits | ((bits & -bits) - 1);
                bits = (t + 1) | (((~t & -~t) - 1) >> (Integer.numberOfTrailingZeros(bits) + 1));

            }

            if (min == Integer.MAX_VALUE) return -1;
            else return min - 2;
        }
    }

    public static void main(String[] args) throws IOException {
        LaboratorySolver ls = new LaboratorySolver();
        ls.parse();
        int res = ls.solve();
        System.out.println(res);
    }
}