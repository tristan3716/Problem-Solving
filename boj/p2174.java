package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class p2174 {
    private static class Solver {
        private static class Robot {
            int r, c;
            int d;

            public Robot(int r, int c, int d) {
                this.r = r;
                this.c = c;
                this.d = d;
            }
        }
        private static class Command {
            int type;
            int id;
            int time;

            public Command(int type, int id, int time) {
                this.type = type;
                this.id = id;
                this.time = time;
            }
        }

        int n, m;
        int a, b;
        Map<Integer, Robot> robotList;
        Command[] commands;
        int [][] map;
        private static final int [][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        public Solver () throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());

            map = new int[n][m];
            robotList = new HashMap<>();

            st = new StringTokenizer(br.readLine(), " ");
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            commands = new Command[b];

            for (int i = 1; i <= a; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int c = Integer.parseInt(st.nextToken()) - 1;
                int r = Integer.parseInt(st.nextToken()) - 1;
                int d;
                char dc = st.nextToken().charAt(0);
                switch (dc) {
                    case 'N':
                        d = 0; break;
                    case 'E':
                        d = 1; break;
                    case 'S':
                        d = 2; break;
                    default: // W
                        d = 3; break;
                }
                map[r][c] = i;
                robotList.put(i, new Robot(r, c, d));
            }

            for (int i = 0; i < b; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int id = Integer.parseInt(st.nextToken());
                char tc = st.nextToken().charAt(0);
                int type;
                int c = Integer.parseInt(st.nextToken());
                switch (tc) {
                    case 'F':
                        type = 0;
                        break;
                    case 'L':
                        c = -c;
                    default: // R
                        type = 1;
                        c %= 4;
                        break;
                }
                commands[i] = new Command(type, id, c);
            }
        }

        public void simulate() {
            for (int i = 0; i < b; i++) {
                Command c = commands[i];
                Robot x = robotList.get(c.id);

                switch (c.type) {
                    case 0:
                        map[x.r][x.c] = 0;
                        int nr = x.r;
                        int nc = x.c;
                        for (int j = 0; j < c.time; j++) {
                            nr = nr + directions[x.d][0];
                            nc = nc + directions[x.d][1];
                            if (nr < 0 || nc < 0 || nr >= n || nc >= m) {
                                System.out.printf("Robot %d crashes into the wall", c.id);
                                System.exit(0);
                            }
                            if (map[nr][nc] != 0) {
                                System.out.printf("Robot %d crashes into robot %d", c.id, map[nr][nc]);
                                System.exit(0);
                            }
                        }
                        map[nr][nc] = c.id;
                        x.r = nr;
                        x.c = nc;
                        break;
                    case 1:
                        x.d = (x.d + c.time + 4) % 4;
                        break;
                }
            }
            System.out.println("OK");
        }
    }

    public static void main(String[] args) throws IOException {
        Solver s = new Solver();
        s.simulate();
    }
}