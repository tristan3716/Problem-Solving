import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int[][] spread = new int[5][5];
    private static final double[][][] spreadRatio = {
            {/* LEFT  */
                    {   0,    0, 0.02,    0,    0},
                    {   0, 0.10, 0.07, 0.01,    0},
                    {0.05,    0,    0,    0,    0},
                    {   0, 0.10, 0.07, 0.01,    0},
                    {   0,    0, 0.02,    0,    0},
            },
            {/* RIGHT */
                    {   0,    0, 0.02,    0,    0},
                    {   0, 0.01, 0.07, 0.10,    0},
                    {   0,    0,    0,    0, 0.05},
                    {   0, 0.01, 0.07, 0.10,    0},
                    {   0,    0, 0.02,    0,    0},
            },
            {/* DOWN  */
                    {   0,    0,    0,    0,    0},
                    {   0, 0.01,    0, 0.01,    0},
                    {0.02, 0.07,    0, 0.07, 0.02},
                    {   0, 0.10,    0, 0.10,    0},
                    {   0,    0, 0.05,    0,    0},
            },
            {/* UP    */
                    {   0,    0, 0.05,    0,    0},
                    {   0, 0.10,    0, 0.10,    0},
                    {0.02, 0.07,    0, 0.07, 0.02},
                    {   0, 0.01,    0, 0.01,    0},
                    {   0,    0,    0,    0,    0},
            },
    };

    private static class Pos {
        int r;
        int c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }

        private static final int[][] DIRECTIONS = {
                {/* LEFT  */  0, -1},
                {/* RIGHT */  0,  1},
                {/* DOWN  */  1,  0},
                {/* UP    */ -1,  0}
        };

        public int getNextType(final int n) {
            int half = n / 2;
            if (this.r > this.c) {
                if (n - this.r - 1 <= this.c) {
                    return 1; // RIGHT
                } else {
                    return 2; // DOWN;
                }
            } else {
                if (n - this.c - 1 < this.r) {
                    return 3; // UP
                } else {
                    return 0; // LEFT;
                }
            }
        }

        public Pos next(final int n) {
            int nextType = this.getNextType(n);

            return new Pos( this.r + DIRECTIONS[nextType][0],
                            this.c + DIRECTIONS[nextType][1]);
        }

        public Pos next(final int n, final int type) {
            return new Pos( this.r + DIRECTIONS[type][0],
                    this.c + DIRECTIONS[type][1]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int out = 0;
        Pos current = new Pos(n / 2, n / 2);
        for (int i = 1; i < n * n; i++) {
            int nd = current.getNextType(n);
            Pos source = current.next(n, nd);

            int value = map[source.r][source.c];

            for (int j = -2; j <= 2; j++) {
                for (int k = -2; k <= 2; k++) {
                    int destinationR = source.r + j;
                    int destinationC = source.c + k;

                    double ratio = spreadRatio[nd][j + 2][k + 2];
                    if (ratio != 0) {
                        int moveValue = (int)(value * ratio);
                        map[source.r][source.c] -= moveValue;

                        try {
                            map[destinationR][destinationC] += moveValue;
                        } catch (IndexOutOfBoundsException e) {
                            out += moveValue;
                        }
                    }
                }
            }

            int nr = source.r + Pos.DIRECTIONS[nd][0];
            int nc = source.c + Pos.DIRECTIONS[nd][1];

            try {
                map[nr][nc] += map[source.r][source.c];
            } catch (IndexOutOfBoundsException e) {
                out += map[source.r][source.c];
            }
            map[source.r][source.c] = map[current.r][current.c];
            map[current.r][current.c] = 0;

            current = source;
        }

        System.out.println(out);
    }
}
