import java.util.Scanner;

public class Main {
    public static boolean isPalindrome(int x){
        int t = x;
        int r = 0;
        int s = 0;
        while (t != 0) {
            r = t % 10;
            s = s * 10 + r;
            t /= 10;
        }
        return (s == x);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        while (true){
            int n = sc.nextInt();
            if (n == 0) {
                break;
            }

            if (isPalindrome(n)) {
                sb.append("yes").append("\n");
            }
            else {
                sb.append("no").append("\n");
            }
        }
        System.out.println(sb);
    }
}