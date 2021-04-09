package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class p2493 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws NumberFormatException, IOException {
        Stack<Integer> s = new Stack<>();
        int n = Integer.parseInt(bf.readLine());
        String st = bf.readLine();
        int[] arr = Arrays.asList(st.split(" ")).stream().mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < n; ++i) {
            while (!s.empty() && arr[s.peek()] < arr[i]) {
                s.pop();
            }
            if (s.empty()) {
                System.out.print("0 ");
            }
            else {
                System.out.print((s.peek()+1) + " ");
            }
            s.push(i);
        }
    }
}