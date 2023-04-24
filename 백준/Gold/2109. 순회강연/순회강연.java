import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static class Lecture implements Comparable<Lecture> {
        int p;
        int d;

        public Lecture(int p, int d) {
            this.p = p;
            this.d = d;
        }

        @Override
        public int compareTo(Lecture o) {
            return o.p - this.p;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Lecture> arr = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            arr.add(new Lecture(p, d));
        }
        Collections.sort(arr);
        boolean[] visited = new boolean[10001];
        int sum = 0;
        for (Lecture lecture : arr) {
            for (int i = lecture.d; i >= 1; i--) {
                if (!visited[i]) {
                    visited[i] = true;
                    sum += lecture.p;
                    break;
                }
            }
        }
        System.out.println(sum);
    }
}
