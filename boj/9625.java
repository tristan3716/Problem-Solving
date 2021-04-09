import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a=-1, b;
		int k = sc.nextInt();
		int r=-1, o=1, t=1;
		if (k==1) {a = 0; b = 1;}
		else if (k==2) {a = 1; b = 1;}
		else if (k==3) {a = 1; b = 2;}
		else {
			for(int i=3; i<k+1; i++) {
                		if(i==k) a = r;
				r = o + t;
				o = t;
				t = r;
			}
			b = r;
		}  
		System.out.printf("%d %d", a, b);
	}
}