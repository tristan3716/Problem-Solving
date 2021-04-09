import java.util.Scanner;

public class Main {
    int n;
    static long [] tree;
    static long [] source;

    void update(int i, long diff) {
        while (i <= n){
            tree[i] += diff;
            i += (i & -i);
        }
    }

    long sum(int i) {
        long res = 0;
        while (i > 0) {
            res += tree[i];
            i -= (i & -i);
        }

        return res;
    }

    public void solution() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        int N = 1000005;
        tree = new long[N];
        source = new long[N];

        for (int i = 1; i <= n; ++i) {
            source[i] = sc.nextLong();
            update(i, source[i]);
        }

        for (int i = 0; i < (m+k); ++i) {
            int type = sc.nextInt();
            if (type == 1) {
                int b = sc.nextInt();
                long c = sc.nextLong();
                long diff = c - source[b];
                source[b] = c;
                update(b, diff);
            } else {
                int b = sc.nextInt();
                int c = sc.nextInt();
                System.out.println((sum(c) - sum(b-1)));
            }
        }
    }

    public static void main(String[] args) {
        Main ts = new Main();
        ts.solution();
    }
}