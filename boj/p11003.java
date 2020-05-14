package src;

import java.io.*;
import java.util.StringTokenizer;

public class p11003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        final int n = Integer.parseInt(st.nextToken());
        final int l = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");

        int[] result = new int[n];
        int[] value = new int[n];
        int[] idq = new int[n];
        int dqs = 0;
        int dqe = 0;

        for (int i = 0; i < n; ++i) {
            if (dqs != dqe && idq[dqs] <= i - l) {
                dqs++;
            }
            value[i] = Integer.parseInt(st.nextToken());
            while (dqs != dqe && value[idq[dqe - 1]] > value[i]) {
                dqe--;
            }
            idq[dqe++] = i;
            result[i] = value[idq[dqs]];
        }

        for (int x : result) {
            bw.write(x + " ");
        }
        bw.flush();
    }
}