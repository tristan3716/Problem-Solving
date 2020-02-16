/**
 * @title P배열 S1
 * @see https://www.acmicpc.net/problem/1566
 * @since 2020.01.29
 * @category simulation, brute-force, math
 * @complexity O(2^n * n) -> 820ms (n = 18)
 * @description
 *      행의 상태가 결정되면 열에 대해서는 완전탐색을 할 필요가 없음.
 *      각각의 Aij는 +i+j, +i-j, -i+j, -i-j 중 한가지이므로 하나를 먼저 결정해주면 다른 하나는 자연스럽게 결정됨
 *      이후 2^n * n에 대한 시뮬레이션
 */

import java.util.BitSet;
import java.util.Scanner;

public class Main {
    static int n;
    static int m;
    static int count = Integer.MAX_VALUE;
    static BitSet bits;

    private static void updateHorizontal(int[][] arr, int r) {
        for (int i = 0; i < m; ++i) {
            arr[r][i] = -arr[r][i];
        }
    }

    private static void updateVertical(int[][] arr, int r) {
        for (int j = 0; j < n; ++j) {
            arr[j][r] = -arr[j][r];
        }
    }

    private static boolean isPArray(int [][] arr) {
        for (int i = 0; i < n; ++i) {
            if (isPHorizontal(arr, i) == false) {
                return false;
            }
        }
        for (int i = 0; i < m; ++i) {
            if (isPVertical(arr, i) == false) {
                return false;
            }
        }
        return true;
    }

    private static boolean isPHorizontal(int[][] arr, int r) {
        int sum = 0;
        for (int i = 0; i < m; ++i) {
            sum += arr[r][i];
        }
        return sum > 0;
    }

    private static boolean isPVertical(int[][] arr, int r) {
        int sum = 0;
        for (int i = 0; i < n; ++i) {
            sum += arr[i][r];
        }
        return sum > 0;
    }

    private static int [][] parseArguments() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        int[][] arr = new int[n][m];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                arr[i][j] = sc.nextInt();
            }
        }
        sc.close();
        return arr;
    }

    private static int solveVertical(int [][] arr) {
        int queryCount = 0;
        for (int i = 0; i < m; ++i) {
            if (!isPVertical(arr, i)) {
                bits.set(i);
                updateVertical(arr, i);
                queryCount += 1;
            }
        }
        return queryCount;
    }

    private static void restoreVertical(int [][] arr) {
        for (int i = 0; i < m; ++i) {
            if (bits.get(i)) {
                updateVertical(arr, i);
            }
        }
    }

    private static void solveHorizontal(int [][] arr, int depth, int queryCount) {
        if (depth == n) {
            bits.clear();
            queryCount += solveVertical(arr);
            boolean valid = isPArray(arr);
            if (valid == true) {
                count = Math.min(count, queryCount);
            }
            restoreVertical(arr);
            return;
        }

        updateHorizontal(arr, depth);
        solveHorizontal(arr, depth+1, queryCount+1);
        updateHorizontal(arr, depth);
        solveHorizontal(arr, depth+1, queryCount);
    }

    public static void main(String[] args) {
        int [][] arr = parseArguments();
        bits = new BitSet(n);
        solveHorizontal(arr, 0, 0);

        if (count == Integer.MAX_VALUE) {
            System.out.println("-1\n");
        }
        else {
            System.out.printf("%d\n", count);
        }
    }
}
