import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] time;
    static int[] index;
    static ArrayList<Integer>[] arr;
    static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        time = new int[N + 1];
        index = new int[N + 1];
        arr = new ArrayList[N + 1];
        result = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = new ArrayList<>();
        }
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            while (true) {
                int input = Integer.parseInt(st.nextToken());
                if (input == -1) break;
                arr[input].add(i);
                index[i]++;
            }
        }

        ts();

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(result[i] + time[i]).append("\n");
        }
        System.out.print(sb);
    }

    static void ts() {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (index[i] == 0) queue.add(i);
        }
        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int i = 0; i < arr[now].size(); i++) {
                int next = arr[now].get(i);
                index[next]--;
                result[next] = Math.max(result[next], result[now] + time[now]);
                if (index[next] == 0) queue.add(next);
            }
        }
    }
}
