import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String source = br.readLine();
        StringBuilder sbb = new StringBuilder();
        char[] sb = source.toCharArray();
        char[] temp = source.toCharArray();
        int l = source.length();

        int Q = Integer.parseInt(br.readLine());
        int x, y;
        int c;
        while (Q-- != 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            String t = st.nextToken();
            x = Integer.parseInt(st.nextToken());
            switch (t) {
                case "1":
                    y = Integer.parseInt(st.nextToken()) + 1;
                    c = y - x;
                    System.arraycopy(sb, x, temp, 0, c);
                    System.arraycopy(sb, 0, sb, c, x);
                    System.arraycopy(temp, 0, sb, 0, c);
                    break;
                case "2":
                    y = Integer.parseInt(st.nextToken()) + 1;
                    c = y - x;
                    System.arraycopy(sb, x, temp, 0, c);
                    System.arraycopy(sb, y, sb, x, l-c-x);
                    System.arraycopy(temp, 0, sb, l-c, c);
                    break;
                case "3":
                    sbb.append(sb[x]).append('\n');
            }
        }
        System.out.println(sbb);
    }
}