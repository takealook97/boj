import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static List<Integer>[] listArr;
    static boolean[] visited, isCycle;
    static int[] distance, parent;
    static boolean check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        listArr = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        isCycle = new boolean[N + 1];
        distance = new int[N + 1];
        parent = new int[N + 1];
        check = false;

        for (int i = 1; i <= N; i++) {
            listArr[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            listArr[from].add(to);
            listArr[to].add(from);
        }

        findCycle(1, -1);

        Arrays.fill(distance, -1);
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if (isCycle[i]) {
                queue.add(i);
                distance[i] = 0;
            }
        }

        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int next : listArr[now]) {
                if (distance[next] == -1) {
                    distance[next] = distance[now] + 1;
                    queue.add(next);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(distance[i]).append(" ");
        }
        System.out.println(sb);
    }

    static boolean findCycle(int now, int par) {
        visited[now] = true;
        for (int next : listArr[now]) {
            if (!visited[next]) {
                parent[next] = now;
                if (findCycle(next, now)) {
                    if (!check) {
                        isCycle[now] = true;
                        if (now == next) {
                            check = true;
                        }
                    }
                    return true;
                }
            } else if (next != par) {
                markCycle(now, next);
                return true;
            }
        }
        return false;
    }

    static void markCycle(int start, int end) {
        check = true;
        isCycle[start] = true;
        while (start != end) {
            start = parent[start];
            isCycle[start] = true;
        }
    }
}
