import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static long[] sumArr;
    static Map<Long, Long> map = new HashMap<>();
    static long answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        sumArr = new long[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            long num = Long.parseLong(st.nextToken());
            sumArr[i] = sumArr[i - 1] + num;
        }

        map.put(0L, 1L);

        for (int i = 1; i <= N; i++) {
            long cur = sumArr[i];
            if (map.containsKey(cur - K)) {
                answer += map.get(cur - K);
            }
            map.put(cur, map.getOrDefault(cur, 0L) + 1);
        }

        System.out.println(answer);
    }
}
