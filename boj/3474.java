import java.util.Scanner;

public class Main{
    static int five;
    public static void main(String []args){
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int t = sc.nextInt();
        int n = 0;
        for (int i = 0; i < t; ++i) {
            five = 0;
            n = sc.nextInt();
            while (n != 0) {
                five += n/5;
                n /= 5;
            }
            sb.append(five).append("\n");
        }
        System.out.println(sb);
    }
}