import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        Solver s = new Solver();
        System.out.println(s.cnt);
    }
}

class Solver {
    int n;
    int[] selectOf;
    int[] connected;
    boolean[] visit;
    int cnt;

    private void dfs(int c, boolean mafia) {
        cnt += mafia ? 1 : 0;
        int next = selectOf[c];
        if ((--connected[next] == 0 || mafia) && !visit[next]) {
            visit[next] = true;
            dfs(next, !mafia);
        }
    }

    public Solver() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        selectOf = new int[n];
        connected = new int[n];
        visit = new boolean[n];
        for (int i = 0; i < n; i++) {
            int t = Integer.parseInt(br.readLine()) - 1;
            selectOf[i] = t;
            connected[t] += 1;
        }
        for (int i = 0; i < n; ++i) {
            if (connected[i] == 0 && !visit[i]) {
                visit[i] = true;
                dfs(i, true);
            }
        }

        for (int i = 0; i < n; ++i) {
            if (!visit[i]) {
                visit[i] = true;
                dfs(i, false);
            }
        }
    }
}