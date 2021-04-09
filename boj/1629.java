import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    static long p(int a, int b, int m) {
        if (b == 0) return 1;
        else if ((b & 1) != 0) {
            return a * p(a, b-1, m);
        }
        else {
            long aa = p(a, b/2, m);
            return ((aa % m) * (aa % m) % m);
        }
    }

    public static void main(String[] args) {
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();

        System.out.println(p(a,b,c) % c);
    }
}