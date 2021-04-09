import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int ZongMalCount = sc.nextInt();
		int num = 665;
		for (int i = 0; i < ZongMalCount; ) {
			num++;
			if (String.valueOf(num).contains("666")) {
				i++;
			}
		}
		
		System.out.println(num);
	}
}