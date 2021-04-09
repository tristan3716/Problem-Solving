import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	private static int lowerBound(List<Integer> arr, int val) {
		if (arr.isEmpty()) {
			return -1;
		}
		int start = 0;
		int end = arr.size();
		int mid = 0;
		while (true) {
			mid = (start + end) / 2;
			if (start >= end) {
				break;
			}
			if (val > arr.get(mid)) {
				start = mid + 1;
			}
			else {
				end = mid;
			}
		}
		
		return mid;
	}
    public static void main(String[] args)  throws Exception {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
         
        while(sc.hasNext()) {
            int n = sc.nextInt();
             
            int [] arr = new int[n];
            List<Integer> l = new ArrayList<>();
            
            for (int i = 0; i < n; ++i) {
            	arr[i] = sc.nextInt();
            }
            for (int i = 0; i < n; ++i) {
            	int k = arr[i];
            	int b = lowerBound(l, k);
            	if (b == l.size() || b == -1) {
            		l.add(k);
            	}
            	else {
            		l.set(b, k);
            	}
            }
            sb.append(l.size()).append('\n');
        }
        System.out.print(sb);
        sc.close();
    }
}