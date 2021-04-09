package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class p15683 {
    private static class CCTV {
        int type;
        int direction;
        int r, c;

        public CCTV(int type, int direction, int r, int c) {
            this.type = type;
            this.direction = direction;
            this.r = r;
            this.c = c;
        }
    }

    private static class Solver {
        final int n, m;
        final boolean[][] map;
        boolean [][] copied;
        List<CCTV> cctvList = new ArrayList<>();
        public Solver() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            map = new boolean[n][m];
            copied = new boolean[n][m];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < m; j++) {
                    int t = Integer.parseInt(st.nextToken());
                    switch (t) {
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                            cctvList.add(new CCTV(t, 0, i, j));
                            break;
                        case 6:
                            empty++;
                            map[i][j] = true;
                    }
                }
            }

            empty = n*m - empty;
        }
        int empty = 0;

        int count() {
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (copied[i][j]) cnt++;
                }
            }
            return (empty - cnt);
        }

        final int [][] directions = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
        public void calculate() {
            for (int i = 0; i < n; i++) {
                Arrays.fill(copied[i], false);
            }
            next:
            for (CCTV cctv : cctvList) {
                copied[cctv.r][cctv.c] = true;
                int dir = cctv.direction;
                for (int i = 0; i < 4; i++) {
                    int cd = (dir + i) % 4;
                    int nr = cctv.r + directions[cd][0];
                    int nc = cctv.c + directions[cd][1];
                    while (nr >= 0 && nc >= 0 && nr < n && nc < m && !map[nr][nc]) {
                        copied[nr][nc] = true;
                        nr += directions[cd][0];
                        nc += directions[cd][1];
                    }
                    switch (cctv.type) {
                        case 1:
                            continue next;
                        case 2:
                            i++;
                            if (i > 2) { continue next; }
                            break;
                        case 3:
                            if (i > 0) { continue next; }
                            break;
                        case 4:
                            if (i > 1) { continue next; }
                            break;
                        case 5:
                            break;
                    }
                }
            }

            result = Math.min(result, count());
        }

        int result = Integer.MAX_VALUE;

        public void place(int cur) {
            if (cur == cctvList.size()) {
                calculate();
            } else {
                CCTV cctv = cctvList.get(cur);
                switch (cctv.type) {
                    case 1:
                    case 3:
                    case 4:
                        for (int i = 0; i < 4; i++) {
                            cctv.direction = i;
                            place(cur + 1);
                        }
                        break;
                    case 2:
                        for (int i = 0; i < 2; i++) {
                            cctv.direction = i;
                            place(cur + 1);
                        }
                        break;
                    case 5:
                        place(cur + 1);
                }
            }
        }
        public void solve () {
            place(0);
            System.out.println(result);
        }
    }
    public static void main(String[] args) throws IOException {
        Solver s = new Solver();
        s.solve();
    }
}