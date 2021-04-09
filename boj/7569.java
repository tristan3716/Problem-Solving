import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int count, empty;

    private static class TomatoSolver {
        public static final int[] dw = new int[] { -1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        public static final int[] dv = new int[] { 0, 0, -1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        public static final int[] du = new int[] { 0, 0, 0, 0, -1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        public static final int[] dt = new int[] { 0, 0, 0, 0, 0, 0, -1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        public static final int[] dr = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, -1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        public static final int[] ds = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        public static final int[] dq = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 1, 0, 0, 0, 0, 0, 0, 0, 0 };
        public static final int[] dp = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 1, 0, 0, 0, 0, 0, 0 };
        public static final int[] dO = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 1, 0, 0, 0, 0 };
        public static final int[] dn = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 1, 0, 0 };
        public static final int[] dm = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 1 };
        public static final int directions = dn.length;

        private int convert(int n, int m) {
            return n * M + m;
        }
        private int convert(int o, int n, int m) {
            return o * NM + n * M + m;
        }
        private int convert(int w, int v, int u, int t, int r, int s, int q, int p, int o, int n, int m) {
            return w * VM + v * UM + u * TM + t * RM + r * SM + s * QM + q * PM + p * OM + o * NM + n * M + m;
        }

        private int [] tomatoes;
        private boolean[] visit;
        private int W=1, V=1, U=1, T=1, S=1, R=1, Q=1, P=1, O=1, N=1, M=1;
        private int MAX, VM, UM, TM, SM, RM, QM, PM, OM, NM;
        private Queue<Tomato> qq;
        int dimension;

        public void parse() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            dimension = st.countTokens();

            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            if (dimension > 2) {
                O = Integer.parseInt(st.nextToken());
                if (dimension > 3) {
                    P = Integer.parseInt(st.nextToken());
                    Q = Integer.parseInt(st.nextToken());
                    R = Integer.parseInt(st.nextToken());
                    S = Integer.parseInt(st.nextToken());
                    T = Integer.parseInt(st.nextToken());
                    U = Integer.parseInt(st.nextToken());
                    V = Integer.parseInt(st.nextToken());
                    W = Integer.parseInt(st.nextToken());
                }
            }

            MAX = W * V * U * T * R * S * Q * P * O * N * M;
            VM = V * U * T * R * S * Q * P * O * N * M;
            UM = U * T * R * S * Q * P * O * N * M;
            TM = T * R * S * Q * P * O * N * M;
            RM = R * S * Q * P * O * N * M;
            SM = S * Q * P * O * N * M;
            QM = Q * P * O * N * M;
            PM = P * O * N * M;
            OM = O * N * M;
            NM = N * M;

            tomatoes = new int[MAX];
            visit = new boolean[MAX];
            qq = new LinkedList<>();

            int w = 0, v = 0, u = 0, t = 0, s = 0, r = 0, q = 0, p = 0, o = 0, n = 0, m = 0;

            switch (dimension){
                case 11:
                    for (w = 0; w < W; w++) {
                        for (v = 0; v < V; v++) {
                            for (u = 0; u < U; u++) {
                                for (t = 0; t < T; t++) {
                                    for (s = 0; s < S; s++) {
                                        for (r = 0; r < R; r++) {
                                            for (q = 0; q < Q; q++) {
                                                for (p = 0; p < P; p++) {
                                                    for (o = 0; o < O; o++) {
                                                        for (n = 0; n < N; n++) {
                                                            st = new StringTokenizer(br.readLine());

                                                            for (m = 0; m < M; m++) {
                                                                int value = Integer.parseInt(st.nextToken());
                                                                tomatoes[convert(w, v, u, t, s, r, q, p, o, n, m)] = value;
                                                                if (value == -1)
                                                                    empty++;
                                                                if (value == 1) {
                                                                    visit[convert(w, v, u, t, s, r, q, p, o, n, m)] = true;
                                                                    this.qq.add(new Tomato(w, v, u, t, s, r, q, p, o, n, m, 0));
                                                                    count++;
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    break;
                case 3:
                    for (o = 0; o < O; o++) {
                        for (n = 0; n < N; n++) {
                            st = new StringTokenizer(br.readLine());

                            for (m = 0; m < M; m++) {
                                int value = Integer.parseInt(st.nextToken());
                                tomatoes[convert(o, n, m)] = value;
                                if (value == -1)
                                    empty++;
                                if (value == 1) {
                                    visit[convert(o, n, m)] = true;
                                    this.qq.add(new Tomato(o, n, m, 0));
                                    count++;
                                }
                            }
                        }
                    }
                    break;
                case 2:
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
                    break;
            }
        }
        private boolean isIn(int w, int v, int u, int t, int r, int s, int q, int p, int o, int n, int m){
            return !(w < 0 || w >= W
                    || v < 0 || v >= V
                    || u < 0 || u >= U
                    || t < 0 || t >= T
                    || s < 0 || s >= S
                    || r < 0 || r >= R
                    || q < 0 || q >= Q
                    || p < 0 || p >= P
                    || o < 0 || o >= O
                    || n < 0 || n >= N
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
                    int nw = t.w + dw[d];
                    int nv = t.v + dv[d];
                    int nu = t.u + du[d];
                    int nt = t.t + dt[d];
                    int ns = t.s + ds[d];
                    int nr = t.r + dr[d];
                    int nq = t.q + dq[d];
                    int np = t.p + dp[d];
                    int no = t.o + dO[d];
                    int nn = t.n + dn[d];
                    int nm = t.m + dm[d];

                    if (isIn(nw, nv, nu, nt, ns, nr, nq, np, no, nn, nm)
                        && !visit[convert(nw, nv, nu, nt, ns, nr, nq, np, no, nn, nm)]
                        && tomatoes[convert(nw, nv, nu, nt, ns, nr, nq, np, no, nn, nm)] == 0)
                    {
                        tomatoes[convert(nw, nv, nu, nt, ns, nr, nq, np, no, nn, nm)] = 1;
                        visit[convert(nw, nv, nu, nt, ns, nr, nq, np, no, nn, nm)] = true;
                        qq.add(new Tomato(nw, nv, nu, nt, ns, nr, nq, np, no, nn, nm, t.day + 1));

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
            public int w, v, u, t, s, r, q, p, o, n, m;
            int day;

            public Tomato(int n, int m, int day) {
                this.n = n;
                this.m = m;
                this.day = day;
            }
            public Tomato(int o, int n, int m, int day) {
                this.o = o;
                this.n = n;
                this.m = m;
                this.day = day;
            }
            public Tomato(int w, int v, int u, int t, int s, int r, int q, int p, int o, int n, int m, int day) {
                this.w = w;
                this.v = v;
                this.u = u;
                this.t = t;
                this.s = s;
                this.r = r;
                this.q = q;
                this.p = p;
                this.o = o;
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