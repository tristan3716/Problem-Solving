import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static class Node implements Comparable<Node> {
        int r;
        int c;
        int h;

        public Node(int r, int c, int h) {
            this.r = r;
            this.c = c;
            this.h = h;
        }

        @Override
        public int compareTo(Node node) {
            return this.h - node.h;
        }
    }
    private static final int[][] dir = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][m];
        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();

        for (int i = 1; i < n - 1; i++) {
            pq.offer(new Node(i, 0, arr[i][0]));
            pq.offer(new Node(i, m - 1, arr[i][m - 1]));
            visited[i][0] = true;
            visited[i][m - 1] = true;
        }
        for (int i = 1; i < m - 1; i++) {
            pq.offer(new Node(0, i, arr[0][i]));
            pq.offer(new Node(n - 1, i, arr[n - 1][i]));
            visited[0][i] = true;
            visited[n - 1][i] = true;
        }

        visited[0][0] = visited[0][m - 1] = visited[n - 1][0] = visited[n - 1][m - 1] = true;

        int answer = 0;

        int[][] vi = new int[n][m];
        while (!pq.isEmpty()) {
            final Node current = pq.poll();

            for (int[] d : dir) {
                int nr = current.r + d[0];
                int nc = current.c + d[1];

                if (nr >= 0 && nc >= 0 && nr < n && nc < m && !visited[nr][nc]) {
                    visited[nr][nc] = true;

                    if (current.h > arr[nr][nc]) {
                        vi[nr][nc] = current.h - arr[nr][nc];
                        answer += current.h - arr[nr][nc];
                        pq.offer(new Node(nr, nc, current.h));
                    } else {
                        pq.offer(new Node(nr, nc, arr[nr][nc]));
                    }

                }
            }
        }

        System.out.println(answer);
    }
}