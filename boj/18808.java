package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p18808 {
    static int n, m, k;
    static int[][] map;
    static int[][][][] stickerShape = new int[100][4][10][10];
    static int[][][] stickerSize = new int[100][4][2];
    static int[] stickerCount = new int[100];

    private static boolean isIn(int r, int c) {
        return (r >= 0 && c >= 0 && r < n && c < m);
    }

    private static boolean isPlaceAble(int i, int j, int[][] shape, int[] size) {
        int r = size[0];
        int c = size[1];

        for (int l = 0; l < r; l++) {
            for (int o = 0; o < c; o++) {
                if (!isIn(i + l, j + o)) {
                    return false;
                }
                if (shape[l][o] == 1 && map[i + l][j + o] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void place(int i, int j, int[][] shape, int[] size) {
        int r = size[0];
        int c = size[1];
        for (int l = 0; l < r; l++) {
            for (int o = 0; o < c; o++) {
                if (shape[l][o] == 1) {
                    map[i + l][j + o] = 1;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        stickerCount = new int[k];

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = stickerSize[i][0][0] = stickerSize[i][2][0] = Integer.parseInt(st.nextToken());
            int c = stickerSize[i][0][1] = stickerSize[i][2][1] = Integer.parseInt(st.nextToken());
            stickerSize[i][1][0] = stickerSize[i][3][0] = c;
            stickerSize[i][1][1] = stickerSize[i][3][1] = r;

            stickerShape[i][0] = new int[r][c];
            stickerShape[i][1] = new int[c][r];
            stickerShape[i][2] = new int[r][c];
            stickerShape[i][3] = new int[c][r];
            for (int j = 0; j < r; j++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int l = 0; l < c; l++) {
                    int t = Integer.parseInt(st.nextToken());
                    stickerShape[i][0][j][l] = t;
                    stickerShape[i][2][r - j - 1][c - l - 1] = t;
                    if (t == 1) {
                        stickerCount[i]++;
                    }
                }
            }
            for (int j = 0; j < c; j++) {
                for (int l = 0; l < r; l++) {
                    stickerShape[i][1][c - j - 1][l] = stickerShape[i][2][l][j];
                    stickerShape[i][3][j][r - l - 1] = stickerShape[i][2][l][j];
                }
            }
        }

        int sum = 0;
        for (int o = 0; o < k; o++) {
            next:
            for (int l = 0; l < 4; l++) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        if (isPlaceAble(i, j, stickerShape[o][l], stickerSize[o][l])) {
                            place(i, j, stickerShape[o][l], stickerSize[o][l]);
                            sum += stickerCount[o];


                            break next;
                        }
                    }
                }
            }
        }

        System.out.println(sum);
    }
}
