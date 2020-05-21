import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    static Map<String, Integer> map = new HashMap<>();
    public static int decode(String raw) {
        int parity = map.get(raw.substring(49, 56));
        int odd = map.get(raw.substring(0, 7)) +
                map.get(raw.substring(14, 21)) +
                map.get(raw.substring(28, 35)) +
                map.get(raw.substring(42, 49));
        int even = map.get(raw.substring(7, 14)) +
                map.get(raw.substring(21, 28)) +
                map.get(raw.substring(35, 42));
        if ((odd * 3 + even + parity) % 10 == 0) {
            return odd+even+parity;
        }
        else {
            return 0;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map.put("0001101", 0);
        map.put("0011001", 1);
        map.put("0010011", 2);
        map.put("0111101", 3);
        map.put("0100011", 4);
        map.put("0110001", 5);
        map.put("0101111", 6);
        map.put("0111011", 7);
        map.put("0110111", 8);
        map.put("0001011", 9);

        int tc = Integer.parseInt(br.readLine());
        for (int t = 1; t <= tc; ++t) {
            String [] sizes = br.readLine().split(" ");
            int a = Integer.parseInt(sizes[0]);
            int b = Integer.parseInt(sizes[1]);

            for (int i = 0; i < a; ++i) {
                String str = br.readLine();
                int last;
                if ((last = str.lastIndexOf("1")) != -1) {
                    System.out.println("#"+t+" " + decode(str.substring(last-55, last+1)));
                    for (i = i+1;i < a; ++i) { br.readLine(); }
                } else { continue; }
            }
        }
    }
}