package src;

import java.util.Scanner;

public class p14500 {
    static int[][] shape = {
            {1,1,1},
            {0,1,1}};

    static int[][] shape1 = {
            {1},
            {1},
            {1},
            {1}};
    static int[][] shape2 = {
            {1,1,1,1}};

    static int[][] shape3 = {
            {1,1},
            {1,1}};

    static int[][] shape4 = {
            {1,1},
            {0,1,1}};
    static int[][] shape5 = {
            {0,1,1},
            {1,1}};
    static int[][] shape6 = {
            {1},
            {1,1},
            {0,1}};
    static int[][] shape7 = {
            {0,1},
            {1,1},
            {1}};

    static int[][] shape8 = {
            {1,0},
            {1},
            {1,1}};
    static int[][] shape9 = {
            {0,1},
            {0,1},
            {1,1}};
    static int[][] shape10 = {
            {1,1},
            {0,1},
            {0,1}};
    static int[][] shape11 = {
            {1,1},
            {1},
            {1}};
    static int[][] shape12 = {
            {1},
            {1,1,1}};
    static int[][] shape13 = {
            {1,1,1},
            {1}};
    static int[][] shape14 = {
            {1,1,1},
            {0,0,1}};
    static int[][] shape15 = {
            {0,0,1},
            {1,1,1}};


    static int[][] shape16 = {
            {0,1},
            {1,1,1}};
    static int[][] shape17 = {
            {1,1,1},
            {0,1}};
    static int[][] shape18 = {
            {1,0},
            {1,1},
            {1}};
    static int[][] shape19 = {
            {0,1},
            {1,1},
            {0,1}};

    static int[][][] table = {	shape1,
            shape2,
            shape3,
            shape4,
            shape5,
            shape6,
            shape7,
            shape8,
            shape9,
            shape10,
            shape11,
            shape12,
            shape13,
            shape14,
            shape15,
            shape16,
            shape17,
            shape18,
            shape19};

    static int n;
    static int m;

    private static boolean isValid(final int i, final int j) {
        return !(i < 0 || j < 0 || i >= n || j >= m);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        int[][] arr = new int[n][m];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                arr[i][j] = sc.nextInt();
            }
        }

        int max = -1;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                for (int[][] x : table) {
                    int sum = 0;
                    put:
                    for (int ii = 0; ii < x.length; ++ii) {
                        for (int jj = 0; jj < x[ii].length; ++jj) {
                            if (!isValid(i+ii, j+jj)) {
                                sum = 0;
                                break put;
                            }
                            if (x[ii][jj] == 1) {
                                sum += arr[i+ii][j+jj];
                            }
                        }
                    }
                    if (max < sum) {
                        max = sum;
                    }
                }
            }
        }
        System.out.println(max);
    }
}