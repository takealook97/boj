import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static ArrayList<Node>[] arr;
    static boolean[] visit;
    static int maxLength = 0;
    static int farthestNode;

    static class Node {
        int arrival;
        int cost;

        public Node(int arrival, int cost) {
            this.arrival = arrival;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new ArrayList[N + 1];
        visit = new boolean[N + 1];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            arr[V] = new ArrayList<>();
            while (true) {
                int arrival = Integer.parseInt(st.nextToken());
                if (arrival == -1) break;
                int cost = Integer.parseInt(st.nextToken());
                arr[V].add(new Node(arrival, cost));
            }
        }
        dfs(1, 0);
        visit = new boolean[N + 1];
        dfs(farthestNode, 0);
        System.out.println(maxLength);

    }

    static void dfs(int node, int cost) {
        visit[node] = true;
        if (cost > maxLength) {
            maxLength = cost;
            farthestNode = node;
        }
        for (Node next : arr[node]) {
            int a = next.arrival;
            int c = next.cost;
            if (!visit[a]) {
                dfs(a, cost + c);
            }
        }
        visit[node] = false;
    }
}
