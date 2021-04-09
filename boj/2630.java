import java.util.Scanner;

public class Main {
    static int n;
    
    static int blue;
    static int white;
    static int[][] map = new int[133][133];
    static void rec(int r, int c, int size){
        if (size == 1) {
            if (map[r][c] == 1) {blue++;}
            else {white++;}
            return;
        }
        boolean isBlue = true;
        boolean isWhite = true;
        check:
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; j++) {
                if (map[r+i][c+j] == 0) {
                    isBlue = false;
                } else if (map[r+i][c+j] == 1) {
                    isWhite = false;
                }
                if (isBlue && isWhite) {
                    break check;
                }
            }
        }
        if (!isBlue && !isWhite) {
            rec(r, c, size/2);
            rec(r, c+size/2, size/2);
            rec(r+size/2, c, size/2);
            rec(r+size/2, c+size/2, size/2);
        } else if (isBlue) {
            blue++;
        } else {
            white++;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        rec(0, 0, n);

        System.out.println(white);
        System.out.println(blue);
    }
}