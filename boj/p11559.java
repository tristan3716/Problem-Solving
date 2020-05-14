package src;

import java.util.*;


public class p11559 {
    private static class Puyo {
        int i;
        int j;
        public Puyo(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public static void main(String[] args) {
        p11559 p = new p11559();
        p.solution();
    }

    int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};

    boolean isIn(int i, int j) {
        return (i >= 0 && j >= 0 && i < 12 && j < 6);
    }

    boolean bfs(int i, int j) {
        Queue<Puyo> q = new LinkedList<>();
        Queue<Puyo> res = new LinkedList<>();
        q.offer(new Puyo(i, j));
        char startType = arr[i][j];

        while (!q.isEmpty()){
            Puyo c = q.poll();
            if (visit[c.i][c.j]){
                continue;
            }
            visit[c.i][c.j] = true;
            res.offer(c);

            for (int[] d : dir){
                int ny = c.i + d[0];
                int nx = c.j + d[1];

                if (isIn(ny, nx) && !visit[ny][nx] && arr[ny][nx] == startType){
                    q.offer(new Puyo(ny, nx));
                }
            }
        }

        boolean result = (res.size() >= 4);
        if (result) {
            while (!res.isEmpty()) {
                Puyo c = res.poll();
                arr[c.i][c.j] = '.';
            }
        }

        return result;
    }

    void initializeVisit(){
        for (int i = 0; i < 12; ++i){
            for (int j = 0; j < 6; j++) {
                visit[i][j] = false;
            }
        }
    }

    boolean explosion() {
        boolean hasExplosion = false;

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                if (arr[i][j] != '.' && !visit[i][j]) {
                    hasExplosion |= bfs(i, j);
                }
            }
        }
        return hasExplosion;
    }
    void gravity(){
        for (int j = 0; j < 6; ++j) {
            int b = 11;
            nextLine:
            for (;;){
                while (arr[b][j] != '.') {
                    b--;
                    if (b < 1){
                        break nextLine;
                    }
                }
                if (b < 1){
                    break;
                }
                int n = b - 1;
                while (arr[n][j] == '.'){
                    n--;
                    if (n < 0) {
                        break nextLine;
                    }
                }
                char temp = arr[b][j];
                arr[b][j] = arr[n][j];
                arr[n][j] = temp;

                b--;
            }
        }
    }

    boolean[][] visit = new boolean[12][6];
    char[][] arr = new char[12][6];
    Scanner sc = new Scanner(System.in);

    void solution() {
        for (int i = 0; i < 12; ++i) {
            String str = sc.next();
            for (int j = 0; j < 6; ++j) {
                arr[i][j] = str.charAt(j);
            }
        }

        int expCnt = 0;
        while (true) {
            boolean hasExplosion = explosion();

            gravity();

            if (!hasExplosion) {
                break;
            } else {
                expCnt++;
            }

            initializeVisit();
        }
        System.out.println(expCnt);
    }
}
