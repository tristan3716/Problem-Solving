import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p1916 {
    private static class Edge {
        int e, c;

        public Edge(int e, int c) {
            this.e = e;
            this.c = c;
        }
    }

    private static class Vertex implements Comparable<Vertex> {
        int v;
        int cost;

        public Vertex(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Vertex vertex) {
            return this.cost - vertex.cost;
        }
    }

    static int[] distance = new int[20005];

    private static final int INF = Integer.MAX_VALUE/3;

    private static void dijkstra(int start) {
        Arrays.fill(distance, INF);
        PriorityQueue<Vertex> q = new PriorityQueue<>();


        distance[start] = 0;
        q.offer(new Vertex(start, distance[start]));

        while (!q.isEmpty()) {
            Vertex x = q.poll();
            int v = x.v;
            int cost = x.cost;

            if (distance[v] >= cost) {
                for (Edge e : graphByAdjacentList.get(v)) {
                    int n = e.e;
                    int w = e.c + cost;

                    if (distance[n] > w) {
                        distance[n] = w;
                        q.offer(new Vertex(n, w));
                    }
                }
            }
        }
    }

    static int V;
    static int E;
    static int S;
    static int D;
    static ArrayList<ArrayList<Edge>> graphByAdjacentList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        V = Integer.parseInt(br.readLine());
        E = Integer.parseInt(br.readLine());

        StringTokenizer stringTokenizer;
        graphByAdjacentList.add(null);
        for (int i = 0; i < V; i++) {
            graphByAdjacentList.add(new ArrayList<>());
        }

        while (E-- != 0) {
            stringTokenizer = new StringTokenizer(br.readLine(), " ");
            int startOfEdge = Integer.parseInt(stringTokenizer.nextToken());
            int endOfEdge = Integer.parseInt(stringTokenizer.nextToken());
            int cost = Integer.parseInt(stringTokenizer.nextToken());
            if (endOfEdge > D) {
                continue;
            }

            graphByAdjacentList.get(startOfEdge).add(new Edge(endOfEdge, cost)); // Directed
            //graphByAdjacentList.get(endOfEdge).add(new Edge(startOfEdge, cost)); // Undirected
        }
        stringTokenizer = new StringTokenizer(br.readLine(), " ");
        S = Integer.parseInt(stringTokenizer.nextToken());
        D = Integer.parseInt(stringTokenizer.nextToken());

        dijkstra(S);

        System.out.println(distance[D]);
    }
}

