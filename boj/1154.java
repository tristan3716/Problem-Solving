/**
 * @title 팀 편성
 * @problem https://www.acmicpc.net/problem/1154
 * @submission https://www.acmicpc.net/source/28205075
 * @date 2021년 4월 9일
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static boolean found = false;
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        boolean[][] matrix = new boolean[n + 1][n + 1];
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (a == -1) {
                break;
            }

            matrix[a][b] = matrix[b][a] = true;
        }

        int[] teamIndex = new int[2];
        int[][] teamSet = new int[n][2];
        matching(matrix, teamIndex, teamSet, 1);

        if (!found) {
            sb.append(-1);
        }

        System.out.println(sb);
    }

    private static void matching(
            final boolean[][] matrix,
            int[] teamIndex,
            final int[][] teamSet,
            final int current) {
        if (found) {
            return;
        }
        if (current == matrix.length) {
            sb.append(1).append('\n');
            for (int i = 0; i < teamIndex[0]; i++) {
                sb.append(teamSet[i][0]).append(' ');
            }
            sb.append(-1).append('\n');
            for (int i = 0; i < teamIndex[1]; i++) {
                sb.append(teamSet[i][1]).append(' ');
            }
            sb.append(-1);

            found = true;
            return;
        }

        boolean connectAll = true;
        for (int i = 0; i < teamIndex[0]; i++) {
            if (!matrix[teamSet[i][0]][current]) {
                connectAll = false;
                break;
            }
        }

        if (connectAll) {
            teamSet[teamIndex[0]++][0] = current;
            matching(matrix, teamIndex, teamSet,
                    current + 1);
            teamIndex[0]--;
        }

        connectAll = true;
        for (int i = 0; i < teamIndex[1]; i++) {
            if (!matrix[teamSet[i][1]][current]) {
                connectAll = false;
                break;
            }
        }

        if (connectAll) {
            teamSet[teamIndex[1]++][1] = current;
            matching(matrix, teamIndex, teamSet,
                    current + 1);
            teamIndex[1]--;
        }
    }
}