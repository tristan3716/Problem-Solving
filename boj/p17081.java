package src;

import java.util.*;

class Point {
    int r, c;

    public Point(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

class Character extends Point {
    int atk, def;
    int remainHp, maxHp;

    public Character(int r, int c, int atk, int def, int maxHp) {
        super(r, c);
        this.atk = atk;
        this.def = def;
        this.remainHp = maxHp;
        this.maxHp = maxHp;
    }

    int getAtk() {
        return atk;
    }

    int getDef() {
        return def;
    }

    void heal(){
        remainHp = maxHp;
    }
    void heal(int x){
        remainHp += x;
        remainHp = Math.min(remainHp, maxHp);
    }

    void getAttacked(int atk){
        remainHp -= Math.max(1, atk-getDef());
        remainHp = Math.max(0, remainHp);
    }

    boolean isDead(){
        return remainHp <= 0;
    }
}

class Monster extends Character {
    String name;
    int exp;
    boolean isBoss;

    public Monster(int r, int c, int atk, int def, int maxHp, String name, int exp, boolean isBoss) {
        super(r, c, atk, def, maxHp);
        this.name = name;
        this.exp = exp;
        this.isBoss = isBoss;
    }
}

class Item extends Point {
    int type;
    int val;
    String name;

    public Item(int r, int c, int type, int val) {
        super(r, c);
        this.type = type;
        this.val = val;
    }

    public Item(int r, int c, int type, String name) {
        super(r, c);
        this.type = type;
        this.name = name;
    }
}

class Equipment {
    Set<String> list;

    public Equipment() {
        this.list = new HashSet<>();
    }

    void equip(String s){
        if (list.size() == 4)
            return;
        list.add(s);
    }

    boolean has(String s){
        return list.contains(s);
    }

    void useRE(){
        list.remove("RE");
    }
}

class Player extends Character {
    int lv;
    int maxExp, curExp;
    int sr, sc;
    int weapon, armor;
    Equipment accList;

    boolean isBossKilled;
    String reason;

    public Player(int r, int c) {
        super(r, c, 2, 2, 20);
        this.lv = 1;
        this.maxExp = 5;
        this.curExp = 0;
        this.sr = r;
        this.sc = c;
        this.weapon = 0;
        this.armor = 0;
        this.isBossKilled = false;
        this.accList = new Equipment();
    }

    @Override
    int getAtk(){
        return atk + weapon;
    }

    @Override
    int getDef(){
        return def + armor;
    }

    void move(int r, int c){
        this.r = r;
        this.c = c;
    }

    void equipWeapon(int v){
        this.weapon = v;
    }
    void equipArmor(int v){
        this.armor = v;
    }

    void getBox(Item item){
        switch (item.type){
            case 1:
                this.equipWeapon(item.val);
                break;
            case 2:
                this.equipArmor(item.val);
                break;
            case 3:
                this.accList.equip(item.name);
                break;
        }
    }

    void getTrap(){
        if (accList.has("DX")){
            remainHp -= 1;
        }
        else {
            remainHp -= 5;
        }
    }

    void levelUp() {
        lv++;
        maxHp += 5;
        atk += 2;
        def += 2;
        remainHp = maxHp;
        curExp = 0;
        maxExp = lv * 5;
    }

    void getExp(int x) {
        if (accList.has("EX")) {
            x = (int)(x * 1.2);
        }
        curExp += x;

        if (curExp >= maxExp) {
            levelUp();
        }
    }

    void fight(Monster m){
        boolean isFirstPlayerAttack = true;
        boolean hasFirstAttackImmune = false;
        if (m.isBoss && accList.has("HU")){
            heal();
            hasFirstAttackImmune = true;
        }

        while (true){
            // player's turn
            int atk = getAtk();
            if (isFirstPlayerAttack) {
                if (accList.has("CO")){
                    if (accList.has("DX")){
                        atk *= 3;
                    }
                    else {
                        atk *= 2;
                    }
                }
                isFirstPlayerAttack = false;
            }
            m.getAttacked(atk);

            if (m.isDead()){
                getExp(m.exp);
                if (accList.has("HR")){
                    heal(3);
                }
                break;
            }

            // monster's turn

            atk = m.getAtk();
            if (hasFirstAttackImmune){
                hasFirstAttackImmune = false;
            }
            else {
                this.getAttacked(atk);
            }
            if (this.isDead()){
                if (accList.has("RE")){
                    revive();
                    m.heal();
                }
                else {
                    reason = m.name;
                }
                break;
            }
        }
    }

    void revive(){
        accList.useRE();
        heal();
        move(sr, sc);
    }

    void printStatus() {
        System.out.printf("LV : %d\n", lv);
        System.out.printf("HP : %d/%d\n", remainHp, maxHp);
        System.out.printf("ATT : %d+%d\n", atk, weapon);
        System.out.printf("DEF : %d+%d\n", def, armor);
        System.out.printf("EXP : %d/%d\n", curExp, maxExp);

        if (isBossKilled){
            System.out.println("YOU WIN!");
        } else if (!isDead()){
            System.out.println("Press any key to continue.");
        } else{
            System.out.println("YOU HAVE BEEN KILLED BY " + reason + "..\n");
        }
    }
}

class Game{
    Player player;
    Map<Integer, Monster> mobList = new HashMap<>();
    Map<Integer, Item> itemList = new HashMap<>();

    int n, m;
    int passedTurns;
    char[][] grid;
    char[] commands;

    int getKey(int r, int c){
        return r * 1234 + c;
    }

    static boolean foundBoss = false;
    boolean isBoss(int r, int c, int bossR, int bossC) {
        if (foundBoss) {
            return false;
        }
        if (r == bossR && c == bossC){
            foundBoss = true;
            return true;
        }
        return false;
    }

    public Game() {
        Scanner sc = new Scanner(System.in);
        this.passedTurns = 0;
        this.n = sc.nextInt();
        this.m = sc.nextInt();
        int K = 0;
        int L = 0;
        int bossR = -1, bossC = -1;

        grid = new char[n][m];
        for (int i = 0; i < n; i++) {
            String str = sc.next();
            for (int j = 0; j < m; j++) {
                char ch = str.charAt(j);
                if (ch == '&'){
                    K++;
                } else if (ch == 'B'){
                    L++;
                } else if (ch == 'M') {
                    K++;
                    bossC = j;
                    bossR = i;
                } else if (ch == '@') {
                    ch = '.';
                    player = new Player(i, j);
                }
                grid[i][j] = ch;
            }
        }

        String rawCommands = sc.next();
        commands = new char[rawCommands.length()];
        for (int i = 0; i < rawCommands.length(); i++) {
            commands[i] = rawCommands.charAt(i);
        }

        for (int i = 0; i < K; i++) {
            int r = sc.nextInt() - 1;
            int c = sc.nextInt() - 1;
            String name = sc.next();
            int atk = sc.nextInt();
            int def = sc.nextInt();
            int hp = sc.nextInt();
            int exp = sc.nextInt();

            mobList.put(getKey(r, c), new Monster(r, c, atk, def, hp, name, exp, isBoss(r, c, bossR, bossC)));
        }

        for (int i = 0; i < L; i++) {
            int r = sc.nextInt() - 1;
            int c = sc.nextInt() - 1;
            char type = sc.next().charAt(0);
            String name = sc.next();

            if (type == 'W') {
                itemList.put(getKey(r, c), new Item(r, c, 1, Integer.parseInt(name)));
            } else if (type == 'A') {
                itemList.put(getKey(r, c), new Item(r, c, 2, Integer.parseInt(name)));
            } else {
                itemList.put(getKey(r, c), new Item(r, c, 3, name));
            }
        }
    }

    static final int[][] d = {{0,-1}, {0,1}, {-1,0}, {1,0}};

    boolean isIn(int r, int c){
        return (r >= 0 && r < n && c >= 0 && c < m);
    }

    boolean isMovable(int r, int c){
        return (grid[r][c] != '#');
    }

    void move(char rdir){
        int nr = player.r;
        int nc = player.c;

        int dir = -1;
        switch (rdir){
            case 'L':
                dir = 0; break;
            case 'R':
                dir = 1; break;
            case 'U':
                dir = 2; break;
            case 'D':
                dir = 3; break;
        }
        nr += d[dir][0];
        nc += d[dir][1];

        if (isIn(nr, nc) && isMovable(nr, nc)){
            player.move(nr, nc);
        }
    }

    boolean Do (){
        int r = player.r;
        int c = player.c;
        switch (grid[r][c]){
            case 'B':
                player.getBox(itemList.get(getKey(r,c)));
                grid[r][c] = '.';
                break;
            case '^':
                player.getTrap();
                if (player.isDead()){
                    if(player.accList.has("RE")){
                        player.revive();
                    }
                    else {
                        player.reason = "SPIKE TRAP";
                    }
                }
                break;
            case 'M':
            case '&':
                Monster m = mobList.get(getKey(r, c));
                player.fight(m);
                if (m.isDead()){
                    if (m.isBoss){
                        return true;
                    }
                    mobList.remove(getKey(r, c));
                    grid[r][c] = '.';
                }
                break;
        }
        return false;
    }

    void play(){
        passedTurns = 0;
        while (passedTurns < commands.length){
            move(commands[passedTurns]);
            boolean isEnd = Do();
            passedTurns++;
            if (isEnd){
                player.isBossKilled = true;
                break;
            }
            if (player.isDead()){
                break;
            }
        }
    }

    void printGrid(){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
    }

    void printResult(){
        if (!player.isDead()){
            grid[player.r][player.c] = '@';
        }
        printGrid();
        System.out.printf("Passed Turns : %d\n", passedTurns);
        player.printStatus();
    }
}

public class p17081 {
    public static void main(String[] args) {
        Game game = new Game();
        game.play();
        game.printResult();
    }
}