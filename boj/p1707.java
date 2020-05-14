package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p1707 {
    private static boolean isBipartiteGraph(int startVertex) {
        Queue<Integer> queue = new LinkedList<>();

        int currentColor = 1;
        int nextColor = -1;

        queue.offer(startVertex);
        vertexColor[startVertex] = currentColor;

        while (!queue.isEmpty()) {
            nextColor = -currentColor;

            int queueSize = queue.size();
            while (queueSize-- != 0) {
                int currentVertex = queue.poll();

                for (int nextVertex : graphByAdjacentList.get(currentVertex)) {
                    if (vertexColor[nextVertex] == 0) {
                        vertexColor[nextVertex] = nextColor;
                        queue.offer(nextVertex);
                    } else {
                        if (vertexColor[nextVertex] == currentColor) {
                            return false;
                        }
                    }
                }
            }

            currentColor = nextColor;
        }

        return true;
    }

    static int V;
    static int E;
    static int[] vertexColor = new int[20001];
    static ArrayList<ArrayList<Integer>> graphByAdjacentList = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        testcase:
        for (int tc = 1; tc <= T; ++tc) {
            StringTokenizer stringTokenizer = new StringTokenizer(br.readLine(), " ");
            V = Integer.parseInt(stringTokenizer.nextToken());
            E = Integer.parseInt(stringTokenizer.nextToken());

            Arrays.fill(vertexColor, 0);
            graphByAdjacentList.clear();
            graphByAdjacentList.add(null);
            for (int i = 0; i < V; i++) { graphByAdjacentList.add(new ArrayList<>()); }

            while (E-- != 0) {
                stringTokenizer = new StringTokenizer(br.readLine(), " ");
                int startOfEdge = Integer.parseInt(stringTokenizer.nextToken());
                int endOfEdge = Integer.parseInt(stringTokenizer.nextToken());

                graphByAdjacentList.get(startOfEdge).add(endOfEdge);
                graphByAdjacentList.get(endOfEdge).add(startOfEdge);
            }

            for (int i = 1; i <= V; i++) {
                if (vertexColor[i] == 0) {
                    if (!isBipartiteGraph(i)) {
                        stringBuilder.append("NO").append('\n');
                        continue testcase;
                    }
                }
            }

            stringBuilder.append("YES").append('\n');
        }

        System.out.print(stringBuilder);
    }
}