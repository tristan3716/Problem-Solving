import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Dice {
    public int r, c;

    private int[] horizontal = new int[6];
    private int[] vertical = new int[6];
    private int[] temp = new int[6];
    private int[] PositionAfterNegative = new int[] {4, 1, 5, 3, 2, 0};
    private int[] PositionAfterPositive = new int[] {5, 1, 4, 3, 0, 2};

    public Dice(int sr, int sc) {
        this.r = sr;
        this.c = sc;
    }

    public int top() {
        return horizontal[0];
    }
    public int bottom() {
        return horizontal[2];
    }
    public void setBottom(int x) {
        horizontal[2] = x;
        vertical[2] = x;
    }

    private void shift(int[] arr, int direction) {
        if (direction > 0) {
            int first = arr[0];
            System.arraycopy(arr, 1, arr, 0, 3);
            arr[3] = first;
        } else {
            int last = arr[3];
            System.arraycopy(arr, 0, arr, 1, 3);
            arr[0] = last;
        }
    }
    private void moveWest() {
        shift(this.horizontal, -1);
        System.arraycopy(this.vertical, 0, temp, 0, 6);
        for (int i = 0; i < 6; i++) {
            this.vertical[PositionAfterNegative[i]] = temp[i];
        }
    }
    private void moveEast() {
        shift(this.horizontal, 1);
        System.arraycopy(this.vertical, 0, temp, 0, 6);
        for (int i = 0; i < 6; i++) {
            this.vertical[PositionAfterPositive[i]] = temp[i];
        }
    }
    private void moveNorth() {
        shift(this.vertical, -1);
        System.arraycopy(this.horizontal, 0, temp, 0, 6);
        for (int i = 0; i < 6; i++) {
            this.horizontal[PositionAfterNegative[i]] = temp[i];
        }
    }
    private void moveSouth() {
        shift(this.vertical, 1);
        System.arraycopy(this.horizontal, 0, temp, 0, 6);
        for (int i = 0; i < 6; i++) {
            this.horizontal[PositionAfterPositive[i]] = temp[i];
        }
    }

    public boolean move(int n, int m, int direction) {
        int nr = r;
        int nc = c;
        switch (direction) {
            case 1: // east
                nc = c + 1;
                break;
            case 2: // west
                nc = c - 1;
                break;
            case 3: // north
                nr = r - 1;
                break;
            case 4: // south
                nr = r + 1;
                break;
        }

        if (nr >= 0 && nr < n && nc >= 0 && nc < m) {
            r = nr;
            c = nc;
            switch (direction) {
                case 1: // east
                    moveEast();
                    break;
                case 2: // west
                    moveWest();
                    break;
                case 3: // north
                    moveNorth();
                    break;
                case 4: // south
                    moveSouth();
                    break;
            }

            return true;
        } else {
            return false;
        }
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int sr = Integer.parseInt(st.nextToken());
        int sc = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Dice dice = new Dice(sr, sc);
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < k; i++) {
            int command = Integer.parseInt(st.nextToken());

            boolean moved = dice.move(n, m, command);
            if (moved) {
                if (map[dice.r][dice.c] == 0) {
                    map[dice.r][dice.c] = dice.bottom();
                } else {
                    dice.setBottom(map[dice.r][dice.c]);
                    map[dice.r][dice.c] = 0;
                }

                sb.append(dice.top()).append('\n');
            }
        }

        System.out.println(sb);
    }
}
