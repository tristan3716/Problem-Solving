import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine()) + 1;
        for (int tc = 1; tc < t; tc++) {
            sb.append('#').append(tc).append(' ');
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());
            int ex = Integer.parseInt(st.nextToken());
            int ey = Integer.parseInt(st.nextToken());

            int dx = Math.abs(sx - ex);
            int dy = Math.abs(sy - ey);

            int diff = Math.max(dx, dy);
            int dist = dx + dy;

            if (dist % 2 == 0) {
                sb.append(diff * 2).append('\n');
            } else {
                sb.append(diff * 2 - 1).append('\n');
            }

        }
        System.out.println(sb);
    }
}