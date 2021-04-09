import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		Solver s = new Solver();
		s.solve();
	}
}

class Solver {
	final int V, E;
	ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	ArrayList<ArrayList<Integer>> graphR = new ArrayList<>();
	ArrayList<ArrayList<Integer>> scc = new ArrayList<>();
	Stack<Integer> s = new Stack<>();
	boolean[] visit;

	public Solver () throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		visit = new boolean[V+1];

		for (int i = 0; i <= V; i++) {
			graph.add(new ArrayList<>());
			graphR.add(new ArrayList<>());
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
			graphR.get(b).add(a);
		}
	}

	void dfs(int cur) {
		for (int next : graph.get(cur)) {
			if (!visit[next]) {
				visit[next] = true;
				dfs(next);
			}
		}
		s.push(cur);
	}
	void dfs() {
		for (int i = 1; i <= V; i++) {
			if (!visit[i]) {
				visit[i] = true;
				dfs(i);
			}
		}
	}

	void dfsR(int cur, int id) {
		scc.get(id).add(cur);
		for (int next : graphR.get(cur)) {
			if (!visit[next]) {
				visit[next] = true;
				dfsR(next, id);
			}
		}
	}
	void dfsR() {
		while (!s.isEmpty()) {
			int cur = s.pop();
			if (!visit[cur]) {
				visit[cur] = true;
				scc.add(new ArrayList<>());
				dfsR(cur, scc.size() - 1);
			}
		}
	}

	public void solve() {
		dfs();
		Arrays.fill(visit, false);
		dfsR();
		for (ArrayList<Integer> integers : scc) {
			integers.sort(Integer::compareTo);
		}
		scc.sort(Comparator.comparingInt(a -> a.get(0)));
		StringBuilder sb = new StringBuilder();
		sb.append(scc.size()).append('\n');
		for (ArrayList<Integer> cc : scc) {
			for (int x : cc) {
				sb.append(x).append(' ');
			}
			sb.append(-1).append('\n');
		}

		System.out.println(sb);
	}
}