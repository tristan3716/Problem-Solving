/**
 * @title 여행 가자 G4
 * @see https://www.acmicpc.net/problem/1976
 * @since 2020.02.16
 * @category graph, disjoint-set
 * @complexity O(n^2) -> 416ms (n = 200, m = 1000)
 * @description
 *      union-find 연습 문제
 *      불필요한 size가 제거되지 않았아서 발생한 오버헤드,
 *      union-find의 연산은 O(a(n)) 이고
 *      입력이 무향그래프 인접행렬로 주어지므로 (n^2)/2까지 줄일 여지가 있음
 */

import java.util.Scanner;

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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        DisjointSet ds = new DisjointSet(n);
        int k = sc.nextInt();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; j++) {
                int t = sc.nextInt();
                if (t == 1){
                    ds.union(i, j);
                }
            }
        }

        int root = ds.find(sc.nextInt()-1);
        for (int i = 1; i < k; i++) {
            if (ds.find(sc.nextInt()-1) != root){
                System.out.println("NO");
                System.exit(0);
            }
        }
        System.out.println("YES");
    }
}