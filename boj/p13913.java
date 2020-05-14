package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p13913 {
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
            System.out.print("0\n");
            System.out.print(N);
            System.exit(0);
        }
        if (N > K) {
            sb.append(N-K).append('\n');
            for (int i = N; i >= K; i--) {
                sb.append(i).append(' ');
            }
            System.out.print(sb);
            System.exit(0);
        }
        Arrays.fill(graph, -1);

        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(N, 0));

        int answer = -1;
        while (!q.isEmpty()) {
            Pos c = q.poll();

            np = c.p + 1;
            if (np >= 0 && np <= MAX) {
                if (graph[np] == -1) {
                    graph[np] = c.p;
                    if (np == K) {
                        answer = c.d + 1;
                        break;
                    }
                    q.offer(new Pos(np, c.d + 1));
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
                    q.offer(new Pos(np, c.d + 1));
                }
            }
            np = c.p * 2;
            if (np >= 0 && np <= MAX) {
                if (graph[np] == -1) {
                    graph[np] = c.p;
                    if (np == K) {
                        answer = c.d + 1;
                        break;
                    }
                    q.offer(new Pos(np, c.d + 1));
                }
            }
        }

        int c = K;
        while(true) {
            sb.insert(0, ' ').insert(0, c);
            if (c == N)
                break;
            c = graph[c];
        }
        sb.insert(0, '\n').insert(0, answer);

        System.out.println(sb);
    }
}