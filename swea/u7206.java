import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    private int[] d = new int[100000];

    int maxDPValue = -1;
    private void calculate(int value, int multiply, int depth) {
        if (d[value * multiply] != 0 || value < 10) {
            maxDPValue = Math.max(maxDPValue, d[value * multiply]);
        }

        for (int i = 10; i < value; i *= 10) {
            calculate(value / i, multiply * (value % i), depth+1);
        }
    }

    private void calculate(int value) {
        maxDPValue = 0;
        calculate(value, 1, 0);
        d[value] = maxDPValue + 1;
    }

    private void init() {
        for (int i = 0; i < 10; i++) {
            d[i] = 0;
        }
    }

    private void tabulation() {
        init();
        for (int i = 10; i < 100000; i++) {
            calculate(i);
        }
    }

    private void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc < t + 1; tc++) {
            int n = Integer.parseInt(br.readLine());
            sb.append('#').append(tc).append(' ').append(d[n]).append('\n');
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        Solution solution = new Solution();
        solution.tabulation();
        solution.solve();
    }
}