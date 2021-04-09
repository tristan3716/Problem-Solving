import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static boolean[] getCandidate(final boolean[] infected, final int n, final ArrayList<ArrayList<Integer>> groups) {
        boolean[] infectedCandidate = new boolean[n + 1];
        System.arraycopy(infected, 1, infectedCandidate, 1, n);
        for (int i = groups.size() - 1; i >= 0; i--) {
            boolean hasNotInfected = false;
            boolean hasInfected = false;
            for (int x : groups.get(i)) {
                if (infectedCandidate[x]) {
                    hasInfected = true;
                } else {
                    hasNotInfected = true;
                }
            }

            if (hasInfected && hasNotInfected) {
                for (int x : groups.get(i)) {
                    infectedCandidate[x] = false;
                }
            }
        }
        return infectedCandidate;
    }

    private static boolean[] simulate(final boolean[] infectedCandidate, final int n, final ArrayList<ArrayList<Integer>> groups) {
        boolean[] infected = new boolean[n + 1];
        System.arraycopy(infectedCandidate, 1, infected, 1, n);
        for (ArrayList<Integer> group : groups) {
            boolean hasInfected = false;
            for (int x : group) {
                if (infected[x]) {
                    hasInfected = true;
                    break;
                }
            }

            if (hasInfected) {
                for (int x : group) {
                    infected[x] = true;
                }
            }
        }

        return infected;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        boolean[] infected = new boolean[n + 1];
        ArrayList<ArrayList<Integer>> groups = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            ArrayList<Integer> group = new ArrayList<>();
            st = new StringTokenizer(br.readLine(), " ");
            int k = Integer.parseInt(st.nextToken());
            for (int j = 0; j < k; j++) {
                group.add(Integer.parseInt(st.nextToken()));
            }
            groups.add(group);
        }
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            boolean isInfect = st.nextToken().equals("1");
            if (isInfect) {
                infected[i + 1] = true;
            }
        }
        boolean[] infectedCandidate = getCandidate(infected, n, groups);
        boolean[] infectedResult = simulate(infectedCandidate, n, groups);

        StringBuilder sb = new StringBuilder();
        if (Arrays.equals(infected, infectedResult)) {
            sb.append("YES\n");
            for (int i = 1; i <= n; i++) {
                sb.append(infectedCandidate[i] ? "1 " : "0 ");
            }
        } else {
            sb.append("NO\n");
        }

        System.out.print(sb);
    }
}
