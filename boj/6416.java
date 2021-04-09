import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int k = 1;
        boolean isFinished;
        endProblem:
        while (true) {
            isFinished = false;
            String first = null;
            Set<String> root = new HashSet<>();
            Set<String> nodes = new HashSet<>();
            Set<String> rootEliminate = new HashSet<>();
            endTestcase:
            while (true) {
                st = new StringTokenizer(br.readLine());
                while (st.hasMoreTokens()) {
                    String a = st.nextToken();
                    String b = st.nextToken();
                    long x = Long.parseLong(a);
                    long y = Long.parseLong(b);

                    if (x > 100000000L || y > 100000000L) {
                        throw new RuntimeException();
                    }

                    if ("-1".equals(a)) {
                        break endProblem;
                    }
                    if ("0".equals(a)) {
                        break endTestcase;
                    }

                    root.add(a);
                    if(nodes.contains(b)) {
                        if (!isFinished) {
                            sb.append("Case ").append(k).append(" is not a tree.\n");
                            isFinished = true;
                        }
                    }
                    if (first == null) {
                        first = b;
                    }
                    nodes.add(b);
                    rootEliminate.add(b);
                }
            }

            rootEliminate.forEach(root::remove);
            if (root.size() != 1 && !nodes.isEmpty()) {
                sb.append("Case ").append(k).append(" is not a tree.\n");
                isFinished = true;
            }

            if (!isFinished) {
                sb.append("Case ").append(k).append(" is a tree.\n");
            }

            k++;
        }

        System.out.print(sb);
    }
}
