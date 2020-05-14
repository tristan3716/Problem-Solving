package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@SuppressWarnings("unchecked")
public class p14891 {
    static LinkedList<Boolean>[] gear = new LinkedList[4];

    private static void rotate(int cur, int d) {
        boolean L = false;
        boolean R = false;
        if (cur > 0) {
            if (gear[cur].get(6) != gear[cur-1].get(2)) {
                L = true;
            }
        }
        if (cur < 3) {
            if (gear[cur].get(2) != gear[cur+1].get(6)) {
                R = true;
            }
        }

        Collections.rotate(gear[cur], d);
        if (L) { rotateL(cur-1, -d); }
        if (R) { rotateR(cur+1, -d); }
    }

    private static void rotateL(int cur, int d) {
        boolean L = false;
        if (cur > 0) {
            if (gear[cur].get(6) != gear[cur-1].get(2)) {
                L = true;
            }
        }
        Collections.rotate(gear[cur], d);
        if (L) { rotateL(cur-1, -d); }
    }

    private static void rotateR(int cur, int d) {
        boolean R = false;
        if (cur < 3) {
            if (gear[cur].get(2) != gear[cur+1].get(6)) {
                R = true;
            }
        }
        Collections.rotate(gear[cur], d);
        if (R) { rotateR(cur+1, -d); }
    }

    private static int score() {
        int s = 1;
        int ret = 0;
        for (int i = 0; i < 4; i++, s <<= 1) {
            ret += gear[i].get(0) ? s : 0;
        }
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 4; i++) {
            char[] t = br.readLine().toCharArray();
            gear[i] = new LinkedList<>();
            for (int j = 0; j < 8; j++) {
                if (t[j] == '1') {
                    gear[i].offer(true);
                } else {
                    gear[i].offer(false);
                }
            }
        }

        int k = Integer.parseInt(br.readLine());
        while (k-- != 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int i = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());

            rotate(i, d);
        }

        System.out.println(score());
    }
}