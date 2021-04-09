import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Main {
    private static class ChangeBall{
        int index;
        int color;

        public ChangeBall(int index, int color) {
            this.index = index;
            this.color = color;
        }
    }
    private static class Ball {
        int color;
        int consecutiveCount;

        public Ball(int color, int consecutiveCount) {
            this.color = color;
            this.consecutiveCount = consecutiveCount;
        }
    }

    public static int simulate(int n, int[] arr) {
        ArrayDeque<Ball> stack = new ArrayDeque<>();
        int last = 0;
        int consecutiveCount = 0;
        for (int current : arr) {
            while (consecutiveCount >= 4 && current != last) {
                for (int i = 0; i < consecutiveCount; i++) {
                    stack.pollLast();
                }

                if (!stack.isEmpty()) {
                    Ball top = stack.peekLast();
                    last = top.color;
                    consecutiveCount = top.consecutiveCount;
                } else {
                    last = 0;
                    consecutiveCount = 0;
                }
            }

            if (last == current) {
                consecutiveCount += 1;
            } else {
                consecutiveCount = 1;
            }

            stack.offerLast(new Ball(current, consecutiveCount));

            last = current;
        }

        if (consecutiveCount >= 4) {
            for (int i = 0; i < consecutiveCount; i++) {
                stack.pollLast();
            }
        }

        return stack.size();
    }

    public static List<ChangeBall> findPositions(int n, int[] arr) {
        List<ChangeBall> list = new ArrayList<>();

        int last = 0;
        int consecutiveCount = 0;
        for (int i = 0; i < n; i++) {
            int current = arr[i];

            if (last == current) {
                consecutiveCount += 1;
            } else {
                switch (consecutiveCount) {
                    case 2:
                        if (i + 1 < n && arr[i + 1] == arr[i - 2]) {
                            ChangeBall ball = new ChangeBall(i, arr[i + 1]);
                            list.add(ball);
                        }
                        break;
                    case 3:
                        if (i - 3 >= 0) {
                            ChangeBall ball =new ChangeBall(i, arr[i - 3]);
                            list.add(ball);
                        }

                }

                consecutiveCount = 1;
            }

            last = current;
        }

        last = 0;
        consecutiveCount = 0;
        for (int i = n - 1; i >= 0; i--) {
            int current = arr[i];

            if (last == current) {
                consecutiveCount += 1;
            } else {
                switch (consecutiveCount) {
                    case 2:
                        if (i - 1 >= 0 && arr[i - 1] == arr[i + 2]) {
                            ChangeBall ball = new ChangeBall(i, arr[i - 1]);
                            list.add(ball);
                        }
                        break;
                    case 3:
                        if (i + 3 < n) {
                            ChangeBall ball = new ChangeBall(i, arr[i + 3]);
                            list.add(ball);
                        }

                }

                consecutiveCount = 1;
            }

            last = current;
        }

        return list;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        int answer = Integer.MAX_VALUE;
        List<ChangeBall> list = findPositions(n, arr);
        for (ChangeBall x : list) {
            int index = x.index;
            int color = x.color;
            int oldColor = arr[index];
            arr[index] = color;
            int count = simulate(n, arr);
            answer = Math.min(answer, count);
            arr[index] = oldColor;
        }

        System.out.println(answer);
    }
}
