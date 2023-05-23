import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K, X;
    static ArrayList<Integer>[] arr;
    static boolean[] visit;
    static int[] distance;
    static int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        arr = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = new ArrayList<>();
        }
        distance = new int[N + 1];
        Arrays.fill(distance, INF);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            arr[Integer.parseInt(st.nextToken())].add(Integer.parseInt(st.nextToken()));
        }
        dk(X);
        StringBuilder sb = new StringBuilder();
        boolean check = false;
        for (int i = 1; i <= N; i++) {
            if (distance[i] == K) {
                sb.append(i).append("\n");
                if (!check) check = true;
            }
        }
        if (!check) System.out.println(-1);
        else System.out.print(sb);
    }

    static void dk(int departure) {
        Arrays.fill(distance, INF);
        visit = new boolean[N + 1];
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(departure);
        distance[departure] = 0;
        while (!pq.isEmpty()) {
            int now = pq.poll();
            if (!visit[now]) {
                for (int next : arr[now]) {
                    if (distance[next] > distance[now] + 1) {
                        distance[next] = distance[now] + 1;
                        pq.add(next);
                    }
                }
            }
        }

    }
}
