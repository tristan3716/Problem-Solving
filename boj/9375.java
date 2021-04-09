import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < t; tc++) {
            Map<String, Integer> map = new HashMap<>();
            int n = Integer.parseInt(br.readLine());
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                st.nextToken();
                String category = st.nextToken();
                if (map.containsKey(category)) {
                    int x = map.get(category);
                    map.replace(category, x+1);
                } else {
                    map.put(category, 1);
                }
            }
            int res = map.values().stream().mapToInt(x -> (x + 1)).reduce(1, (a, b) -> a * b) - 1;
            System.out.println(res);
        }
    }
}
