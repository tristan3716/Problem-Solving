package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p16724 {
    public static void main(String[] args) throws IOException {
        int n, m;
        char [][] map;
        int [][] visit;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        visit = new int[n][m];

        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int cnt = 0;
        int id = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visit[i][j] == 0) {
                    int nr = i;
                    int nc = j;
                    while (visit[nr][nc] == 0) {
                        visit[nr][nc] = id;
                        char ch = map[nr][nc];
                        switch (ch) {
                            case 'D':
                                nr++; break;
                            case 'U':
                                nr--; break;
                            case 'R':
                                nc++; break;
                            case 'L':
                                nc--; break;
                        }
                    }
                    if (visit[nr][nc] == id++) {
                        cnt++;
                    }
                }
            }
        }
        System.out.println(cnt);
    }
}
