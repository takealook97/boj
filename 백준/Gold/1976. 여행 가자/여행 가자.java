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
    static boolean[] visit;
    static ArrayList<Integer> plan = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        arr = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = new ArrayList<>();
        }
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int info = Integer.parseInt(st.nextToken());
                if (info == 1) {
                    arr[i].add(j);
                }
            }
        }
        String[] line = br.readLine().split(" ");
        for (String num : line) {
            plan.add(Integer.parseInt(num));
        }

        if (plan.size() == 1) {
            System.out.println("YES");
            System.exit(0);
        }
        boolean check = true;
        for (int i = 1; i < plan.size(); i++) {
            int now = plan.get(i - 1);
            int next = plan.get(i);
            check = bfs(now, next);
            if (!check) break;
        }
        if (check) System.out.println("YES");
        else System.out.println("NO");
    }

    static boolean bfs(int departure, int goal) {
        visit = new boolean[N + 1];
        Queue<Integer> queue = new LinkedList<>();
        visit[departure] = true;
        queue.add(departure);
        while (!queue.isEmpty()) {
            int now = queue.poll();
            if (now == goal) return true;
            for (int next : arr[now]) {
                if (!visit[next]) {
                    visit[next] = true;
                    queue.add(next);
                }
            }
        }
        return false;
    }
}
