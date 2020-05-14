package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class p8972 {
    private static class Pos {
        static int[][] directions = { {9},
                { 1,-1}, { 1, 0}, { 1, 1},
                { 0,-1}, { 0, 0}, { 0, 1},
                {-1,-1}, {-1, 0}, {-1, 1}};

        int r, c;
        int direction;

        public Pos(int r, int c) {
            super();
            this.r = r;
            this.c = c;
        }

        public void move() {
            r = r + directions[direction][0];
            c = c + directions[direction][1];
        }
        public void setDirection(int direction) {
            this.direction = direction;
        }
    }

    private static class Robot extends Pos {
        public Robot(int r, int c) {
            super(r, c);
        }
    }

    private static class Arduino extends Pos {
        boolean isExplosed;
        public Arduino(int r, int c) {
            super(r, c);
            this.isExplosed = false;
        }
        public int key() {
            return r * 123 + c;
        }
        public void calc(Robot robot) {
            int vdiff = robot.r - this.r;
            int hdiff = robot.c - this.c;
            if (vdiff == 0) {
                this.direction = hdiff < 0 ? 4 : 6;
            } else if (hdiff == 0) {
                this.direction = vdiff < 0 ? 8 : 2;
            } else if (vdiff < 0 && hdiff < 0) {
                this.direction = 7;
            } else if (vdiff < 0 && hdiff > 0) {
                this.direction = 9;
            } else if (hdiff < 0) {
                this.direction = 1;
            } else {
                this.direction = 3;
            }
        }
    }


    static List<Arduino> list = new ArrayList<Arduino>();
    public void calculateDirection(Robot robot) {
        for (Arduino xArduino : list) {
            xArduino.calc(robot);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        char [][] arr = new char[n][m];
        Map<Integer, Arduino> s = new HashMap<>();
        Robot robot = null;
        for (int i = 0; i < n; ++i) {
            char[] string = scanner.next().toCharArray();
            for (int j = 0; j < m; j++) {
                char ch = string[j];
                if (ch == 'R') {
                    list.add(new Arduino(i, j));
                } else if (ch == 'I') {
                    ch = '.';
                    robot = new Robot(i, j);
                }
                arr[i][j] = ch;
            }
        }
        assert robot != null;
        char[] commands = scanner.next().toCharArray();

        for (int i = 0; i < commands.length; ++i) {
            robot.setDirection(commands[i]-'0');
            robot.move();
            if (arr[robot.r][robot.c] != '.') {
                System.out.println("kraj " + (i+1));
                System.exit(0);
            }
            for (Arduino x : list) {
                x.calc(robot);
            }
            for (Arduino x : list) {
                arr[x.r][x.c] = '.';
                x.move();
                if (robot.r == x.r && robot.c == x.c) {
                    System.out.println("kraj " + (i+1));
                    System.exit(0);
                }
            }
            for (Arduino x : list) {
                if (s.containsKey(x.key())) {
                    x.isExplosed = true;
                    s.get(x.key()).isExplosed = true;
                }
                s.put(x.key(), x);
            }

            for (int it = 0; it < list.size(); ++it) {
                Arduino x = list.get(it);
                if (x.isExplosed) {
                    list.remove(it--);
                    arr[x.r][x.c] = '.';
                } else {
                    arr[x.r][x.c]= 'R';
                }
            }
            if (arr[robot.r][robot.c] != '.') {
                System.out.println("kraj " + (i+1));
                System.exit(0);
            }
            s.clear();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == robot.r && j == robot.c) {
                    arr[i][j] = 'I';
                }
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }
}