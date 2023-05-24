import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static ArrayList<Integer>[] arr;
    static int[] count;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new ArrayList[N + 1];
        count = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int b = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            arr[a].add(b);
        }
        for (int i = 1; i <= N; i++) {
            bfs(i);
        }
        int max = 0;
        for (int i = 1; i <= N; i++) {
            max = Math.max(max, count[i]);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (count[i] == max) sb.append(i).append(" ");
        }
        System.out.println(sb);
    }

    static void bfs(int departure) {
        visit = new boolean[N + 1];
        Queue<Integer> queue = new LinkedList<>();
        visit[departure] = true;
        queue.add(departure);
        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int next : arr[now]) {
                if (!visit[next]) {
                    visit[next] = true;
                    count[departure]++;
                    queue.add(next);
                }
            }
        }
    }
}
