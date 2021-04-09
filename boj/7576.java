import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int count, empty;

    private static class TomatoSolver {
        public static final int[] dn = new int[] { -1, 1, 0, 0 };
        public static final int[] dm = new int[] { 0, 0, -1, 1 };
        public static final int directions = dn.length;

        private int convert(int n, int m) {
            return n * M + m;
        }

        private int [] tomatoes;
        private boolean[] visit;
        private int N=1, M=1;
        private int MAX;
        private Queue<Tomato> qq;
        int dimension;

        public void parse() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());

            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());

            MAX = N * M;

            tomatoes = new int[MAX];
            visit = new boolean[MAX];
            qq = new LinkedList<>();

            int n = 0, m = 0;

            for (n = 0; n < N; n++) {
                st = new StringTokenizer(br.readLine());

                for (m = 0; m < M; m++) {
                    int value = Integer.parseInt(st.nextToken());
                    tomatoes[convert(n, m)] = value;
                    if (value == -1)
                        empty++;
                    else if (value == 1) {
                        visit[convert(n, m)] = true;
                        this.qq.add(new Tomato(n, m, 0));
                        count++;
                    }
                }
            }
        }
        private boolean isIn(int n, int m){
            return !(n < 0 || n >= N
                    || m < 0 || m >= M);
        }

        public void solve() {
            if (count + empty == MAX) {
                System.out.println(0);
                System.exit(0);
            }

            while(!qq.isEmpty()) {
                Tomato t = qq.poll();

                for (int d = 0; d < directions; d++) {
                    int nn = t.n + dn[d];
                    int nm = t.m + dm[d];

                    if (isIn(nn, nm)
                            && !visit[convert(nn, nm)]
                            && tomatoes[convert(nn, nm)] == 0)
                    {
                        tomatoes[convert(nn, nm)] = 1;
                        visit[convert(nn, nm)] = true;
                        qq.add(new Tomato(nn, nm, t.day + 1));

                        count++;
                        if (count + empty == MAX) {
                            System.out.println(t.day + 1);
                            System.exit(0);
                        }
                    }
                }
            }

            System.out.println(-1);
        }

        private static class Tomato {
            public int n, m;
            int day;

            public Tomato(int n, int m, int day) {
                this.n = n;
                this.m = m;
                this.day = day;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        TomatoSolver ts = new TomatoSolver();
        ts.parse();
        ts.solve();
    }
}