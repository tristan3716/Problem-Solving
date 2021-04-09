/**
 * 카탈란수
 */

import java.util.Scanner;

public class Main {
    public static void main(String [] args) {
        long [] cheat = {1L, 
                        1L, 2L, 5L, 14L, 42L, 132L, 429L, 1430L, 4862L, 16796L, 
                        58786L, 208012L, 742900L, 2674440L, 9694845L,
                        35357670L, 129644790L, 477638700L, 1767263190L, 6564120420L,
                        24466267020L, 91482563640L, 343059613650L, 1289904147324L, 4861946401452L,
                        18367353072152L, 69533550916004L, 263747951750360L, 1002242216651368L, 3814986502092304L};
		Scanner sc = new Scanner(System.in);
        while (true) {
            int n = sc.nextInt();
            if (n == 0) {
                break;
            }
            else {
                System.out.println(cheat[n]);
            }
        }
    }
}