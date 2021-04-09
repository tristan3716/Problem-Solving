import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static class DisjointSet {
        int n;

        private int[] parent;
        private int[] size;
        private int[] rank;

        public DisjointSet(int n) {
            this.n = n;

            parent =new int[n];
            size = new int[n];
            rank = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
                rank[i] = 0;
            }
        }

        public int find(int x){
            if (parent[x] == x) {
                return x;
            }
            else {
                return parent[x] = find(parent[x]);
            }
        }

        public int sizeOf(int x){
            return size[find(x)];
        }

        public void union(int p, int q){
            p = find(p);
            q = find(q);
            if (p == q)
                return;
            if (rank[p] < rank[q]) {
                parent[p] = q;
                size[q] += size[p];
            }
            else {
                parent[q] = p;
                size[p] += size[q];
            }
            if (rank[p] == rank[q])
                rank[p]++;
        }
    }

    public static void main(String[] args)  throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        DisjointSet ds = new DisjointSet(n);
        int k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; ++i) {
            String [] strs = br.readLine().split(" ");
            int a = Integer.parseInt(strs[0])-1;
            int b = Integer.parseInt(strs[1])-1;
            ds.union(a, b);
        }

        System.out.println(ds.sizeOf(0)-1);
    }
}