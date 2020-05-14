import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p1194 {
    private static class Solver {
        private final int n, m;
        private final char[][] map;
        private final boolean[][][] visit;

        private final int[] qr = new int[40000];
        private final int[] qc = new int[40000];
        private final int[] qk = new int[40000];

        private int qb = 0;
        private int qe = 0;

        private static final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        public void solve() {
            int pass = 1;
            while (qb != qe) {
                int qs = qe - qb;
                for (int i = 0; i < qs; i++) {
                    int cr = qr[qb];
                    int cc = qc[qb];
                    int ck = qk[qb++];

                    for (int[] d : directions) {
                        int nr = cr + d[0];
                        int nc = cc + d[1];
                        int key = ck;

                        if (map[nr][nc] == '1') {
                            System.out.println(pass);
                            System.exit(0);
                        } else if (map[nr][nc] != '#') {
                            char ch = map[nr][nc];

                            if (ch >= 'a') {
                                key = key | (1 << (ch - 'a'));
                            }
                            else if (ch >= 'A' && ch <= 'Z') {
                                if ((key & (1 << (ch - 'A'))) == 0) {
                                    continue;
                                }
                            }
                            if (!visit[nr][nc][key]) {
                                visit[nr][nc][key] = true;
                                qr[qe] = nr;
                                qc[qe] = nc;
                                qk[qe++] = key;
                            }
                        }
                    }
                }

                pass++;
            }

            System.out.println(-1);
        }

        public Solver() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            map = new char[n + 2][m + 2];
            visit = new boolean[n + 2][m + 2][1 << 6];
            for (int i = 0; i < n + 2; i++) {
                map[i][0] = '#';
                map[i][m + 1] = '#';
            }
            for (int i = 0; i < m + 2; i++) {
                map[0][i] = '#';
                map[n + 1][i] = '#';
            }

            for (int i = 1; i < n + 1; i++) {
                char[] arr = br.readLine().toCharArray();
                for (int j = 1; j < m + 1; j++) {
                    if (arr[j - 1] == '0') {
                        map[i][j] = '.';
                        visit[i][j][0] = true;
                        qr[qe] = i;
                        qc[qe] = j;
                        qk[qe++] = 0;
                    } else {
                        map[i][j] = arr[j - 1];
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        Solver s = new Solver();
        s.solve();
    }
}