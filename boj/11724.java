import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static List<Integer>[] graph;
	static boolean[] visit;
	
	static void dfs(int v) {
		visit[v] = true;
		for (Integer x : graph[v]) {
			if (!visit[x]) {
				dfs(x);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int m = sc.nextInt();
		
		graph = new List[n + 1];
		visit = new boolean[n + 1];
		for (int i = 1; i <= n; ++i) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < m; ++i) {
			int s = sc.nextInt();
			int e = sc.nextInt();
			graph[s].add(e);
			graph[e].add(s);
		}
		
		int cnt = 0;
		for (int i = 1; i <= n; ++i) {
			if (!visit[i]) {
				dfs(i);
				cnt++;
			}
		}
		
		System.out.println(cnt);
		
	}
}