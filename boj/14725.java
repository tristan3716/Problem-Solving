package src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class p14725 {
    private static class Trie {
        String val;
        TreeMap<String, Trie> child = new TreeMap<>();

        public Trie() {
            this(null);
            build();
        }
        public Trie(String s) {
            this.val = s;
        }

        public void add(String[] arr, int idx) {
            if (child.containsKey(arr[idx])) {
                child.get(arr[idx]).add(arr, idx + 1);
            } else {
                Trie x = new Trie(arr[idx]);
                child.put(arr[idx], x);
                if (idx + 1 < arr.length) {
                    x.add(arr, idx + 1);
                }
            }
        }

        private static String[] tab;
        private void build() {
            tab = new String[15];
            tab[0] = "";
            for (int i = 1; i < 15; i++) {
                tab[i] = tab[i-1] + "--";
            }
        }
        private static StringBuilder sb;
        @Override
        public String toString() {
            sb = new StringBuilder();
            print(0);
            return sb.toString();
        }

        public void print(int i) {
            for (Map.Entry<String, Trie> e : child.entrySet()) {
                sb.append(tab[i]);
                sb.append(e.getKey()).append('\n');
                e.getValue().print(i + 1);
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Trie trie = new Trie();
        for (int i = 0; i < n; i++) {
            String[] arr = br.readLine().split(" ");
            trie.add(arr, 1);
        }
        System.out.println(trie);
    }
}