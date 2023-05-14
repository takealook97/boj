import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
    static int N;
    static ArrayList<Long> sum = new ArrayList<>();
    static PriorityQueue<Long> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            pq.add(Long.parseLong(br.readLine()));
        }

        if (pq.size() == 1) {
            System.out.println(0);
            System.exit(0);
        }
        while (pq.size() > 1) {
            long a = pq.poll();
            long b = pq.poll();
            sum.add(a + b);
            pq.add(a + b);
        }
        long result = 0;
        for (long i : sum) {
            result += i;
        }
        System.out.println(result);
    }
}
