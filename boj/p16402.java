package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class p16402 {
    static int n, m;
    static int[] p;
    static String[] emp;
    static HashMap<String, Integer> empires = new HashMap<>();

    static int find(int x) {
        return p[x] == x ? x : (p[x] = find(p[x]));
    }

    static void union(int a, int b, int t) {
        if (t == 2) {
            int temp = a;
            a = b;
            b = temp;
        }

        int pa = find(a);
        b = find(b);
        if (pa == b) {
            p[a] = a;
            p[b] = a;
        }
        else {
            p[b] = pa;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        p = new int[n];
        emp = new String[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            st.nextToken();
            st.nextToken();
            String empire = st.nextToken();
            emp[i] = empire;
            empires.put(empire, i);
            p[i] = i;
        }

        StringTokenizer sts;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), ",");

            sts = new StringTokenizer(st.nextToken(), " ");
            sts.nextToken();
            sts.nextToken();
            String empireA = sts.nextToken();

            sts = new StringTokenizer(st.nextToken(), " ");
            sts.nextToken();
            sts.nextToken();
            String empireB = sts.nextToken();

            int t = Integer.parseInt(st.nextToken());

            union(empires.get(empireA), empires.get(empireB), t);
        }

        ArrayList<String> suzerains = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (find(i) == i) {
                suzerains.add(emp[i]);
            }
        }
        suzerains.sort(null);
        StringBuilder sb = new StringBuilder();

        sb.append(suzerains.size()).append('\n');
        for (String x : suzerains) {
            sb.append("Kingdom of ").append(x).append('\n');
        }

        System.out.println(sb);
    }
}