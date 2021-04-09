import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    private static class Solver {
        final int target;
        final int m;
        boolean[] button = new boolean[22];

        public Solver() {
            Scanner sc = new Scanner(System.in);
            target = Integer.parseInt(sc.nextLine());
            m = Integer.parseInt(sc.nextLine());
            if (m != 0) {
                int[] broken = Stream.of(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                for (int x : broken) {
                    button[x] = true;
                }
            }
        }

        private int c(int n) {
            if (n == 0) {
                if (button[0]) {
                    return 0;
                } else {
                    return 1;
                }
            } else {
                int d = 0;
                while (n > 0) {
                    if (button[n % 10]) return 0;
                    n /= 10;
                    d++;
                }
                return d;
            }
        }

        public void solve() {
            int res = Math.abs(target - 100);
            for (int i = 0; i <= 1000000; i++) {
                int dst = c(i);
                if (dst > 0) {
                    int p = Math.abs(i - target);
                    if (res > p + dst) {
                        res = p + dst;
                    }
                }
            }
            System.out.println(res);
        }
    }

    public static void main(String[] args) {
        Solver s = new Solver();
        s.solve();
    }
}