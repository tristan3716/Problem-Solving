import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final int dayTime = 60 * 9;

    private static int[][] graph;
    private static int[][] spot;
    private static int m;
    private static int air;
    private static int[] spots;
    private static int[] hotels;

    private static void parse() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new int[n][n];
        char[] type = new char[n];
        spot = new int[n][2];
        visited = new boolean[n];

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = i + 1; j < n; j++) {
                graph[j][i] = graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int spotCount = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            char ch = st.nextToken().charAt(0);
            if (ch == 'P') {
                spot[i][0] = Integer.parseInt(st.nextToken());
                spot[i][1] = Integer.parseInt(st.nextToken());
                spotCount++;
            } else if (ch == 'A') {
                air = i;
            }
            type[i] = ch;
        }

        spots = new int[spotCount];
        hotels = new int[n - spotCount - 1];
        int si = 0;
        int hi = 0;
        for (int i = 0; i < n; i++) {
            if (type[i] == 'P') {
                spots[si++] = i;
            } else if (type[i] == 'H') {
                hotels[hi++] = i;
            }
        }
    }

    private static boolean[] visited;


    private static int maxSatisfaction = 0;
    private static Deque<Integer> path = new ArrayDeque<>();
    private static Deque<Integer> maxPath;

    private static void dfs(int currentSpot, int currentDay, int todayTime, int totalSatisfaction) {
        boolean visitable = false;
        for (int next : spots) {
            if (!visited[next]) {
                int nt = todayTime + graph[currentSpot][next] + spot[next][0];
                if (nt <= dayTime) {
                    visitable = true;
                    path.addLast(next);
                    visited[next] = true;
                    dfs(next, currentDay, nt, totalSatisfaction + spot[next][1]);
                    visited[next] = false;
                    path.pollLast();
                }
            }
        }
        if (!visitable) {
            if (currentDay == m) {
                int nt = todayTime + graph[currentSpot][air];
                if (nt <= dayTime) {
                    if (maxSatisfaction < totalSatisfaction) {
                        maxSatisfaction = totalSatisfaction;
                        maxPath = new ArrayDeque<>(path);
                    }
                }
            } else {
                for (int next : hotels) {
                    int nt = todayTime + graph[currentSpot][next];
                    if (nt <= dayTime) {
                        path.addLast(next);
                        dfs(next,
                                currentDay + 1,
                                0,
                                totalSatisfaction);
                        path.pollLast();
                    }
                }

            }
        }
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine()) + 1;
        for (int tc = 1; tc < t; tc++) {
            maxSatisfaction = 0;
            parse();
            dfs(air,
                    1,
                    0,
                    0);

            sb.append('#').append(tc).append(' ').append(maxSatisfaction);
            if (maxSatisfaction != 0) {
                for (int x : maxPath) {
                    sb.append(' ').append((x+1));
                }
                sb.append(' ').append((air+1));
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }
}