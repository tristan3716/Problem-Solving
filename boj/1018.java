import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int r;
    static int c;
    static char[][] board;
    static char[][] temp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        board = new char[r][c];
        temp = new char[8][8];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < r; i++) {
            String s = br.readLine();
            for (int j = 0; j < c; j++) {
                board[i][j] = s.charAt(j);
            }
        }

        for (int i = 0; i <= r - 8; i++) {
            for (int j = 0; j <= c - 8; j++) {
                int cnt = findBoardArea(i, j); // 자기말고 옆에 애들 색깔 바꾸기
                int cnt2 = findBoardArea2(i, j); // 자기부 색깔바꾸기
                min = Math.min(min, cnt);
                min = Math.min(min, cnt2);
            }
        }

        System.out.print(min);
    }

    static private int findBoardArea(int row, int col) {
        int cnt = 0;
        for (int i = 0; i < temp.length; i++) {
            System.arraycopy(board[row++], col, temp[i], 0, 8);
        }
        for (int i = 0; i <7; i++) {
            for (int j = 0; j <7; j++) {
                if(temp[i][j]==temp[i+1][j]) {
                    if (temp[i+1][j] == 'W')
                        temp[i+1][j] = 'B';
                    else
                        temp[i+1][j] = 'W';
                    cnt++;
                }
                if(temp[i][j]==temp[i][j+1]) {
                    if (temp[i][j+1] == 'W')
                        temp[i][j+1] = 'B';
                    else
                        temp[i][j+1] = 'W';
                    cnt++;
                }
            }
        }
        if(temp[7][7]==temp[7][6] || temp[7][7]==temp[6][7])cnt++;

        return cnt;
    }

    static private int findBoardArea2(int row, int col) {
        int cnt = 1;
        for (int i = 0; i < temp.length; i++) {
            System.arraycopy(board[row++], col, temp[i], 0, 8);
        }

        if (temp[0][0] == 'W')
            temp[0][0] = 'B';
        else
            temp[0][0] = 'W';

        for (int i = 0; i <7; i++) {
            for (int j = 0; j <7; j++) {
                if(temp[i][j]==temp[i+1][j]) {
                    if (temp[i+1][j] == 'W') {
                        temp[i + 1][j] = 'B';
                    }
                    else
                        temp[i+1][j] = 'W';
                    cnt++;
                }
                if(temp[i][j]==temp[i][j+1]) {
                    if (temp[i][j+1] == 'W') {
                        temp[i][j + 1] = 'B';
                    }
                    else
                        temp[i][j+1] = 'W';
                    cnt++;
                }
            }
        }
        if(temp[7][7]==temp[7][6] || temp[7][7]==temp[6][7])cnt++;
        return cnt;
    }
}