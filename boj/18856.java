import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        StringBuilder sb = new StringBuilder();
        sb.append(n).append("\n1 2 ");
        for (int i = 3; i < n; i++) {
            sb.append(i).append(' ');
        }
        sb.append(997);

        System.out.print(sb);
    }
}