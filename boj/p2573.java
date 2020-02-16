/**
 * @title 빙산 G4
 * @see https://www.acmicpc.net/problem/2573
 * @since 2020.02.07
 * @category bfs, simulation, graph
 * @complexity O(10 * n * C) -> 1004ms (n = 300, C <= 10000, t = 10n...?)
 * @description
 *      뇌절.
 *      빙산 데이터를 map에 저장해서 효율적인 무언가를 해보려했던 듯.
 *      결국 구현 난이도가 높아지고, 머리만 아팠음.
 *      단순 bfs/dfs로 빙산 melting, connectivity check을 했으면 좀 편했을 듯
 */

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class IO {
    static BufferedReader br;
    static StringTokenizer st;

    IO() {
        br = new BufferedReader(new InputStreamReader(System.in));
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
}

class Pos {
    int y;
    int x;

    public Pos(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

class N {
    int life;
    Pos p;
    int nearCount;

    public N(int life, Pos p) {
        super();
        this.life = life;
        this.p = p;
        this.nearCount = 4;
    }

    public void increaseNear() {
        this.nearCount++;
    }

    @Override
    protected N clone() throws CloneNotSupportedException {
        N c = new N(this.life, this.p);
        c.nearCount = this.nearCount;
        return c;
    }

    @Override
    public String toString() {
        return "N [life=" + life + ", " + (p != null ? "p=" + p + ", " : "") + "nearCount=" + nearCount + "]";
    }


}

public class Main {
    static int[][] dir = {{-1,0},{1,0},{0,1},{0,-1}};

    static int n;
    static int m;

    static int[][] arr;

    static Map<String, N> map = new HashMap<>();
    static Map<String, N> copied = new HashMap<>();

    static String getKey(int y, int x) {
        return "" + (y * (m) + x);
    }

    static void melt() throws CloneNotSupportedException {
        for (Map.Entry<String, N> e : map.entrySet()) { // melt
            e.getValue().life -= e.getValue().nearCount;
        }

        for (Map.Entry<String, N> e : map.entrySet()) { // clone
            copied.put(e.getKey(), e.getValue().clone());
        }

        for (Map.Entry<String, N> e : copied.entrySet()) { // check adjoining sea
            N current = e.getValue();

            if (current.life <= 0) {
                for (int [] d : dir) {
                    int ny = current.p.y + d[0];
                    int nx = current.p.x + d[1];
                    if (map.containsKey(getKey(ny, nx))) {
                        map.get(getKey(ny, nx)).increaseNear();
                    }
                }
                arr[e.getValue().p.y][e.getValue().p.x] = 0;
                map.remove(e.getKey());
            }
        }
        copied.clear();
    }

    static void bfs(int i, int j) { // make iceberg-map
        boolean[][] visit = new boolean[n][m];
        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(i, j));

        while (!q.isEmpty()) {
            Pos t = q.poll();
            int cy = t.y;
            int cx = t.x;

            map.put(getKey(cy, cx), new N(arr[cy][cx], t));

            int near = 4;
            for (int [] d : dir) {
                int ny = cy + d[0];
                int nx = cx + d[1];
                if (arr[ny][nx] != 0) {
                    near--;
                    if (visit[ny][nx] != true) {
                        visit[ny][nx] = true;
                        q.offer(new Pos(ny, nx));
                    }
                }
            }
            map.get(getKey(cy, cx)).nearCount = near;
        }
    }

    static int hasCompleteConnectivity() { // check iceberg-connectivity
        int targetSize = map.size();
        int bfsCount = 0;
        N start = null;
        for (Map.Entry<String, N> e : map.entrySet()) { // melt
            start = e.getValue();
            break;
        }
        if (targetSize == 1 || start == null)
            return 2;

        boolean[][] visit = new boolean[n][m];
        Queue<Pos> q = new LinkedList<>();
        q.offer(start.p);

        while (!q.isEmpty()) {
            Pos t = q.poll();
            int cy = t.y;
            int cx = t.x;

            for (int [] d : dir) {
                int ny = cy + d[0];
                int nx = cx + d[1];
                if (arr[ny][nx] != 0) {
                    if (visit[ny][nx] != true) {
                        visit[ny][nx] = true;
                        bfsCount++;
                        q.offer(new Pos(ny, nx));
                    }
                }
            }
        }

        return (bfsCount == targetSize) ? 0 : 1;
    }

    public static void main(String[] args) throws IOException, CloneNotSupportedException {
        IO io = new IO();

        n = io.nextInt();
        m = io.nextInt();

        arr = new int[n][m];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = io.nextInt();
            }
        }

        findIce:
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] != 0) {
                    bfs(i, j);
                    break findIce;
                }
            }
        }

        int time = 0;
        while (true) {
            melt();

            time++;

            int res = hasCompleteConnectivity();

            if (res == 1) {
                System.out.println(time);
                break;
            }
            else if (res == 2) {
                System.out.println(0);
                break;
            }
        }
    }
}