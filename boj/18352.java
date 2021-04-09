import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

class Dis {
	int destination;
	int distance;
	public Dis(int start, int distance) {
		super();
		this.destination = start;
		this.distance = distance;
	}
}

class FastIO {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    FastIO() {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    String next() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }

        return st.nextToken();
    }

    int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    long nextLong() throws IOException {
        return Long.parseLong(next());
    }

    double nextDouble() throws IOException {
        return Double.parseDouble(next());
    }

    String nextLine() throws IOException {
        return br.readLine();
    }

    void write(double d) throws IOException {
        bw.write(String.valueOf(d));
        close();
    }

    void write(char c) throws IOException {
        bw.write(c);
        close();
    }

    void write(int i) throws IOException {
        bw.write(String.valueOf(i));
        close();
    }

    void write(long l) throws IOException {
        bw.write(String.valueOf(l));
        close();
    }

    void write(StringBuilder sb) throws IOException {
        bw.write(sb.toString().trim());
        close();
    }

    void write(String str) throws IOException {
        bw.write(str.trim());
        close();
    }

    void close() throws IOException {
        bw.close();
        br.close();
    }
}

public class Main {
	//static Scanner sc = new Scanner(System.in);
	static FastIO io = new FastIO();
	
	static List<Integer>[] makeGraph() throws IOException{
		@SuppressWarnings("unchecked")
		List<Integer>[] graph = new List[N + 1];
		for (int i = 1; i < graph.length; ++i) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i += 1) {
			int s = io.nextInt(); //sc.nextInt();
			int e = io.nextInt(); //sc.nextInt();

			graph[s].add(e);
			//graph[e].add(s);
		}
		return graph;
	}
	
	static List<Integer> bfs(List<Integer>[] graph, int start, int destinationDepth){
		List<Integer> path = new ArrayList<>();
		
		Queue<Dis> q = new LinkedList<>();
		boolean[] visit = new boolean[graph.length];

		q.add(new Dis(start, 0));

		while (!q.isEmpty()) {
			Dis current = q.poll();
			
			if (current.distance > destinationDepth) {
				continue;
			}
			if (visit[current.destination] == true) {
				continue;
			}
			if (current.distance == destinationDepth) {
				path.add(current.destination);
			}
			
			visit[current.destination] = true;
			for (int i = 0; i < graph[current.destination].size(); ++i) {
				int next = graph[current.destination].get(i);
				if (visit[next] == false) {
					q.add(new Dis(next, current.distance + 1));
				}
			}
		}

		return path;
	}
	
	static int N;
	static int M;
	static int K;
	static int X;
	public static void main(String[] args) throws IOException {
		//도시의 개수 N, 도로의 개수 M, 거리 정보 K, 출발 도시의 번호 X
		N = io.nextInt(); //sc.nextInt();
		M = io.nextInt(); //sc.nextInt();
		K = io.nextInt(); //sc.nextInt();
		X = io.nextInt(); //sc.nextInt();
		
		List<Integer>[] g = makeGraph();
		
		StringBuilder res = new StringBuilder();
		List<Integer> path = bfs(g, X, K);
		
		if (path.isEmpty()) {
			res.append(-1);
			//System.out.println(-1);
		}
		else {
			path.sort(null);
			for (Integer x : path) {
				res.append(x).append('\n');
				//System.out.println(x);
			}
		}
		io.write(res);
	}
}