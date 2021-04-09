package src;

import java.util.*;

public class p17472 {
    static Scanner sc;
    static int n;
    static int m;
    static int[][] arr;
    static List<Set<Pos>> map;
    static int islandCount;
    static int[][] graph;
    static int edgeCount;

    static {
        sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        arr = new int[n][m];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                arr[i][j] = sc.nextInt();
            }
        }
        map = new ArrayList<>();
    }

    private static boolean isIn(int i, int j){
        return (i >= 0 && j >= 0 && i < n && j < m);
    }
    private static class Pos{
        int r, c;
        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
        static int [][] dirs = {{0, -1}, {0, 1}, {1, 0}, {-1,0}};
    }

    private static void ccl(int i, int j, int count, boolean[][] visit) {
        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(i, j));
        visit[i][j] = true;

        while (!q.isEmpty()) {
            Pos t = q.poll();

            arr[t.r][t.c] = count;
            map.get(count-1).add(t);

            for (int[] dir : Pos.dirs) {
                int nr = t.r + dir[0];
                int nc = t.c + dir[1];

                if (isIn(nr, nc) && !visit[nr][nc] && arr[nr][nc] != 0) {
                    visit[nr][nc] = true;
                    q.offer(new Pos(nr, nc));
                }
            }
        }
    }

    public static int ccl() {
        int count = 1;
        boolean[][] visit = new boolean[n][m];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 1 && !visit[i][j]) {
                    map.add(new HashSet<Pos>());
                    ccl(i, j, count, visit);
                    count++;
                }
            }
        }
        return count-1;
    }

    private static void makeBridge(int i, int j, int label) {
        for (int[] dir : Pos.dirs) {
            int distance = 0;

            int nr = i + dir[0];
            int nc = j + dir[1];

            while (isIn(nr, nc)) {
                if (arr[nr][nc] != 0) {
                    if (arr[nr][nc] != label && distance > 1) {
                        graph[label-1][arr[nr][nc]-1] = Math.min(graph[label-1][arr[nr][nc]-1], distance);
                        graph[arr[nr][nc]-1][label-1] = Math.min(graph[arr[nr][nc]-1][label-1], distance);
                        edgeCount++;
                    }
                    break;
                }
                nr = nr + dir[0];
                nc = nc + dir[1];
                distance += 1;
            }
        }
    }

    public static void makeBridge() {
        graph = new int[islandCount][islandCount];
        for (int[] x : graph) {
            Arrays.fill(x, Integer.MAX_VALUE);
        }

        for (int i = 0; i < islandCount; ++i) {
            for (Pos x : map.get(i)) {
                makeBridge(x.r, x.c, i+1);
            }
        }
    }

    public static void placeBridge() {
        int[][] E = new int[edgeCount][2];
        int[] S = new int[islandCount+1];
        for (int i = 0; i < islandCount+1; ++i) {
            S[i] = i;
        }

        int idx = 0;
        for (int i = 0; i < islandCount; ++i) {
            for (int j = i+1; j < islandCount; j++) {
                if (graph[i][j] != Integer.MAX_VALUE) {
                    E[idx][0] = i;
                    E[idx][1] = j;
                    idx++;
                }
            }
        }
        edgeCount = idx;

        Arrays.sort(E, new Comparator<int[]>() {
            @Override
            public int compare(int[] ints, int[] t1) {
                if ((ints[0] == 0 && ints[1] == 0) || (t1[0] == 0 && t1[1] == 0))
                    return 1;

                if (Arrays.equals(t1, ints)) {
                    return 0;
                }
                return (graph[ints[0]][ints[1]] > graph[t1[0]][t1[1]]) ? 1 : -1;
            }
        });

        int sel = 0;
        int i = 0;
        int sum = 0;
        while (sel < islandCount - 1) {
            if (i == edgeCount) {
                break;
            }
            int v1 = E[i][0];
            int v2 = E[i][1];
            int s1 = S[v1];
            int s2 = S[v2];

            if (s1 != s2) {
                for (int j = 0; j < S.length; ++j) {
                    if (S[j] == s2) {
                        S[j] = s1;
                    }
                }
                sel++;
                sum += graph[v1][v2];
            }
            i++;
        }
        if (sel != islandCount-1) {
            System.out.println(-1);
        } else {
            System.out.println(sum);
        }
    }

    public static void main(String[] args) {
        islandCount = ccl();
        makeBridge();
        placeBridge();
    }
}
