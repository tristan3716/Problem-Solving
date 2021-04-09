import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[][] dice = new int[10001][6];
    private static int answer = 0;
    private static int sum = 0;

    private static void calculate(final int n, int current, int prev) {
        if (current == n) {
            answer = Math.max(answer, sum);
        } else {
            for (int i = 0; i < 6; i++) {
                if (dice[current][i] != prev) {
                    continue;
                }

                int temp = 0;
                for (int j = 0; j < 3; j++) {
                    if (j == i || j + 3 == i) {
                        continue;
                    }

                    temp = Math.max(temp, dice[current][j]);
                    temp = Math.max(temp, dice[current][j + 3]);
                }

                sum += temp;
                calculate(n, current + 1, dice[current][(i + 3) % 6]);
                sum -= temp;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            dice[i][0] = Integer.parseInt(st.nextToken());
            dice[i][1] = Integer.parseInt(st.nextToken());
            dice[i][2] = Integer.parseInt(st.nextToken());
            dice[i][4] = Integer.parseInt(st.nextToken());
            dice[i][5] = Integer.parseInt(st.nextToken());
            dice[i][3] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < 6; i++) {
            calculate(n, 0, dice[0][i]);
        }

        System.out.println(answer);
    }
}