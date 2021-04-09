import java.util.Scanner;
import java.util.Arrays;

public class Main {
    static Scanner sc;
    static int[][] q;
    private static int calculate(String x, String y){
        int count = 0;
        for (int i = 0; i < x.length(); ++i) {
            count += (x.charAt(i) == y.charAt(i) ? 0 : 1);
        }
        return count;
    }
    public static void main(String []args){
        sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        
        String[] s = new String[n];
        int[][] c = new int[m][4]; // A  // C // G // T
        for (int i = 0; i < n; ++i) {
            s[i] = sc.next();
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                switch(s[i].charAt(j)) {
                    case 'A':
                        c[j][0]++;
                        break;
                    case 'C':
                        c[j][1]++;
                        break;
                    case 'G':
                        c[j][2]++;
                        break;
                    case 'T':
                        c[j][3]++;
                        break;
                }
            }
        }
        
        int sum = 0;
        for (int i = 0; i < m; ++i) {
            int max = c[i][0];
            int maxChar = 0;
            int cnt = max;
            for (int j = 1; j < 4; ++j) {
                if (max < c[i][j]) {
                    max = c[i][j];
                    maxChar = j;
                }
                if (c[i][j] != 0) {
                    cnt += c[i][j];
                }
            }
            sum += (cnt - max);
            switch (maxChar) {
                    case 0:
                        System.out.print('A');
                        break;
                    case 1:
                        System.out.print('C');
                        break;
                    case 2:
                        System.out.print('G');
                        break;
                    case 3:
                        System.out.print('T');
                        break;
            }
        }
        System.out.print("\n" + sum);
    }
}