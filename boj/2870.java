import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		List<String> result = new ArrayList<>();
		int t = sc.nextInt();
		while (t-- != 0) {
			String raw = sc.next();
			
			String[] arr = raw.split("[a-z]+");
			
			for (String x : arr) {
				if (x.length() > 0) {
					x = x.replaceAll("^0*", "");
					if (x.length() > 0)
						result.add(x);
					else
						result.add("0");
				}
			}
		}

		result.sort(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if (o1.length() == o2.length()) {
					return o1.compareTo(o2);
				}
				else {
					return o1.length() - o2.length();
				}
			}
		});
		
		for (String x : result) {
			System.out.println(x);
		}
	}
}