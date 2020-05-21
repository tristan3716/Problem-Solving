import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@SuppressWarnings("unchecked")
public class Solution {
    private static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }

        private static int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        private static int[][] dirsM = {{0, 0}, {-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        private static boolean isIn(int r, int c) {
            return (r >= 0 && c >= 0 && r < 10 && c < 10);
        }

        private void move(int dir) {
            this.r += dirsM[dir][0];
            this.c += dirsM[dir][1];
        }
    }

    private static class Charger {
        int id;
        int r, c;
        int radius;
        int p;

        public Charger(int id, int r, int c, int radius, int p) {
            this.id = id;
            this.r = r;
            this.c = c;
            this.radius = radius;
            this.p = p;

            spread();
        }

        private static boolean[][] visit = new boolean[10][10];
        private void init() {
            for (int i = 0; i < 10; i++) {
                Arrays.fill(visit[i], false);
            }
        }

        private void spread() {
            init();
            Queue<Pos> q = new LinkedList<>();
            q.offer(new Pos(r, c));
            visit[r][c] = true;

            for (int i = 0; i <= radius; i++) {
                int qs = q.size();

                for (int j = 0; j < qs; j++) {
                    Pos cur = q.poll();
                    assert cur != null;
                    mapCharger[cur.r][cur.c].add(id);

                    for (int[] d : Pos.dirs) {
                        int nr = cur.r + d[0];
                        int nc = cur.c + d[1];

                        if (Pos.isIn(nr, nc) && !visit[nr][nc]) {
                            visit[nr][nc] = true;
                            q.offer(new Pos(nr, nc));
                        }
                    }
                }
            }

        }
    }

    private static Charger[] chargers = new Charger[9];
    private static int m, a;
    private static int[] commandA = new int[100];
    private static int[] commandB = new int[100];
    private static List<Integer>[][] mapCharger = new ArrayList[10][10];

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static void parse() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        m = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < m; i++) {
            commandA[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < m; i++) {
            commandB[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= a; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            chargers[i] = new Charger(i, y, x, c, p);
        }
    }

    private static void init() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                mapCharger[i][j] = new ArrayList<>();
                mapCharger[i][j].add(0);
            }
        }
        chargers[0] = new Charger(0, 0, 0, 0, 0);
    }

    private static int charge(Pos a, Pos b) {
        List<Integer> ca = mapCharger[a.r][a.c];
        List<Integer> cb = mapCharger[b.r][b.c];

        int maxValue = 0;
        for (int xa : ca) {
            for (int xb : cb) {
                int value;
                if (xa != xb) {
                    value = chargers[xa].p + chargers[xb].p;
                } else {
                    value = chargers[xa].p;
                }
                maxValue = Math.max(maxValue, value);
            }
        }

        return maxValue;
    }

    private static int solve() {
        Pos a = new Pos(0, 0);
        Pos b = new Pos(9, 9);

        int sum = charge(a, b);
        for (int i = 0; i < m; i++) {
            a.move(commandA[i]);
            b.move(commandB[i]);

            sum += charge(a, b);
        }

        return sum;
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine()) + 1;
        for (int tc = 1; tc != t; tc++) {
            init();
            parse();
            int answer = solve();

            sb.append('#').append(tc).append(' ').append(answer).append('\n');
        }

        System.out.print(sb);
    }
}