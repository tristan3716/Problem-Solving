package src;

import java.io.*;
import java.util.ArrayList;

public class p3663 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        final int [] vertical = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        while (t-- != 0) {
            char[] x = br.readLine().toCharArray();
            int v_move = 0;
            int length = x.length;
            ArrayList<Integer> right = new ArrayList<>();
            ArrayList<Integer> left = new ArrayList<>();
            right.add(0);
            for (int i = 0; i < length; i++) {
                v_move += vertical[x[i] - 'A'];
                if (x[i] != 'A') {
                    right.add(i);
                    left.add(length - i);
                }
            }
            left.add(0);
            Integer [] rrr = new Integer[right.size()];
            right.toArray(rrr);
            Integer [] lrr = new Integer[left.size()];
            left.toArray(lrr);

            int min = rrr[0] * 2 + lrr[0];
            int dis;
            for (int i = 1; i < right.size(); i++) {
                dis = rrr[i] < lrr[i] ? rrr[i] * 2 + lrr[i] : rrr[i] + lrr[i] * 2;
                min = Math.min(min, dis);
            }
            sb.append((min + v_move)).append('\n');
        }
        System.out.println(sb);
    }
}