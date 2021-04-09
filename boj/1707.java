import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static boolean isBipartiteGraph(int startVertex) {
        Queue<Integer> queue = new LinkedList<>(); // queue for bfs

        int currentColor = 1; // colorA (currentVertex's color)
        int nextColor = -1;   // colorB (adjacentVertex's color)

        queue.offer(startVertex); // add startVertex
        vertexColor[startVertex] = currentColor; // set color of startVertex

        // 그래프의 깊이(==시작정점으로부터의 거리)단위로 색을 바꿔가며 bfs 진행
        // 같은 거리를 가지는 모든 정점은 같은 색으로 칠해져야함
        while (!queue.isEmpty()) { // for in Connected component
            nextColor = -currentColor; // set nextColor to -currentColor

            int queueSize = queue.size(); // get queue's size (all vertex has same depth, in bfs)
            while (queueSize-- != 0) { // in range [1, qs]
                int currentVertex = queue.poll(); // poll queue's first

                for (int nextVertex : graphByAdjacentList.get(currentVertex)) { // for in currentVertex's AdjacentVertex
                    if (vertexColor[nextVertex] == 0) { // if not visit yet
                        vertexColor[nextVertex] = nextColor; // set color to nextColor
                        queue.offer(nextVertex); // offer nextVertex
                    } else { // if already visit
                        if (vertexColor[nextVertex] == currentColor) { // if currentVertex and nextVertex has same color
                            return false; // it is not bipartite graph
                        }
                    }
                }
            }

            currentColor = nextColor; // flip color (same as "currentColor = -currentColor;")
        }

        return true; // if traversal is end successful, it is bipartite graph
    }

    static int V;
    static int E;
    static int[] vertexColor = new int[20001];
    static ArrayList<ArrayList<Integer>> graphByAdjacentList = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        /* IO AREA ============================================================ */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // input reader
        StringBuilder stringBuilder = new StringBuilder(); // output reader

        /* SOLVING AREA ======================================================= */
        int T = Integer.parseInt(br.readLine()); // number of testcase
        testcase:
        for (int tc = 1; tc <= T; ++tc) { // in range[1, T]
            /* PARSE AREA ===================================================== */
            StringTokenizer stringTokenizer = new StringTokenizer(br.readLine(), " "); // tokenizer
            V = Integer.parseInt(stringTokenizer.nextToken()); // number of vertex
            E = Integer.parseInt(stringTokenizer.nextToken()); // number of edge

            Arrays.fill(vertexColor, 0); // initialize color of each vertex
            graphByAdjacentList.clear(); // initialize graph
            graphByAdjacentList.add(null); // padding for 1-base
            for (int i = 0; i < V; i++) { graphByAdjacentList.add(new ArrayList<>()); } // add empty edge list

            /* parse edges */
            while (E-- != 0) { // in range [1, E]
                stringTokenizer = new StringTokenizer(br.readLine(), " ");
                int startOfEdge = Integer.parseInt(stringTokenizer.nextToken()); // start of edge
                int endOfEdge = Integer.parseInt(stringTokenizer.nextToken()); // end of edge

                graphByAdjacentList.get(startOfEdge).add(endOfEdge); // add edge
                graphByAdjacentList.get(endOfEdge).add(startOfEdge); // add reversed edge (UN-DIRECTED GRAPH)
            }

            /* SOLVING AREA =================================================== */
            for (int i = 1; i <= V; i++) { // about all vertex ( == all Connected component)
                if (vertexColor[i] == 0) { // if not yet determine vertex's color
                    if (!isBipartiteGraph(i)) { // is it bipartite graph?
                        // if CC is not bipartite graph, append "NO" and continue to next testcase
                        stringBuilder.append("NO").append('\n');
                        continue testcase;
                    }
                }
            }

            // if reach here, graph is bipartite graph
            stringBuilder.append("YES").append('\n');
        }

        System.out.print(stringBuilder); // print answers
    }
}