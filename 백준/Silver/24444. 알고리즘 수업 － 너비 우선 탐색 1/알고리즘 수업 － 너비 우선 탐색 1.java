import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
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
            arr[i] = new PriorityQueue<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            arr[u].add(v);
            arr[v].add(u);
        }
        bfs();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(result[i]).append("\n");
        }
        System.out.print(sb);
    }

    static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(R);
        visit[R] = true;
        while (!queue.isEmpty()) {
            int now = queue.poll();
            result[now] = index++;
            while (!arr[now].isEmpty()) {
                int next = arr[now].poll();
                if (!visit[next]) {
                    visit[next] = true;
                    queue.add(next);
                }
            }
        }
    }
}
