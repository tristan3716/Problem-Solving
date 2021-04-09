import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class Main {
    private static class StringWrapper {
        StringBuilder sb;

        public StringWrapper(String string) {
            this.sb = new StringBuilder(string);
        }

        public boolean isEmpty() {
            return this.sb.length() == 0;
        }
        public void removeReverse() {
            this.removeLast();
            this.sb.reverse();
        }
        public char getLast() {
            return this.sb.charAt(this.sb.length() - 1);
        }
        public void removeLast() {
            this.sb.setLength(this.sb.length() - 1);
        }

        public boolean equals(String str) {
            return this.sb.toString().equals(str);
        }
    }

    public static int solve(String s, String t) {
        StringWrapper sw = new StringWrapper(t);
        while(!sw.isEmpty()) {
            if (sw.getLast() == 'A') {
                sw.removeLast();
            } else {
                sw.removeReverse();
            }

            if (sw.equals(s)) {
                return 1;
            }
        }

        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        String t = br.readLine();

        System.out.println(solve(s, t));
    }
}
