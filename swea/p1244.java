import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    static List<Set<String>> s = new ArrayList<>();

    static int toInt(char [] x) {
        int res = 0;
        for (char c : x) {
            res = res * 10 + (c - '0');
        }
        return res;
    }

    static void swap(char [] x, int i, int j) {
        char temp = x[i];
        x[i] = x[j];
        x[j] = temp;
    }

    static int max;
    static void rec(char [] x, int k, int c){
        if (k == c) {
            max = Math.max(max, toInt(x));
        } else {
            for (int i = 0; i < x.length; ++i) {
                for (int j = i+1; j < x.length; ++j) {
                    if (i != j){
                        swap(x, i, j);
                        if (!s.get(c).contains(String.valueOf(x))) {
                            s.get(c).add(String.valueOf(x));
                            rec(x, k, c + 1);
                        }
                        swap(x, i, j);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 10; ++i) {
            s.add(new HashSet<>());
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        for (int tc = 1;; ++tc){
            String [] ss = br.readLine().split(" ");
            String x = ss[0];
            int k = Integer.parseInt(ss[1]);

            rec(x.toCharArray(), k, 0);
            sb.append('#').append(tc).append(' ').append(max).append('\n');
            if (tc == t)
                break;
            max = 0;
            for (Set<String> X : s){
                X.clear();
            }
        }
        System.out.print(sb);
    }
}