package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p13549 {
    private static class Pos {
        int p;
        int d;

        public Pos(int p, int d) {
            this.p = p;
            this.d = d;
        }
    }

    static int N;
    static int K;
    static int np;
    final static int MAX = 100000;

    static int[] graph = new int[MAX + 5];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if (N == K) {
            System.out.print("0");
            System.exit(0);
        }
        if (N > K) {
            sb.append(N-K);
            System.out.print(sb);
            System.exit(0);
        }
        Arrays.fill(graph, -1);

        Deque<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(N, 0));

        int answer = Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            Pos c = q.pollFirst();

            np = c.p * 2;
            if (np >= 0 && np <= MAX) {
                if (graph[np] == -1) {
                    graph[np] = c.p;
                    if (np == K) {
                        answer = c.d;
                        break;
                    }
                    q.offerFirst(new Pos(np, c.d));
                }
            }
            np = c.p - 1;
            if (np >= 0 && np <= MAX) {
                if (graph[np] == -1) {
                    graph[np] = c.p;
                    if (np == K) {
                        answer = c.d + 1;
                        break;
                    }
                    q.offerLast(new Pos(np, c.d + 1));
                }
            }
            np = c.p + 1;
            if (np >= 0 && np <= MAX) {
                if (graph[np] == -1) {
                    graph[np] = c.p;
                    if (np == K) {
                        answer = c.d + 1;
                        break;
                    }
                    q.offerLast(new Pos(np, c.d + 1));
                }
            }
        }

        sb.insert(0, answer);

        System.out.println(sb);
    }
}