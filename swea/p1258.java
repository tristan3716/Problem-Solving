import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    private static class IO {
        static BufferedReader br;
        static StringTokenizer st;

        IO() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (st == null || !st.hasMoreTokens()) {
                st = new StringTokenizer(br.readLine());
            }

            return st.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    private static class Result {
        int r;
        int c;
        int count;

        public Result(int r, int c) {
            super();
            this.r = r;
            this.c = c;
            this.count = r * c;
        }

        @Override
        public String toString() {
            return r + " " + c;
        }
    }

    public static void main(String[] args) throws IOException {
        IO io = new IO();

        int t = io.nextInt();
        int[][] arr = new int[102][102];

        for (int tc = 1; tc <= t; tc++) {
            int n = io.nextInt();
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; j++) {
                    arr[i][j] = io.nextInt();
                }
            }

            List<Result> res = new ArrayList<>();
            StringBuilder sb = new StringBuilder("#");
            sb.append(tc).append(' ');
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; j++) {
                    if (arr[i][j] != 0) {
                        int r = n;
                        int c = n;
                        boolean f = true;
                        for (int ii = i; ii <= i+r; ++ii) {
                            if (arr[ii][j] == 0) {
                                r = ii - i;
                                break;
                            }
                            int jj = j;
                            for (; jj <= j+c; jj++) {
                                if (f && arr[i][jj] == 0) {
                                    c = jj-j;
                                    f = false;
                                }
                                arr[ii][jj] = 0;
                            }
                        }
                        arr[i][j] = 0;
                        res.add(new Result(r, c));
                    }
                }
            }

            res.sort(new Comparator<Result>() {
                @Override
                public int compare(Result o1, Result o2) {
                    if (o1.count == o2.count) {
                        return o1.r - o2.r;
                    } else {
                        return o1.count - o2.count;
                    }
                }
            });

            sb.append(res.size()).append(' ');
            res.forEach(x -> sb.append(x.toString()).append(' '));

            System.out.println(sb);
        }
    }
}