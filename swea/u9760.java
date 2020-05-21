import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            StringTokenizer tks = new StringTokenizer(bf.readLine());
            // S:0, D:1, H:2, C:3
            boolean[] mode = new boolean[4];
            int type = 0;

            int[] num = new int[14];

            for (int i = 0; i < 5; i++) {
                String str = tks.nextToken();
                switch (str.charAt(0)) {
                    case 'S':
                        if (!mode[0])
                            type++;
                        mode[0] = true;
                        break;
                    case 'D':
                        if (!mode[1])
                            type++;
                        mode[1] = true;
                        break;
                    case 'H':
                        if (!mode[2])
                            type++;
                        mode[2] = true;
                        break;
                    case 'C':
                        if (!mode[3])
                            type++;
                        mode[3] = true;
                        break;
                }

                if (str.charAt(1) - '0' < 10) {
                    num[str.charAt(1) - '0']++;
                } else {
                    switch (str.charAt(1)) {
                        case 'A':
                            num[1]++;
                            break;
                        case 'T':
                            num[10]++;
                            break;
                        case 'J':
                            num[11]++;
                            break;
                        case 'Q':
                            num[12]++;
                            break;
                        case 'K':
                            num[13]++;
                            break;
                    }

                }

            }

            if (num[10] == 1 && num[11] == 1 && num[12] == 1 && num[13] == 1 && num[1] == 1) {
                if (type == 1) {
                    //sb.append("Straight Flush").append("\n");
                    //continue;
                    //throw new IOException();
                } else {
                    sb.append("Straight").append("\n");
                    continue;
                }
                throw new IOException();
            }

            int max=0;
            int cnt = 0;
            int pair = 0;
            int thriple = 0;
            int four = 0;
            for (int i = 1; i < 14; i++) {
                if (num[i] > 0) {
                    cnt++;
                    if (num[i] == 2) {
                        pair++;
                    } else if (num[i] == 3) {
                        thriple++;
                    } else if (num[i] == 4) {
                        four++;
                    }
                    max=Math.max(max, cnt);

                } else if (num[i] == 0 && cnt > 0) {
                    cnt = 0;
                }
            }


            if (type == 1) {
                if (max == 5) {
                    sb.append("Straight Flush").append("\n");
                    continue;
                }
            }

            if (four == 1) {
                sb.append("Four of a Kind").append("\n");
                continue;
            }

            if (thriple == 1 && pair == 1) {
                sb.append("Full House").append("\n");
                continue;
            }

            if (type == 1) {
                sb.append("Flush").append("\n");
                continue;

            }

            if (max== 5) {
                sb.append("Straight").append("\n");
                continue;
            }

            if (thriple == 1 && pair == 0) {
                sb.append("Three of a kind").append("\n");
                continue;
            }

            if (pair == 2) {
                sb.append("Two pair").append("\n");
                continue;
            }

            if (pair == 1) {
                sb.append("One pair").append("\n");
                continue;
            }

            sb.append("High card").append("\n");
        }

        System.out.println(sb);

    }

}