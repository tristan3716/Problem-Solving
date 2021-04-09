import java.util.Scanner;
import java.util.Arrays;

public class Main {
    static Scanner sc;
    static int[][] q;
    
    private static boolean hasSameStrike(int[] x, int[] c){
        int strikeCount = 
              (x[0] == c[0] ? 1 : 0)
            + (x[1] == c[1] ? 1 : 0)
            + (x[2] == c[2] ? 1 : 0);
        
        if (strikeCount == c[3]) {
            return true;
        }
        return false;
    }
    
    private static boolean hasSameBall(int[] x, int[] c){
        int ballCount = 
              (x[0] == c[1] ? 1 : 0)
            + (x[0] == c[2] ? 1 : 0)
            + (x[1] == c[0] ? 1 : 0)
            + (x[1] == c[2] ? 1 : 0)
            + (x[2] == c[0] ? 1 : 0)
            + (x[2] == c[1] ? 1 : 0);
            
        if (ballCount == c[4]) {
            return true;
        }
        return false;
    }
    
    public static void main(String []args){
        sc = new Scanner(System.in);
        int n = sc.nextInt();
        q = new int[n][5];
        for (int i = 0; i < n; ++i) {
            int t = sc.nextInt(); // query
            q[i][0] = t / 100;
            q[i][1] = (t / 10) % 10;
            q[i][2] = t % 10;
            q[i][3] = sc.nextInt(); // strike
            q[i][4] = sc.nextInt(); // ball
        }
        int answer = 0;
        int[] a = new int[3];
        for (int i = 1; i < 10; ++i) {
            for (int j = 1; j < 10; ++j) {
                if (i == j) {
                    continue;
                }
                next:
                for (int k = 1; k < 10; ++k) {
                    if (j == k || i == k) {
                        continue;
                    }
                    a[0] = i;
                    a[1] = j;
                    a[2] = k;
                    for (int qi = 0; qi < n; ++qi) {
                        if (!hasSameStrike(a, q[qi]) ||
                            !hasSameBall(a, q[qi])) {
                                continue next;
                        }
                    }
                    answer++;
                }
            }
        }
        
        System.out.println(answer);
    }
}