package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p17839 {
    static Map<String, List<String>> g = new HashMap<>();
    static Set<String> v = new HashSet<>();
    static List<String> r = new ArrayList<>();

    private static void dfs(String cur) {
        if (g.containsKey(cur)) {
            for (String next : g.get(cur)) {
                if (!v.contains(next)) {
                    v.add(next);
                    r.add(next);
                    dfs(next);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        while (n-- != 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            String s = st.nextToken(); st.nextToken();
            String e = st.nextToken();

            if (g.containsKey(s)) {
                g.get(s).add(e);
            } else {
                List<String> c = new ArrayList<>();
                c.add(e);
                g.put(s, c);
            }
        }

        if (g.containsKey("Baba")) {
            StringBuilder sb = new StringBuilder();
            v.add("Baba");
            dfs("Baba");

            r.sort(null);
            for (String x : r) {
                sb.append(x).append('\n');
            }
            System.out.print(sb);
        }
    }
}