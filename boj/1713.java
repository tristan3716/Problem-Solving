/**
 * @title 후보 추천하기
 * @problem https://www.acmicpc.net/problem/1713
 * @submission https://www.acmicpc.net/source/28206803
 * @date 2021년 4월 9일
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static class Candidate implements Comparable<Candidate> {
        int id;
        int count;
        int date;
        boolean framed;

        @Override
        public int compareTo(Candidate other) {
            if (this.count == other.count) {
                return this.date - other.date;
            }
            return this.count - other.count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        final int k = Integer.parseInt(br.readLine());

        List<Candidate> frames = new LinkedList<>();
        Map<Integer, Candidate> candidates = new HashMap<>();

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < k; i++) {
            int id = Integer.parseInt(st.nextToken());

            Candidate candidate;
            if (candidates.containsKey(id)) {
                candidate = candidates.get(id);
            } else {
                candidate = new Candidate();
                candidates.put(id, candidate);
            }

            if (!candidate.framed) {
                if (frames.size() == n) {
                    Candidate failed = frames.remove(0);
                    failed.framed = false;
                }

                candidate.framed = true;
                candidate.date = i;
                candidate.count = 1;
                candidate.id = id;
                frames.add(candidate);
            } else {
                candidate.count++;
            }
            frames.sort(null);
        }

        List<Integer> result = new ArrayList<>();
        for (Candidate c : frames) {
            result.add(c.id);
        }
        result.sort(null);
        for (int x : result) {
            System.out.printf("%d ", x);
        }
    }
}