package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class p2733 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static class Interpreter {
        int [] mem = new int[1 << 15 + 5];
        char [] code = new char[1 << 17];
        int [] jmp = new int[1 << 17];
        int ptr = 0;
        int pc = 0;
        int ns = -1;
        boolean valid = true;

        private boolean parse() throws IOException {
            int [] jmpS = new int[1 << 17];
            Arrays.fill(jmp, -1);
            parse:
            while (true) {
                char[] input = br.readLine().toCharArray();
                if (input.length == 0) continue;
                if ((input[0] == 'e') && (input[1] == 'n') && (input[2] == 'd')) {
                    break;
                }

                int size = input.length;
                for (int i = 0; i < size && input[i] != '%'; i++) {
                    switch (input[i]) {
                        case '>':
                        case '<':
                        case '+':
                        case '-':
                        case '.':
                            code[pc++] = input[i];
                            break;
                        case '[':
                            jmpS[++ns] = pc;
                            code[pc++] = input[i];
                            break;
                        case ']':
                            if (ns == -1) {
                                valid = false;
                                break parse;
                            }
                            jmp[pc] = jmpS[ns];
                            jmp[jmpS[ns--]] = pc;
                            code[pc++] = input[i];
                            break;
                    }
                }
            }

            return valid && ns == -1;
        }

        private String interpret() {
            StringBuilder sb = new StringBuilder();
            pc = 0;
            while (code[pc] != 0) {
                switch (code[pc++]) {
                    case '>':
                        if (ptr == 32767) ptr = 0;
                        else ptr++;
                        break;
                    case '<':
                        if (ptr == 0) ptr = 32767;
                        else ptr--;
                        break;
                    case '+':
                        if (mem[ptr] == 255) mem[ptr] = 0;
                        else mem[ptr]++;
                        break;
                    case '-':
                        if (mem[ptr] == 0) mem[ptr] = 255;
                        else mem[ptr]--;
                        break;
                    case '.':
                        sb.append((char) mem[ptr]);
                        break;
                    case '[':
                        if (mem[ptr] == 0) {
                            pc = jmp[pc - 1];
                        }
                        break;
                    case ']':
                        if (mem[ptr] != 0) {
                            pc = jmp[pc - 1];
                        }
                }
            }

            return sb.toString();
        }

        public String solve() throws IOException {
            if (!parse()) return "COMPILE ERROR";
            return interpret();
        }
    }

    public static void main(String[] args) throws IOException {
        StringBuilder res = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        for (int i = 1; i <= t; i++) {
            Interpreter ip = new Interpreter();
            res.append("PROGRAM #").append(i).append(':').append('\n');
            res.append(ip.solve()).append('\n');
        }
        System.out.println(res);
    }
}