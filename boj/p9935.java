package src;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class p9935 {
    public static void main(String[] args)  {
        Scanner sc = new Scanner(System.in);
        Deque<Character> s = new ArrayDeque<>();
        Deque<Character> expCheckStack = new ArrayDeque<>();
        String raw = sc.next();
        String exp = sc.next();
        final String reverseExp = (new StringBuffer(exp)).reverse().toString();
        final int expCnt = exp.length();

        for (int i = 0; i < raw.length(); ++i) {
            s.offer(raw.charAt(i));
            if (!s.isEmpty()) {
                if (s.peekLast() == reverseExp.charAt(0)) {
                    int expIdx = 0;
                    while (!s.isEmpty()) {
                        if (reverseExp.charAt(expIdx) == s.peekLast()) {
                            expCheckStack.offerLast(s.pollLast());
                            if (expIdx == expCnt-1) {
                                expCheckStack.clear();
                                break;
                            }
                            if (s.isEmpty()) {
                                while (!expCheckStack.isEmpty()) {
                                    s.offerLast(expCheckStack.pollLast());
                                }
                                break;
                            }
                            expIdx++;
                        }
                        else {
                            while (!expCheckStack.isEmpty()) {
                                s.offerLast(expCheckStack.pollLast());
                            }
                            break;
                        }
                    }
                }
            }
        }

        if (s.isEmpty()) {
            System.out.println("FRULA");
        }
        else {
            StringBuilder res = new StringBuilder();
            for (Character x : s) {
                res.append(x);
            }
            System.out.println(res);
        }
    }
}