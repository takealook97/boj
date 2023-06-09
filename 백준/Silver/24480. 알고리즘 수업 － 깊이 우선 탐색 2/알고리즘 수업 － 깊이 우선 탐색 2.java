import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, R;
    static PriorityQueue<Integer>[] arr;
    static boolean[] visit;
    static int[] result;
    static int index = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        arr = new PriorityQueue[N + 1];
        visit = new boolean[N + 1];
        result = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = new PriorityQueue<>(Collections.reverseOrder());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a].add(b);
            arr[b].add(a);
        }
        result[R] = index;
        visit[R] = true;
        dfs(R);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(result[i]).append("\n");
        }
        System.out.print(sb);
    }

    static void dfs(int departure) {
        while (!arr[departure].isEmpty()) {
            int next = arr[departure].poll();
            if (!visit[next]) {
                visit[next] = true;
                result[next] = ++index;
                dfs(next);
            }
        }
    }
}
