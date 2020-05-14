package src;

import java.util.Scanner;

public class p1074 {
    static int n;
    static int tr;
    static int tc;
    static int count;
    static void rec(int r, int c, int size){
        if (size == 1) {
            if (r == tr && c == tc) {
                System.out.println(count);
            }
            count++;
            return;
        }
        rec(r, c, size/2);
        rec(r, c+size/2, size/2);
        rec(r+size/2, c, size/2);
        rec(r+size/2, c+size/2, size/2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        tr = sc.nextInt();
        tc = sc.nextInt();
        int v = 1;
        while (n > 0) {
            n -= 1;
            v <<= 1;
        }

        rec(0, 0, v);
    }
}