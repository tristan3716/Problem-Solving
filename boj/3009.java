import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<String> x = new ArrayList<>();
        List<String> y = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            String a = st.nextToken();
            if (x.contains(a)) {
                x.remove(a);
            } else {
                x.add(a);
            }
            String b = st.nextToken();
            if (y.contains(b)) {
                y.remove(b);
            } else {
                y.add(b);
            }
        }

        System.out.println(x.get(0) + " " + y.get(0));
    }
}
