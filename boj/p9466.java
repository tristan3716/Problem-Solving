package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class p9466 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int tc = 1; tc <= t; ++tc) {
            int n = sc.nextInt();
            int[] arr = new int[n+5];
            for (int i = 1; i <= n; ++i) {
                arr[i] = sc.nextInt();
            }

            boolean[] decided = new boolean[n+5];
            List<Integer> list = new ArrayList<>();
            boolean[] visit = new boolean[n+5];
            next:
            for (int i = 1; i <= n; ++i) {
                if (!decided[i]) {
                    int startIdx = i;
                    list.clear();
                    list.add(i);
                    int cur = arr[i];
                    while (cur != startIdx) {
                        if (decided[cur] == true) {
                            continue next;
                        }
                        if (visit[cur] == true) {
                            while (!list.isEmpty() && list.get(0) != cur) {
                                list.remove(0);
                            }
                            break;
                        }
                        visit[cur] = true;
                        list.add(cur);
                        cur = arr[cur];
                    }
                    for (int x : list) {
                        decided[x] = true;
                    }
                }
            }
            int cnt = 0;
            for (int i = 1; i <= n; i++) {
                if (decided[i] == false) {
                    cnt++;
                }
            }
            System.out.println(cnt);
        }
    }
}
