package src;

import java.util.Scanner;

public class p6568 {
    static class Computer {
        int pc;
        int x;
        int alu;
        int[] mbr = new int[32];
        int operator;
        int operand;
        boolean isHLT;

        private void increasePC() {
            pc = (pc+1) % 32;
        }

        private int decode(int x) {
            int res = 0b0;

            int bit = 0b0;
            while (x != 0) {
                if (x % 10 == 1) {
                    res += (1 << bit);
                }
                x /= 10;
                bit++;
            }
            return res;
        }

        private int getOperator() {
            return (x >> 5);
        }

        private int getOperand() {
            return (x & 0b11111);
        }

        private void fetch() {
            this.x = mbr[pc];
            this.operator = getOperator();
            this.operand = getOperand();
        }

        private void excute() {
            switch(this.operator) {
                case 0:
                    STA(); break;
                case 1:
                    LDA(); break;
                case 2:
                    BEQ(); break;
                case 3:
                    NOP(); break;
                case 4:
                    DEC(); break;
                case 5:
                    INC(); break;
                case 6:
                    JMP(); break;
                case 7:
                    HLT(); break;
            }
        }

        private void STA() {
            mbr[operand] = alu;
        }
        public void STA(int idx, int val) {
            mbr[idx] = decode(val);
        }

        private void LDA() {
            alu = mbr[operand];
        }

        private void BEQ() {
            if (alu == 0b0) {
                pc = operand;
            }
        }

        private void NOP() {}

        private void DEC() {
            alu--;
            if (alu < 0b0) {
                alu = 0b11111111;
            }
        }

        private void INC() {
            alu++;
            if (alu > 0b11111111) {
                alu = 0b0;
            }
        }

        private void JMP() {
            pc = operand;
        }

        private void HLT() {
            this.isHLT = true;
        }

        public boolean isEnd() {
            return isHLT;
        }

        public void next() {
            if (!isEnd()) {
                this.fetch();
                this.increasePC();
                this.excute();
            }
        }

        public String result() {
            StringBuilder sb = new StringBuilder();

            int temp = alu;
            while (temp > 0) {
                if ((temp & 1) == 1) {
                    sb.insert(0, "1");
                }
                else {
                    sb.insert(0, "0");
                }
                temp /= 2;
            }
            while (sb.length() < 8) {
                sb.insert(0, "0");
            }

            return sb.toString();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            Computer c = new Computer();

            for (int i = 0; i < 32; ++i) {
                int command = sc.nextInt();

                c.STA(i, command);
            }
            while(!c.isEnd()) {
                c.next();
            }
            System.out.println(c.result());
        }

        sc.close();
    }
}