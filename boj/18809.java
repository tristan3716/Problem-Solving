package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p18809 {
    private static class Solver {
        private static int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

        int answer = 0;

        int[] rqr = new int[5000];
        int[] rqc = new int[5000];
        int rqb = 0;
        int rqe = 0;

        int[] gqr = new int[5000];
        int[] gqc = new int[5000];
        int gqb = 0;
        int gqe = 0;

        int n, m, g, r;
        int[][] map;
        int[][] copied;
        int[] gr = new int[10];
        int[] gc = new int[10];

        void cb(final int[] src, final int r) {
            int bits = (1 << r) - 1;

            while (bits < (1 << src.length)) {
                for (int i = 0; i < n + 2; i++) {
                    System.arraycopy(map[i], 0, copied[i], 0, m + 2);
                }

                rqb = 0;
                rqe = 0;
                gqb = 0;
                gqe = 0;

                int c = 1 << (src.length - 1);
                int idx = 0;
                while (c != 0) {
                    int cr = gr[src[idx]];
                    int cc = gc[src[idx]];
                    copied[cr][cc] = 1;
                    if ((c & bits) != 0) {
                        gqr[gqe] = cr;
                        gqc[gqe++] = cc;
                    } else {
                        rqr[rqe] = cr;
                        rqc[rqe++] = cc;
                    }
                    idx++;
                    c >>= 1;
                }

                int flower = 0;
                int time = 3;

                while ((rqb != rqe) && (gqb != gqe)) {
                    time++;
                    int rqs = rqe - rqb;
                    for (int i = 0; i < rqs; i++) {
                        int cr = rqr[rqb];
                        int cc = rqc[rqb++];

                        if (copied[cr][cc] == -1) continue;

                        for (int[] d : dir) {
                            int nr = cr + d[0];
                            int nc = cc + d[1];

                            if (copied[nr][nc] == 0) {
                                copied[nr][nc] = time;
                                rqr[rqe] = nr;
                                rqc[rqe++] = nc;
                            }
                        }
                    }

                    int gqs = gqe - gqb;
                    for (int i = 0; i < gqs; i++) {
                        int cr = gqr[gqb];
                        int cc = gqc[gqb++];

                        for (int[] d : dir) {
                            int nr = cr + d[0];
                            int nc = cc + d[1];

                            if (copied[nr][nc] == 0) {
                                copied[nr][nc] = 1;
                                gqr[gqe] = nr;
                                gqc[gqe++] = nc;
                            } else if (copied[nr][nc] == time) {
                                copied[nr][nc] = -1;
                                flower++;
                            }
                        }
                    }

                }

                answer = Math.max(flower, answer);

                int t = bits | ((bits & -bits) - 1);
                bits = (t + 1) | (((~t & -~t) - 1) >> (Integer.numberOfTrailingZeros(bits) + 1));
            }
        }

        void go(final int[] src, final int r) {
            int bits = (1 << r) - 1;

            while (bits < (1 << src.length)) {
                int[] arr = new int[r];
                for (int i = 1; i < r; i++) {
                    arr[i] = i;
                }
                int v = 0;
                int c = 1 << (src.length - 1);
                int idx = 0;
                while (c != 0) {
                    if ((c & bits) != 0) {
                        arr[v++] = src[idx];
                    }
                    idx++;
                    c >>= 1;
                }

                cb(arr, g);

                int t = bits | ((bits & -bits) - 1);
                bits = (t + 1) | (((~t & -~t) - 1) >> (Integer.numberOfTrailingZeros(bits) + 1));
            }
        }

        public Solver() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());

            map = new int[n + 2][m + 2];
            copied = new int[n + 2][m + 2];

            for (int i = 0; i < n + 2; i++) {
                map[i][0] = 1;
                map[i][m + 1] = 1;
            }
            for (int i = 0; i < m + 2; i++) {
                map[0][i] = 1;
                map[n + 1][i] = 1;
            }

            int available = 0;
            for (int i = 1; i < n + 1; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 1; j < m + 1; j++) {
                    int t = Integer.parseInt(st.nextToken());
                    if (t == 2) {
                        gr[available] = i;
                        gc[available++] = j;
                    } else if (t == 0) {
                        map[i][j] = 1;
                    }
                }
            }

            int[] arr = new int[available];
            for (int i = 1; i < available; i++) {
                arr[i] = i;
            }

            go(arr, g + r);

            System.out.println(answer);
        }
    }

    public static void main(String[] args) throws IOException {
        new Solver();
    }
}