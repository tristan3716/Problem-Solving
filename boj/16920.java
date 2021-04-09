import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static class Node {
        int r;
        int c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static final int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        final int n = Integer.parseInt(st.nextToken());
        final int m = Integer.parseInt(st.nextToken());
        final int p = Integer.parseInt(st.nextToken());

        int[] s = new int[p + 1];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= p; i++) {
            s[i] = Integer.parseInt(st.nextToken());
        }

        int[][] map = new int[n + 2][m + 2];

        for (int i = 0; i < n + 2; i++) {
            map[i][0] = 10;
            map[i][m + 1] = 10;
        }
        for (int i = 0; i < m + 2; i++) {
            map[0][i] = 10;
            map[n + 1][i] = 10;
        }

        final int[] share = new int[11];

        final Queue<Integer> turn = new LinkedList<>();
        final Queue<Node>[] q = new Queue[p + 1];
        int totalQueueSize = 0;
        for (int i = 1; i < p + 1; i++) {
            turn.offer(i);
            q[i] = new ArrayDeque<>();
        }
        for (int i = 1; i <= n; i++) {
            final char[] arr = br.readLine().toCharArray();
            for (int j = 1; j <= m; j++) {
                final char ch = arr[j - 1];
                if (ch == '#') {
                    map[i][j] = 10;
                } else if (Character.isDigit(ch)) {
                    final int id = ch - '0';
                    share[id]++;
                    map[i][j] = id;
                    totalQueueSize++;
                    q[id].offer(new Node(i, j));
                }
            }
        }

        while (totalQueueSize != 0) {
            final int currentId = turn.poll();
            final Queue<Node> currentQueue = q[currentId];

            for (int i = 0; i < s[currentId]; i++) {
                if (currentQueue.isEmpty()) {
                    break;
                }

                int queueSize = currentQueue.size();
                for (int j = 0; j < queueSize; j++) {
                    Node current = currentQueue.poll();
                    totalQueueSize--;

                    for (int[] d : directions) {
                        final int nr = current.r + d[0];
                        final int nc = current.c + d[1];

                        if (map[nr][nc] == 0) {
                            map[nr][nc] = currentId;
                            share[currentId]++;
                            totalQueueSize++;
                            currentQueue.offer(new Node(nr, nc));
                        }
                    }
                }
            }

            if (!currentQueue.isEmpty()) {
                turn.offer(currentId);
            }
        }

        for (int i = 1; i <= p; i++) {
            System.out.printf("%d ", share[i]);
        }
    }
}
