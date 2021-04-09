public class Main {
	static int n;
	static byte[] buf = new byte[0b100<<16];
	static int ridx = 0;

	static boolean isBlank() {
		if (buf[ridx] == '\n') {
			ridx++;
			return true;
		}
		else {
			return false;
		}
	}

	static int readInt() {
		int res = 0;
		while(!isBlank()) {
			res = 10 * res + buf[ridx++] - '0';
		}
		return res;
	}
	
	public static void main(String[] args) throws Exception {
		System.in.read(buf);
		n = readInt();
		if (n > 23000) {
			n = 23000;
		}
		System.out.println(n);
		long sum = 0;
		for (int i = 0; i < n; ++i) {
			sum += readInt();
		}
		System.out.print(sum);
	}
}