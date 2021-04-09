package src;

import java.io.*;
import java.util.*;

public class p3425 {
    static List<String> list = new ArrayList<>();
    static Deque<Long> stack = new ArrayDeque<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer temp;

        while(true) {
            int cnt = 0;
            list = new ArrayList<>();
            while(true) {
                try {
                    temp = new StringTokenizer(br.readLine());
                    String input = temp.nextToken();
                    if(input.equals("QUIT")) {
                        System.out.println(sb);
                        return;
                    }
                    if(input.equals("END")) {
                        break;
                    }else if(input.equals("NUM")) {
                        list.add(input);
                        list.add(temp.nextToken());
                        cnt++;
                    }else {
                        list.add(input);
                    }
                    cnt++;
                } catch (NoSuchElementException ignored) {
                }
            }
            int N = Integer.parseInt(br.readLine());
            if(N==0) {
                go(cnt);
                sb.append("\n");
            }else {
                for (int i = 0; i < N; i++) {
                    stack = new ArrayDeque<>();
                    long a = Integer.parseInt(br.readLine());
                    stack.offerLast(a);
                    go(cnt);
                    sb.append("\n");
                }
            }
            sb.append("\n");
        }

    }
    static void go(int cnt) {
        label:
        for (int i = 0; i < cnt; i++) {
            String input = list.get(i);
            switch (input) {
                case "NUM":
                    i++;
                    long x = Integer.parseInt(list.get(i));
                    stack.offerLast(x);
                    break;
                case "POP":
                    if (!stack.isEmpty()) {
                        stack.pollLast();
                    } else {
                        sb.append("ERROR");
                        return;
                    }
                    break;
                case "INV":
                    if (!stack.isEmpty()) {
                        long t = stack.pollLast();
                        stack.offerLast(t * (-1));
                    } else {
                        sb.append("ERROR");
                        return;
                    }
                    break;
                case "DUP":
                    if (!stack.isEmpty()) {
                        long t = stack.pollLast();
                        stack.offerLast(t);
                        stack.offerLast(t);
                    } else {
                        sb.append("ERROR");
                        return;
                    }
                    break;
                case "SWP":
                    if (stack.size() > 1) {
                        long a = stack.pollLast();
                        long b = stack.pollLast();
                        stack.offerLast(a);
                        stack.offerLast(b);
                    } else {
                        sb.append("ERROR");
                        return;
                    }
                    break;
                case "ADD":
                    if (stack.size() > 1) {
                        long a = stack.pollLast();
                        long b = stack.pollLast();
                        if (a + b > 1000000000) {
                            sb.append("ERROR");
                            return;
                        }
                        stack.offerLast(a + b);
                    } else {
                        sb.append("ERROR");
                        return;
                    }
                    break;
                case "SUB":
                    if (stack.size() > 1) {
                        long a = stack.pollLast();
                        long b = stack.pollLast();
                        if (Math.abs(b - a) > 1000000000) {
                            sb.append("ERROR");
                            return;
                        }
                        stack.offerLast(b - a);
                    } else {
                        sb.append("ERROR");
                        return;
                    }
                    break;
                case "MUL":
                    if (stack.size() > 1) {
                        long a = stack.pollLast();
                        long b = stack.pollLast();
                        if (a * b > 1000000000) {
                            sb.append("ERROR");
                            return;
                        }
                        stack.offerLast(b * a);
                    } else {
                        sb.append("ERROR");
                        return;
                    }
                    break;
                case "DIV":
                    if (stack.size() > 1) {
                        long a = stack.pollLast();
                        long b = stack.pollLast();
                        int k = 0;
                        if (a < 0) {
                            a = Math.abs(a);
                            k++;
                        }
                        if (b < 0) {
                            b = Math.abs(b);
                            k++;
                        }
                        if (a == 0) {
                            sb.append("ERROR");
                            return;
                        }
                        long result = b / a;
                        if (k == 1) {
                            stack.offerLast(result * (-1));
                        } else {
                            stack.offerLast(result);
                        }
                    } else {
                        sb.append("ERROR");
                        return;
                    }
                    break;
                case "MOD":
                    if (stack.size() > 1) {
                        long a = stack.pollLast();
                        long b = stack.pollLast();
                        int k = 0;
                        if (b < 0) {
                            b = Math.abs(b);
                            k++;
                        }
                        if (a == 0) {
                            sb.append("ERROR");
                            return;
                        }
                        long result = b % a;
                        if (k == 1) {
                            stack.offerLast(result * (-1));
                        } else {
                            stack.offerLast(result);
                        }
                    } else {
                        sb.append("ERROR");
                        return;
                    }
                    break;
                case "END":
                    break label;
            }

        }
        if(stack.size() != 1) {
            sb.append("ERROR");
        }else {
            sb.append(stack.pollLast());
        }
    }

}