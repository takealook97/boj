import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static ArrayList<Integer>[] arr;
    static int[] answer;

    @SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new ArrayList[N + 1];
        answer = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            arr[A].add(B);
            answer[B]++;
        }

        StringBuilder sb = new StringBuilder();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (answer[i] == 0) queue.add(i);
        }
        while (!queue.isEmpty()) {
            sb.append(queue.peek()).append(" ");
            int now = queue.poll();
            for (int next : arr[now]) {
                answer[next]--;
                if (answer[next] == 0) {
                    queue.add(next);
                }
            }
        }
        System.out.println(sb);
    }
}
