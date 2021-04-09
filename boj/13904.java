import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static class Job implements Comparable<Job>{
        int deadline;
        int score;

        public Job(int deadline, int score) {
            this.deadline = deadline;
            this.score = score;
        }

        @Override
        public int compareTo(Job job) {
            if (job.score == this.score) {
                return job.deadline - this.deadline;
            } else {
                return job.score - this.score;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());

        Job[] jobs = new Job[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int deadline = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());
            jobs[i] = new Job(deadline, score);
        }

        Arrays.sort(jobs);
        int[] scores = new int[n];
        int[] deadlines = new int[n];
        for (int i = 0; i < n; i++) {
            scores[i] = jobs[i].score;
            deadlines[i] = jobs[i].deadline;
        }

        int[] parents = new int[1001];
        for (int i = 0; i < 1001; i++) {
            parents[i] = i;
        }
        parents[0] = -1;
        int answer = 0;
        boolean[] occupied = new boolean[1001];
        for (int i = 0; i < n; i++) {
            int p = find(parents, deadlines[i]);
            if (!occupied[p]) {
                occupied[p] = true;
                union(parents, deadlines[i]);
                answer += scores[i];
            }
        }

        System.out.println(answer);
    }

    private static int find(int[] parents, int x) {
        return x == parents[x] ? x : (parents[x] = find(parents, parents[x]));
    }

    private static void union(int[] parents, int a) {
        a = find(parents, a);
        int ap = find(parents, a) - 1;
        if (ap > 0) {
            int next = find(parents, ap);
            if (a != next) {
                parents[a] = next;
            }
        }
    }
}
