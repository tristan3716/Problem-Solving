import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {
    static List<Integer>[] graph;
    static char[] vertex;
    static boolean isDigit(char x) {
        return (x >= '0' && x <= '9');
    }
    static StringBuilder res;
    static void dfs(int v) {
        try {
            dfs(graph[v].get(0));
        }
        catch (Exception e) {}
        res.append(vertex[v]);
        try {
            dfs(graph[v].get(1));
        }
        catch (Exception e) {}
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        next:
        for (int tc = 1; tc <= 10; ++tc) {
            res = new StringBuilder();
            int n = sc.nextInt();

            graph = new List[n + 1];
            vertex = new char[n+1];
            for (int i = 1; i < graph.length; ++i) {
                graph[i] = new ArrayList<>();
            }

            sc.nextLine();
            for (int i = 0; i < n; ++i) {
                String str = sc.nextLine();
                String[] sp = str.split(" ");
                int start = Integer.parseInt(sp[0]);
                vertex[start] = sp[1].charAt(0);
                for (int j = 2; j < sp.length; j++) {
                    int end = Integer.parseInt(sp[j]);
                    graph[start].add(end);
                }
            }
            dfs(1);
            System.out.print("#" + tc + " ");
            for (int i = 0; i < res.length()-1; ++i) {
                if (isDigit(res.charAt(i)) == isDigit(res.charAt(i+1))){
                    System.out.println(0);
                    continue next;
                }
            }
            System.out.println(1);
        }

    }
}