import java.util.Scanner;
import java.util.Stack;

public class Solution {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        Stack<Integer> s = new Stack<>();
        int n;
        int val;
        int sum;
        for (int tc = 1; tc <= t; ++tc) {
            sum = 0;
            s.clear();
            n = sc.nextInt();
            for (int i = 0; i < n; ++i) {
                val = sc.nextInt();
                if (val == 0) {
                    s.pop();
                }
                else {
                    s.push(val);
                }
            }

            for (int x : s) {
                sum += x;
            }
            System.out.printf("#%d %d\n", tc, sum);
        }
        sc.close();
    }
}