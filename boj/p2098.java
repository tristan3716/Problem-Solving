package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p2098 {
    private static class Solver {
        final int n;
        final int fullBit;
        final int [][] dist;
        final int [][] cache;
        public Solver () throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            n = Integer.parseInt(br.readLine());
            fullBit = (1 << n) - 1;
            dist = new int[n][n];
            cache = new int[n][1 << n];

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < n; j++) {
                    dist[i][j] = Integer.parseInt(st.nextToken());
                }
            }
        }
        public int tsp(int cur, int visited) {
            if (cache[cur][visited] != 0)
                return cache[cur][visited];

            if (visited == fullBit) {
                if (dist[cur][0] != 0) return dist[cur][0];
                else return 0x3fffffff;
            }

            int result = 0x3fffffff;
            for (int next = 0; next < n; next++) {
                if ((visited & (1 << next)) != 0) continue;
                if (dist[cur][next] == 0) continue;

                result = Math.min(result, tsp(next, visited | (1 << next)) + dist[cur][next]);
            }
            return (cache[cur][visited] = result);
        }
    }

    public static void main(String[] args) throws IOException {
        Solver s = new Solver();
        System.out.println(s.tsp(0, 1));
    }
}