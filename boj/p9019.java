package src;

import java.io.*;
import java.util.*;

public class p9019 {
    private static class DSLR {
        int source;
        int destination;
        static Queue<Integer> q = new LinkedList<>();

        static char[] visit = new char[10000];
        static int[] old = new int[10000];

        StringBuilder solution;

        public void init(int source, int destination) {
            this.source = source;
            this.destination = destination;
            solution = new StringBuilder();
            q.clear();
            q.offer(source);
            Arrays.fill(visit, '\0');
        }

        private void tracking(int res) {
            while (res != source) {
                solution.insert(0, visit[res]);
                res = old[res];
            }
        }

        private void solve() {
            while (!q.isEmpty()) {
                int current = q.poll();

                int res = D(current);
                if (visit[res] == 0) {
                    visit[res] = 'D';
                    old[res] = current;
                    if (isReach(res)) {
                        tracking(res);
                        break;
                    }
                    q.offer(res);
                }
                res = S(current);
                if (visit[res] == 0) {
                    visit[res] = 'S';
                    old[res] = current;
                    if (isReach(res)) {
                        tracking(res);
                        break;
                    }
                    q.offer(res);
                }
                res = L(current);
                if (visit[res] == 0) {
                    visit[res] = 'L';
                    old[res] = current;
                    if (isReach(res)) {
                        tracking(res);
                        break;
                    }
                    q.offer(res);
                }
                res = R(current);
                if (visit[res] == 0) {
                    visit[res] = 'R';
                    old[res] = current;
                    if (isReach(res)) {
                        tracking(res);
                        break;
                    }
                    q.offer(res);
                }
            }
        }

        private String getAnswer() {
            return this.solution.toString();
        }

        private boolean isReach(int x) {
            return x == destination;
        }

        private int D(int x) {
            return (2 * x) % 10000;
        }

        private int S(int x) {
            return (x + 9999) % 10000;
        }

        private int L(int x) {
            return (x * 10) % 10000 + (x / 1000);
        }

        private int R(int x) {
            return (x / 10) + (x % 10) * 1000;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        DSLR dslr = new DSLR();
        int tc = Integer.parseInt(br.readLine());
        for (int i = 0; i < tc; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int source = Integer.parseInt(st.nextToken());
            int destination = Integer.parseInt(st.nextToken());

            dslr.init(source, destination);

            dslr.solve();

            String result = dslr.getAnswer();

            bw.write(result);
            bw.write('\n');
        }
        bw.flush();
    }
}