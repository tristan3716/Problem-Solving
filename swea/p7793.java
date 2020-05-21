import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, m;
    static int [][] map = new int[52][52];
    static final int [] empty = new int[52];

    static int[] eqr = new int[2505];
    static int[] eqc = new int[2505];
    static int eqb;
    static int eqe;

    static int[] fqr = new int[2505];
    static int[] fqc = new int[2505];
    static int fqb;
    static int fqe;

    private static final int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    private static void init() throws IOException {
        eqb = 0;
        eqe = 0;
        fqb = 0;
        fqe = 0;

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n+2; i++) {
            map[i][0] = -1;
            map[i][m+1] = -1;
        }
        for (int i = 0; i < m+2; i++) {
            map[0][i] = -1;
            map[n+1][i] = -1;
        }

        for (int i = 1; i < n+1; i++) {
            System.arraycopy(empty, 0, map[i], 1, m);
            char [] arr = br.readLine().toCharArray();
            for (int j = 1; j < m+1; j++) {
                char ch = arr[j-1];
                switch (ch) {
                    case 'S':
                        eqr[eqe] = i;
                        eqc[eqe++] = j;
                        map[i][j] = 1;
                        break;
                    case '.':
                        map[i][j] = 0;
                        break;
                    case 'D':
                        map[i][j] = -9;
                        break;
                    case 'X':
                        map[i][j] = -1;
                        break;
                    case '*':
                        fqr[fqe] = i;
                        fqc[fqe++] = j;
                        map[i][j] = 1;
                        break;
                }
            }
        }
    }


    private static int solve() {
        while (eqb != eqe) {
            int qs = fqe - fqb;
            for (int i = 0; i < qs; i++) {
                int cr = fqr[fqb];
                int cc = fqc[fqb++];

                for (int [] d : directions) {
                    int nr = cr + d[0];
                    int nc = cc + d[1];

                    if (map[nr][nc] == 0) {
                        map[nr][nc] = 1;
                        fqr[fqe] = nr;
                        fqc[fqe++] = nc;
                    }
                }
            }

            qs = eqe - eqb;
            for (int i = 0; i < qs; i++) {
                int cr = eqr[eqb];
                int cc = eqc[eqb++];

                for (int [] d : directions) {
                    int nr = cr + d[0];
                    int nc = cc + d[1];

                    if (map[nr][nc] == 0) {
                        map[nr][nc] = map[cr][cc] + 1;
                        eqr[eqe] = nr;
                        eqc[eqe++] = nc;
                    } else if (map[nr][nc] == -9) {
                        return map[cr][cc];
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; ++tc) {
            init();
            int res = solve();
            sb.append('#').append(tc).append(' ').append(res == -1 ? "GAME OVER" : res).append('\n');
        }
        System.out.println(sb);
    }
}