package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p1766 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[n+1];

        graph.add(null);
        for (int i = 1; i < n+1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            inDegree[b]++;
        }

        for (List<Integer> list : graph) {
            if (list != null) list.sort(null);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Queue<Integer> q = new LinkedList<>();
        Queue<Integer> lq = new LinkedList<>();

        for (int i = 1; i < n+1; i++) {
            if (inDegree[i] == 0) {
                pq.offer(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            int c = pq.poll();

            sb.append(c).append(' ');

            for (int next : graph.get(c)) {
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    pq.offer(next);
                }
            }
        }

        System.out.println(sb);
    }
}