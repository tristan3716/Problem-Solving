package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class p1412 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        char[][] adjMat = new char[100][100];
        for (int i = 0; i < n; i++) {
            System.arraycopy(br.readLine().toCharArray(), 0, adjMat[i], 0, n);
        }

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (adjMat[i][j] == 'Y') {
                    if (adjMat[j][i] != 'Y') {
                        graph.get(i).add(j);
                    }
                }
            }
        }

        boolean[] visit = new boolean[n];
        boolean[] stack = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (isCycle(i, visit, stack, graph)) {
                System.out.print("NO");
                System.exit(0);
            }
        }
        System.out.print("YES");
    }
    private static boolean isCycle(int i, boolean[] visit, boolean[] stack, ArrayList<ArrayList<Integer>> g) {
        if (stack[i]) return true;
        if (visit[i]) return false;
        visit[i] = true;
        stack[i] = true;
        for (int n : g.get(i)) {
            if (isCycle(n, visit, stack, g)) {
                return true;
            }
        }
        stack[i] = false;
        return false;
    }
}