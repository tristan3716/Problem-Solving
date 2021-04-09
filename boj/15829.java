import java.util.Scanner;

public class Main {
    static int M = 1234567891;
    public static int hash(String str, int n) {
        char[] arr = str.toCharArray();
        long h = 0;
        long r = 1;
        for (char x : arr) {
            h = (h + ((x - 'a' + 1) * r) % M) % M;
            r *= 31;
            r %= M;
        }
        return (int) (h % M);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        String str = sc.next();

        System.out.println(hash(str, n));
    }
}