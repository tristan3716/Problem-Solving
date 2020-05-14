package src;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/* @ Trie from https://github.com/Harlan1994/Trie */

public class p4358 {
    private static class Trie {
        private int size;
        private boolean caseSensitive;

        private static class TrieNode {
            char c;
            int occurances;
            Map<Character, TrieNode> children;

            TrieNode(char c) {
                this.c = c;
                occurances = 0;
                children = null;
            }

            int insert(String s, int pos) {
                if (s == null || pos >= s.length())
                    return 0;

                if (children == null)
                    children = new HashMap<Character, TrieNode>();

                char c = s.charAt(pos);
                TrieNode n = children.get(c);

                if (n == null) {
                    n = new TrieNode(c);
                    children.put(c, n);
                }

                if (pos == s.length() - 1) {
                    n.occurances++;
                    return n.occurances;
                } else {
                    return n.insert(s, pos + 1);
                }
            }

            boolean remove(String s, int pos) {
                if (children == null || s == null)
                    return false;

                char c = s.charAt(pos);
                TrieNode n = children.get(c);
                if (n == null)
                    return false;

                boolean ret;
                if (pos == s.length() - 1) {
                    int before = n.occurances;
                    n.occurances = 0;
                    ret = before > 0;
                } else {
                    ret = n.remove(s, pos + 1);
                }

                if (n.children == null && n.occurances == 0) {
                    children.remove(n.c);
                    if (children.size() == 0)
                        children = null;
                }

                return ret;
            }

            TrieNode lookup(String s, int pos) {
                if (s == null)
                    return null;

                if (pos >= s.length() || children == null)
                    return null;

                else if (pos == s.length() - 1)
                    return children.get(s.charAt(pos));

                else {
                    TrieNode n = children.get(s.charAt(pos));
                    return n == null ? null : n.lookup(s, pos + 1);
                }
            }
        }

        final TrieNode root;
        public Trie(boolean caseSensitive) {
            root = new TrieNode((char) 0);
            size = 0;
            this.caseSensitive = caseSensitive;
        }

        public boolean contains(String word) {
            if (word == null)
                return false;

            return root.lookup(caseSensitive ? word : word.toLowerCase(), 0) != null;
        }

        public int frequency(String word) {
            if (word == null)
                return 0;

            TrieNode n = root.lookup(caseSensitive ? word : word.toLowerCase(), 0);
            return n == null ? 0 : n.occurances;
        }

        public int insert(String word) {
            if (word == null)
                return 0;

            int times = root.insert(caseSensitive ? word : word.toLowerCase(), 0);
            size++;
            return times;
        }

        public boolean remove(String word) {
            if (word == null)
                return false;

            if (root.remove(caseSensitive ? word : word.toLowerCase(), 0)) {
                size--;
                return true;
            }
            return false;
        }

        public int size() {
            return size;
        }

    }

    public static void main(String[] args) {
        Trie t = new Trie(true);
        Set<String> set = new TreeSet<>();
        Scanner sc = new Scanner(System.in);
        Set<String> raw_set = new TreeSet<>();

        while (sc.hasNextLine()) {
            String str = sc.nextLine();

            raw_set.add(str);
            set.add(str);
            t.insert(str);
        }
        for (String x : set) {
            System.out.printf("%s %.4f\n", x, ((t.frequency(x)*100)/(double)t.size()));
        }
    }
}