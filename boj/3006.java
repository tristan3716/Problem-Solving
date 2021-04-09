import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    int n;
    static int [] tree;
    static int [] idx;

    void update(int i, int diff) {
        while (i <= n){
            tree[i] += diff;
            i += (i & -i);
        }
    }

    int sum(int i) {
        int res = 0;
        while (i > 0) {
            res += tree[i];
            i -= (i & -i);
        }

        return res;
    }

    public void parse() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int N = 16;
        while (N < n) {
            N <<= 1;
        }
        tree = new int[N];
        //arr = new Node[N];
        idx = new int[N];
        for (int i = 1; i <= n; ++i) {
            idx[sc.nextInt()] = i;
            //arr[i] = new Node(sc.nextInt(), i);
            update(i, 1);
        }
    }

    public void sort() {
        //Arrays.sort(arr, 1, n+1, Comparator.comparingInt(node -> node.value));

        int p1 = 1, p2 = n;
        for (int i = 1; i <= n; ++i) {
            if (i % 2 == 1) {
                //System.out.printf("%d\n", sum(arr[p1].idx-1));
                System.out.printf("%d\n", sum(idx[p1]-1));
                //update(arr[p1].idx, -1);
                update(idx[p1], -1);
                p1++;
            }
            else {
                //System.out.printf("%d\n", sum(n) - sum(arr[p2].idx-1)-1);
                System.out.printf("%d\n", sum(n) - sum(idx[p2]-1)-1);
                //update(arr[p2].idx, -1);
                update(idx[p2], -1);
                p2--;
            }
        }
    }

    public static void main(String[] args) {
        Main ts = new Main();
        ts.parse();
        ts.sort();
    }
}