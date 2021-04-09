import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        int m;
        char [] map;
        int [] visit;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        m = Integer.parseInt(st.nextToken());
        visit = new int[m];

        map = br.readLine().toCharArray();

        int cnt = 0;
        int id = 1;

        for (int j = 0; j < m; j++) {
            if (visit[j] == 0) {
                int nc = j;
                while (visit[nc] == 0) {
                    visit[nc] = id;
                    char ch = map[nc];
                    switch (ch) {
                        case 'E':
                            nc++; break;
                        case 'W':
                            nc--; break;
                    }
                }
                if (visit[nc] == id++) {
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }
}
