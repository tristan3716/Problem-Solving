package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class p3826 {
    private static class Term {
        int cr, cc, cs;
        int count;

        public Term(int cr, int cc, int cs, int count) {
            this.cr = cr;
            this.cc = cc;
            this.cs = cs;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        char[][] a = new char[15][];
        char[][] b = new char[15][];

        int T = Integer.MAX_VALUE;
        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            if (n == 0 && m == 0) {
                break;
            }

            for (int i = 0; i < n; i++) {
                a[i] = br.readLine().toCharArray();
            }
            for (int i = 0; i < m; i++) {
                b[i] = br.readLine().toCharArray();
            }

            int cr = 0, cc = 0, cs = 0;
            ArrayList<Term> terms = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                int cnt = 0;
                for (char x : a[i]) {
                    if (x != '.') break;
                    cnt++;
                }
                terms.add(new Term(cr, cc, cs, cnt));
                for (char x : a[i]) {
                    switch (x) {
                        case '(':
                            cr++; break;
                        case ')':
                            cr--; break;
                        case '{':
                            cc++; break;
                        case '}':
                            cc--; break;
                        case '[':
                            cs++; break;
                        case ']':
                            cs--; break;
                    }
                }
            }

            ArrayList<Term> c = new ArrayList<>();
            for (int i = 1; i <= 20; i++) {
                for (int j = 1; j <= 20; j++) {
                    ch:
                    for (int k = 1; k <= 20; k++) {
                        for (Term t : terms) {
                            cr = t.cr;
                            cc = t.cc;
                            cs = t.cs;
                            int cnt = t.count;
                            if (cr * i + cc * j + cs * k != cnt) {
                                continue ch;
                            }
                        }
                        c.add(new Term(i, j, k, 0));
                    }
                }
            }

            cr = cc = cs = 0;
            sb.append(0);

            for (int i = 0; i < m-1; i++) {
                for (char x : b[i]) {
                    switch (x) {
                        case '(':
                            cr++; break;
                        case ')':
                            cr--; break;
                        case '{':
                            cc++; break;
                        case '}':
                            cc--; break;
                        case '[':
                            cs++; break;
                        case ']':
                            cs--; break;
                    }
                }
                if (c.isEmpty()) {
                    sb.append(' ').append(-1);
                } else {
                    Term t = c.get(0);
                    int e = t.cr * cr + t.cc * cc + t.cs * cs;
                    for (Term x : c) {
                        int r = x.cr * cr + x.cc * cc + x.cs * cs;
                        if (r != e) {
                            e = -1;
                            break;
                        }
                    }
                    sb.append(' ').append(e);
                }
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
}