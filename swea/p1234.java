import java.util.Scanner;
import java.util.Stack;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = 1;
        while(sc.hasNext()) {
            int n = sc.nextInt();
            String str = sc.next();
            Stack<Character> s = new Stack<>();
            for (int i = 0; i < n; ++i) {
                if (s.empty()) {
                    s.push(str.charAt(i));
                }
                else {
                    if (s.peek() == str.charAt(i)) {
                        s.pop();
                    }
                    else {
                        s.push(str.charAt(i));
                    }
                }
            }
            str = "";
            while (!s.empty()) {
                str = s.pop() + str;
            }
            System.out.printf("#%d %s\n", tc++, str);
        }
    }

}