package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

@SuppressWarnings("unchecked")
public class p1005 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        next:
        while (t-- != 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int rule = Integer.parseInt(st.nextToken());
            ArrayList<Integer>[] graph = new ArrayList[n + 1];
            int[] weight = new int[n + 1];
            int[] inDegree = new int[n + 1];
            int[] time = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                graph[i] = new ArrayList<>();
            }
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 1; i <= n; i++) {
                weight[i] = Integer.parseInt(st.nextToken());
            }
            for (int i = 0; i < rule; i++) {
                st = new StringTokenizer(br.readLine(), " ");

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                graph[a].add(b);
                inDegree[b]++;
            }

            int target = Integer.parseInt(br.readLine());

            Queue<Integer> q = new LinkedList<>();
            for (int i = 1; i <= n; i++) {
                if (inDegree[i] == 0) {
                    if (i == target) {
                        sb.append(weight[target]).append('\n');
                        continue next;
                    }
                    q.offer(i);
                    time[i] = weight[i];
                }
            }

            while (!q.isEmpty()) {
                int current = q.poll();

                for (int next : graph[current]) {
                    inDegree[next]--;
                    time[next] = Math.max(time[next], time[current] + weight[next]);
                    if (inDegree[next] == 0) {
                        if (next == target) {
                            sb.append(time[target]).append('\n');
                            continue next;
                        }
                        q.offer(next);
                    }
                }
            }
        }
        System.out.print(sb);
    }
}