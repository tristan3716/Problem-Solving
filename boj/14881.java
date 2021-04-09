import java.util.*;

public class Main {
    private static int gcd(int a, int b){
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for (int tc = 0; tc < t; ++tc) {
            int A = sc.nextInt();
            int B = sc.nextInt();
            int C = sc.nextInt();
            if (C>A && C>B) {
                System.out.println("NO");
                continue;
            }
            int g = gcd(A, B);

            if (C % g == 0) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

}