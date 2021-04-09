package src;

import java.util.Scanner;

public class p3780 {
    private static class DisjointSet {
        int n;

        private int[] parent;
        private int[] size;

        public DisjointSet(int n) {
            this.n = n;

            parent =new int[n];
            size = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 0;
            }
        }

        public int find(int x){
            if (parent[x] == x) {
                return x;
            }
            else {
                int p = find(parent[x]);
                size[x] += size[parent[x]];
                return parent[x] = p;
            }
        }

        public int sizeOf(int x){
            return size[x];
        }

        private int cost(int p, int q){
            return Math.abs(p - q) % 1000;
        }

        public void union(int p, int q){
            parent[p] = q;
            size[p] += cost(p,q);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int tc = 0; tc < t; ++tc) {
            int n = sc.nextInt();
            DisjointSet ds = new DisjointSet(n+1);
            test:       while (true) {
                char command = sc.next().charAt(0);
                switch (command) {
                    case 'E':
                        int tmp = sc.nextInt();
                        ds.find(tmp);
                        System.out.println(ds.sizeOf(tmp));
                        break;
                    case 'I':
                        ds.union(sc.nextInt(), sc.nextInt());
                        break;
                    case 'O':
                        break test;
                }
            }
        }
    }

}