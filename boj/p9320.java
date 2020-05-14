package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class p9320 {
    private static class Node {
        double value;
        int used;

        public Node(double value, int used) {
            this.value = value;
            this.used = used;
        }
    }

    private static void calculate(List<Node> a, List<Node> b, List<Node> c) {
        for (Node A : a) {
            for (Node B : b) {
                if ((A.used & B.used) != 0)
                    continue;
                int used = A.used | B.used;
                c.add(new Node(A.value * B.value, used));
                c.add(new Node(A.value - B.value, used));
                c.add(new Node(B.value - A.value, used));
                c.add(new Node(A.value + B.value, used));
                if (Math.abs(B.value) > 10e-12)
                    c.add(new Node(A.value / B.value, used));
                if (Math.abs(A.value) > 10e-12)
                    c.add(new Node(B.value / A.value, used));
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; ++i) {
            List<Node> list = new ArrayList<>();

            int [] arr = Stream.of(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int id = 1;
            for (int x : arr) {
                list.add(new Node(x, (1 << id++)));
            }

            List<Node> list2 = new ArrayList<>();
            List<Node> list3 = new ArrayList<>();
            List<Node> list4 = new ArrayList<>();
            calculate(list, list, list2);

            calculate(list2, list, list3);

            calculate(list3, list, list4);
            calculate(list2, list2, list4);

            boolean found = false;

            for (Node x : list4){
                if (Math.abs(x.value - 24) < 10e-12) {
                    found = true;
                    break;
                }
            }
            if (found) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}