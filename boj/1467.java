import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = br.readLine().chars()
                .map(e -> e - '0')
                .toArray();
        int[] ks = br.readLine().chars()
                .map(e -> e - '0')
                .toArray();
        int[] count = new int[10];
        for (int x : arr) {
            count[x]++;
        }
        for (int x : ks) {
            count[x]--;
        }

        StringBuilder sb = new StringBuilder();
        int completedIndex = 0;
        for (int x = 9; x >= 0; x--) {
            if (count[x] != 0) {
                for (int selectedIndex = completedIndex; selectedIndex < arr.length; selectedIndex++) {
                    if (arr[selectedIndex] == x) {
                        int[] leftCount = new int[10];
                        System.arraycopy(count, 0, leftCount, 0, 10);
                        for (int k = selectedIndex; k < arr.length; k++) {
                            leftCount[arr[k]]--;
                        }
                        boolean possible = true;
                        for (int k = 0; k < 10; k++) {
                            if (leftCount[k] > 0) {
                                possible = false;
                                break;
                            }
                        }
                        if (possible) {
                            completedIndex = selectedIndex + 1;
                            sb.append(x);
                            count[x]--;
                            x = 10;
                        }
                        break;
                    }
                }
            }
        }

        System.out.print(sb);
    }
}
