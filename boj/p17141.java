package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p17141 {
    private static class Solution {
        int[] qr;
        int[] qc;
        int qs;
        int qe;

        int n;
        int m;
        int[][] v;
        int[][] c;
        int empty;
        List<int[]> virus;
        public void parse() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            v = new int[n+2][n+2];
            c = new int[n+2][n+2];

            qr = new int[2501];
            qc = new int[2501];
            for (int i = 0; i < n+2; ++i) {
                c[0][i] = v[0][i] = 1;
                c[i][0] = v[i][0] = 1;
                c[n+1][i] = v[n+1][i] = 1;
                c[i][n+1] = v[i][n+1] = 1;
            }
            virus = new ArrayList<>();
            for (int i = 1; i < n+1; ++i) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 1; j < n+1; j++) {
                    int t = Integer.parseInt(st.nextToken());
                    switch (t) {
                        case 0:
                            empty++; break;
                        case 2:
                            virus.add(new int[]{i, j});
                            v[i][j] = 0; break;
                        case 1:
                            v[i][j] = t;
                    }
                }
            }
            empty = empty + virus.size() - m;
        }
        final static int[][] directions = {{-1,0}, {1,0}, {0,1}, {0,-1}};

        int min;
        private void spread(int bits, int size) {
            for (int i = 1; i < n+1; ++i) { // init
                System.arraycopy(v[i], 1, c[i], 1, n+1);
            }

            qs = 0;
            qe = 0;
            int c = 1 << (size-1);
            int idx = 0;
            while (c != 0) {
                if ((c & bits) != 0) {
                    int xr = vrr[idx][0];
                    int xc = vrr[idx][1];
                    this.c[xr][xc] = 2;
                    qr[qe] = xr;
                    qc[qe++] = xc;
                }
                idx++;
                c >>= 1;
            }

            int max;
            int spreadCount = 0;

            while (qs != qe) {
                int xr = qr[qs];
                int xc = qc[qs++];

                for (int [] direction : directions) {
                    int nr = xr + direction[0];
                    int nc = xc + direction[1];

                    if (this.c[nr][nc] == 0) {
                        spreadCount++;
                        max = this.c[xr][xc] + 1;
                        if (max >= min){
                            return;
                        }
                        if (spreadCount == empty) {
                            min = Math.min(min, max);
                            return;
                        }
                        this.c[nr][nc] = max;

                        qr[qe] = nr;
                        qc[qe++] = nc;
                    }
                }
            }
        }

        int getZeros(int n) {
            int num = 0;
            while ((n & 1) == 0) {
                num++;
                n >>= 1;
            }
            return num;
        }

        int [][] vrr;
        public int solve() {
            if (empty == 0) return 0; // only virus

            vrr = virus.toArray(new int[0][0]);

            min = Integer.MAX_VALUE;

            int bits = (1 << m) - 1;
            while (bits < (1 << vrr.length)) {
                spread(bits, vrr.length);

                int t = bits | ((bits & -bits) - 1);
                bits = (t + 1) | (((~t & -~t) -1) >> (getZeros(bits) + 1));
            }

            if (min == Integer.MAX_VALUE) return -1; // impossible
            else return min - 2; // possible
        }
    }

    public static void main(String[] args) throws IOException {
        Solution ls = new Solution();
        ls.parse();
        System.out.println(ls.solve());
    }
}