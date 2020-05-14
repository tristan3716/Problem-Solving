package src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class p17471 {
    static int n;
    static int [] populations;
    static List<ArrayList<Integer>> graph;
    static int totalSum;
    static Map<Integer, Integer> sum;
    static int fullSet;

    static {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            n = Integer.parseInt(br.readLine());
            populations = new int[n+1];
            fullSet = (1<<n)-1;

            String[] rc = br.readLine().split(" ");
            for (int i = 1; i <= n; ++i) {
                int val = Integer.parseInt(rc[i-1]);
                populations[i] = val;
                totalSum += val;
            }
            graph = new ArrayList<>();
            for (int i = 0; i <= n; ++i) {
                graph.add(new ArrayList<>());
            }

            for (int i = 1; i <= n; ++i) {
                rc = br.readLine().split(" ");
                int k = Integer.parseInt(rc[0]);
                for (int j = 0; j < k; ++j) {
                    int e = Integer.parseInt(rc[j+1]);
                    graph.get(i-1).add(e-1);
                    graph.get(e-1).add(i-1);
                }
            }
            sum = new HashMap<>();
            br.close();
        }
        catch (Exception e) {}
    }

    static int vCount;
    static void dfs(int v, boolean[] visit, int set) {
        visit[v] = true;
        vCount++;
        for (Integer x : graph.get(v)) {
            if (!visit[x] && contains(x, set)) {
                dfs(x, visit, set);
            }
        }

    }

    static boolean contains(int idx, int bits) {
        return (bits & (1 << idx)) != 0;
    }

    static int calculateSum(int bit, int k) {
        int sum = 0;
        for (int i = 0; i < k; ++i) {
            if (contains(i, bit))
                sum += populations[i+1];
        }

        return sum;
    }

    static int minDiff = Integer.MAX_VALUE;
    static void gerrymandering(final boolean x) {
        ne:
        for (int i = (x ? 0 : 1); i < (1 << n); ++i) {
            int bitCount = 0;
            int K = i;
            while (K != 0) {
                bitCount += K & 1;
                if (bitCount > n/2)
                    continue ne;
                K >>= 1;
            };
            int sumA, sumB;
            if (sum.containsKey(i)) {
                sumA = sum.get(i);
                sumB = totalSum - sumA;
            }
            else {
                int ni = (~i & fullSet);
                if (sum.containsKey(ni)) {
                    sumA = sum.get(ni);
                    sumB = totalSum - sumA;
                } else {
                    sumA = calculateSum(i, n);
                    sum.put(i, sumA);
                    sumB = totalSum - sumA;
                }
            }

            int diff = Math.abs(sumA - sumB);
            if (diff < minDiff) {
                boolean[] visit = new boolean[n+1];
                int totalVisit = 0;
                for (int j = 0; j < n; ++j) {
                    if (contains(j, i) && !visit[j]) {
                        dfs(j, visit, i);
                        totalVisit = vCount;
                        vCount = 0;
                        break;
                    }
                }
                for (int j = 0; j < n; ++j) {
                    if (!contains(j, i) && !visit[j]) {
                        dfs(j, visit, ~i & fullSet);
                        totalVisit += vCount;
                        vCount = 0;
                        break;
                    }
                }
                if (totalVisit == n) {
                    minDiff = diff;
                }
            }
        }
    }

    public static void main(String [] args) {
        gerrymandering(false);

        if (minDiff == Integer.MAX_VALUE) {
            System.out.println(-1);
        }
        else {
            System.out.println(minDiff);
        }
    }
}