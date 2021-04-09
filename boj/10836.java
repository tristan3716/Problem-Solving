import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[] flat = new int[2 * m - 1];
        Arrays.fill(flat, 1);

        int[][] map = new int[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = 1;
            }
        }
        int zz;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int idx = 0;
            for (int j = 0; j < 3; j++) {
                zz = Integer.parseInt(st.nextToken());
                for (int k = 0; k < zz; k++) {
                    flat[idx++] += j;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0) {
                    sb.append(flat[m - 1 + j]).append(' ');
                } else if (j == 0) {
                    sb.append(flat[m - 1 - i]).append(' ');
                } else {
                    sb.append(Math.max(flat[m - 1 + j], flat[m - 1 - i])).append(' ');
                }
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
}
