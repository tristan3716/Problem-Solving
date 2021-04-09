import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int n = 4;
    static void copyBoard(int[][] source, int[][] destination) {
        for (int i = 0; i < n; i++) {
            System.arraycopy(source[i], 0, destination[i], 0, n);
        }
    }

    static void moveLeft(int[][] map, int n) {
        for (int i = 0; i < n; i++) {
            int idx = 0;
            int previous = 0;
            for (int j = 0; j < n; j += 1) {
                int current = map[i][j];
                if (current != 0) {
                    if (previous == 0) {
                        previous = current;
                    } else {
                        if (previous == current) {
                            map[i][idx] = previous * 2;
                            previous = 0;
                        } else {
                            map[i][idx] = previous;
                            previous = map[i][j];
                        }
                        idx += 1;
                    }
                }
                map[i][j] = 0;
            }

            if (previous != 0) {
                map[i][idx] = previous;
            }
        }
    }
    static void moveRight(int[][] map, int n) {
        for (int i = 0; i < n; i++) {
            int idx = n - 1;
            int previous = 0;
            for (int j = n - 1; j >= 0; j -= 1) {
                int current = map[i][j];
                if (current != 0) {
                    if (previous == 0) {
                        previous = current;
                    } else {
                        if (previous == current) {
                            map[i][idx] = previous * 2;
                            previous = 0;
                        } else {
                            map[i][idx] = previous;
                            previous = map[i][j];
                        }
                        idx -= 1;
                    }
                }
                map[i][j] = 0;
            }

            if (previous != 0) {
                map[i][idx] = previous;
            }
        }
    }

    static void moveUp(int[][] map, int n) {
        for (int i = 0; i < n; i++) {
            int idx = 0;
            int previous = 0;
            for (int j = 0; j < n; j += 1) {
                int current = map[j][i];
                if (current != 0) {
                    if (previous == 0) {
                        previous = current;
                    } else {
                        if (previous == current) {
                            map[idx][i] = previous * 2;
                            previous = 0;
                        } else {
                            map[idx][i] = previous;
                            previous = map[j][i];
                        }
                        idx += 1;
                    }
                }
                map[j][i] = 0;
            }

            if (previous != 0) {
                map[idx][i] = previous;
            }
        }
    }
    static void moveDown(int[][] map, int n) {
        for (int i = 0; i < n; i++) {
            int idx = n - 1;
            int previous = 0;
            for (int j = n - 1; j >= 0; j -= 1) {
                int current = map[j][i];
                if (current != 0) {
                    if (previous == 0) {
                        previous = current;
                    } else {
                        if (previous == current) {
                            map[idx][i] = previous * 2;
                            previous = 0;
                        } else {
                            map[idx][i] = previous;
                            previous = map[j][i];
                        }
                        idx -= 1;
                    }
                }
                map[j][i] = 0;
            }

            if (previous != 0) {
                map[idx][i] = previous;
            }
        }
    }

    private static int findMax(int[][] map) {
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(map[i][j], max);
            }
        }
        return max;
    }

    private static int maxValue = 0;
    private static void solve(int depth, final int max, final int[][] map) {
        if (depth == max) {
            maxValue = Math.max(maxValue, findMax(map));
        } else {
            int[][] memory = new int[n][n];
            copyBoard(map, memory);

            moveLeft(map, n);
            solve(depth + 1, max, map);
            copyBoard(memory, map);

            moveRight(map, n);
            solve(depth + 1, max, map);
            copyBoard(memory, map);

            moveUp(map, n);
            solve(depth + 1, max, map);
            copyBoard(memory, map);

            moveDown(map, n);
            solve(depth + 1, max, map);
            copyBoard(memory, map);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve(0, 5, map);

        System.out.println(maxValue);
    }
}
