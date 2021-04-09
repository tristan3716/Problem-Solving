package src;

import java.util.*;

public class p17822 {
    private static class PlateRotationSolver {
        private static class Pos {
            int r, c;

            public Pos(int r, int c) {
                this.r = r;
                this.c = c;
            }
        }
        int n;
        int m;
        int t;
        int [][] arr;
        Scanner sc = new Scanner(System.in);

        public PlateRotationSolver() {
            n = sc.nextInt();
            m = sc.nextInt();
            t = sc.nextInt();
            arr = new int[n][m];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }
        }

        private boolean remove() {
            List<Pos> l = new ArrayList<Pos>();
            boolean isRemoved = false;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (arr[i][j] != 0) {
                        if (arr[i][j] == arr[i][(j+1)%m]) {
                            l.add(new Pos(i, j));
                            l.add(new Pos(i, (j+1)%m));
                        }
                    }
                }
            }
            for (int i = 0; i < n-1; i++) {
                for (int j = 0; j < m; j++) {
                    if (arr[i][j] != 0) {
                        if (arr[i][j] == arr[i+1][j]) {
                            l.add(new Pos(i, j));
                            l.add(new Pos(i+1, j));
                        }
                    }
                }
            }
            if (l.size() != 0) {
                isRemoved = true;
            }
            for (Pos x : l){
                arr[x.r][x.c] = 0;
            }

            return isRemoved;
        }
        int leftCount;
        int sum;
        public int getSum() {
            leftCount = 0;
            sum = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (arr[i][j] != 0) {
                        sum += arr[i][j];
                        leftCount++;
                    }
                }
            }
            return sum;
        }

        private void update() {
            // 실수 자료형 사용 x
            getSum();
            if (leftCount == 0) return;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (arr[i][j] != 0) {
                        if (arr[i][j] * leftCount < sum) {
                            arr[i][j]++;
                        } else if (arr[i][j] * leftCount > sum) {
                            arr[i][j]--;
                        }
                    }
                }

            }
        }

        private void rotateL(int r, int k) {
            int[] a = Arrays.copyOfRange(arr[r], 0, k);
            int[] b = Arrays.copyOfRange(arr[r], k, m);

            System.arraycopy(b, 0, arr[r], 0, b.length);
            System.arraycopy(a, 0, arr[r], b.length, a.length);
        }

        private void rotateR(int r, int k) {
            int[] a = Arrays.copyOfRange(arr[r], 0, m - k);
            int[] b = Arrays.copyOfRange(arr[r], m - k, m);

            System.arraycopy(b, 0, arr[r], 0, b.length);
            System.arraycopy(a, 0, arr[r], b.length, a.length);
        }

        public void solve() {
            for (int i = 0; i < t; ++i) {
                int x = sc.nextInt();
                int d = sc.nextInt();
                int k = sc.nextInt();
                switch(d) {
                    case 0:
                        for (int p = x; p <= n; p += x) {
                            rotateR(p-1, k);
                        }
                        break;
                    case 1:
                        for (int p = x; p <= n; p += x) {
                            rotateL(p-1, k);
                        }
                        break;
                }
                boolean isRemoved = remove();
                if (!isRemoved) {
                    update();
                }
            }
        }
    }
    public static void main(String[] args) {
        PlateRotationSolver pr = new PlateRotationSolver();
        pr.solve();

        System.out.println(pr.getSum());
    }
}