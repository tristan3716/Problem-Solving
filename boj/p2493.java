/**
 * @title 탑 G5
 * @see https://www.acmicpc.net/problem/2493
 * @since 2020.02.03
 * @category stack
 * @complexity O(nlogn) -> 1712ms
 * @description
 *      getMin() 연산을 빠르게 할 수 있는 스택을 구현하면 됨.
 *      전에 봤었던 궁극의 Augmented Stack 구현하면 될 듯.
 *      내부에 두개의 stack을 두고, 최솟값과 원래값을 동시에 푸시 및 팝
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Main {
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