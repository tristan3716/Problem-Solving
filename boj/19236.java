import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static Shark shark = new Shark();
    private static class Fish {
        int r;
        int c;
        int dir;
        boolean alive;

        public Fish(int r, int c, int dir, boolean alive) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.alive = alive;
        }
        public Fish(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.alive = true;
        }

        public void rotate() {
            this.dir = (this.dir + 1) % 8;
        }

        public boolean isMovable() {
            int nr = this.r + DIRECTIONS[dir][0];
            int nc = this.c + DIRECTIONS[dir][1];

            return isIn(nr, nc) && shark.r != nr && shark.c != nc;
        }

        public void swap(Fish other) {
            int tr = other.r;
            int tc = other.c;
            other.r = this.r;
            other.c = this.c;
            this.r = tr;
            this.c = tc;
        }
    }
    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < 4 && c < 4;
    }
    private static final int[][] DIRECTIONS = {
            {0, 0},
            {-1,  0}, {-1, -1}, { 0, -1}, {1, -1},
            { 1,  0}, { 1,  1}, { 0,  1}, {-1, 1}
    };
    private static class Shark {
        int r;
        int c;
        int dir;
    }

    public static PriorityQueue<Fish> collectFish() {
        PriorityQueue<Fish> fishes = new PriorityQueue<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (map[i][j] != 0) {
                    Fish fish = new Fish(i, j, dir[i][j]);
                    fishes.offer(fish);
                }
            }
        }

        return fishes;
    }

    public static void copy(int[][] copied, Fish[] copiedFishes) {
        for (int i = 0; i < 4; i++) {
            System.arraycopy(map[i], 0, copied[i], 0, 4);
        }

        for (int i = 1; i < 17; i++) {
            copiedFishes[i] = new Fish(
                    fishes[i].r,
                    fishes[i].c,
                    fishes[i].dir,
                    fishes[i].alive);
        }
    }
    public static void restore(int[][] copied, Fish[] copiedFishes) {
        for (int i = 0; i < 4; i++) {
            System.arraycopy(copied[i], 0, map[i], 0, 4);
        }

        for (int i = 1; i < 17; i++) {
            fishes[i].r = copiedFishes[i].r;
            fishes[i].c = copiedFishes[i].c;
            fishes[i].dir = copiedFishes[i].dir;
            fishes[i].alive = copiedFishes[i].alive;
        }
    }

    public static void moveFish() {
        for (int i = 1; i < 17; i++) {
            Fish fish = fishes[i];

            if (!fish.alive) continue;

            int nd = fish.dir;
            int startDir = fish.dir;

            int nr = fish.r + DIRECTIONS[nd][0];
            int nc = fish.c + DIRECTIONS[nd][1];

            while (true) {
                if (isIn(nr, nc)) {
                    int next = map[nr][nc];
                    if (next != -1) {
                        if (next != 0) {
                            fishes[next].r = fish.r;
                            fishes[next].c = fish.c;
                        }
                        map[fish.r][fish.c] = next;
                        fish.r = nr;
                        fish.c = nc;
                        map[nr][nc] = i;
                        break;
                    }
                }

                nd++;
                if (nd == 9) nd = 1;
                nr = fish.r + DIRECTIONS[nd][0];
                nc = fish.c + DIRECTIONS[nd][1];
                if (nd == startDir) {
                    break;
                }
            }
            fish.dir = nd;
        }
    }

    public static int maxCount = 0;
    public static void simulate(
            final int r,
            final int c,
            final int currentDirection,
            final int eatScore) {

        Fish[] copiedFishes = new Fish[17];
        int[][] copiedMap = new int[4][4];
        copy(copiedMap, copiedFishes);

        final int here = map[r][c];
        fishes[here].alive = false;
        map[r][c] = -1; // eat

        moveFish();
        map[r][c] = 0;

        int nr = r + DIRECTIONS[currentDirection][0];
        int nc = c + DIRECTIONS[currentDirection][1];

        boolean moved = false;
        while (isIn(nr, nc)) {
            if (map[nr][nc] > 0) {
                moved = true;
                simulate(nr, nc, fishes[map[nr][nc]].dir, eatScore + here);

            }

            nr = nr + DIRECTIONS[currentDirection][0];
            nc = nc + DIRECTIONS[currentDirection][1];
        }

        if (!moved) {
            maxCount = Math.max(maxCount, eatScore + here);
        }

        restore(copiedMap, copiedFishes);
        map[r][c] = here;
        fishes[here].alive = true;
    }

    static Fish[] fishes = new Fish[17];
    static int[][] map = new int[4][4];
    static int[][] dir = new int[4][4];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 4; j++) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                map[i][j] = a;

                fishes[a] = new Fish(i, j, b);
            }
        }

        simulate(0, 0, fishes[map[0][0]].dir, 0);

        System.out.println(maxCount);
    }
}
