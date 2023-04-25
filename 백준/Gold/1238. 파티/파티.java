import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, X;
    static ArrayList<Node>[] arr;
    static int[] distance;
    static boolean[] visit;
    static int INF = 100000000;
    static int result = 0;

    static class Node implements Comparable<Node> {
        int destination;
        int cost;

        public Node(int destination, int cost) {
            this.destination = destination;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        arr = new ArrayList[N + 1];
        distance = new int[N + 1];
        visit = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            arr[i] = new ArrayList<>();
        }
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            arr[a].add(new Node(b, c));
        }

        for (int i = 1; i <= N; i++) {
            result = Math.max(result, dk(i, X) + dk(X, i));
        }
        System.out.println(result);

    }

    static int dk(int departure, int arrival) {
        Arrays.fill(distance, INF);
        visit = new boolean[N + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(departure, 0));
        distance[departure] = 0;
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            int nowD = now.destination;
            int nowC = now.cost;

            if (!visit[nowD]) {
                visit[nowD] = true;
                for (Node next : arr[nowD]) {
                    int nextD = next.destination;
                    int nextC = next.cost;
                    if (!visit[nextD] && distance[nextD] > nowC + nextC) {
                        distance[nextD] = nowC + nextC;
                        pq.add(new Node(nextD, distance[nextD]));
                    }
                }
            }
        }
        return distance[arrival];
    }
}
