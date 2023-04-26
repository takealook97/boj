import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int V, E, K;
    static ArrayList<Node>[] arr;
    static long[] distance;
    static boolean[] visit;
    static long INF = 60_000_000_000L;

    static class Node implements Comparable<Node> {
        int destination;
        long cost;

        public Node(int destination, long cost) {
            this.destination = destination;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());
        arr = new ArrayList[V + 1];
        distance = new long[V + 1];
        visit = new boolean[V + 1];

        for (int i = 1; i <= V; i++) {
            arr[i] = new ArrayList<>();
        }
        for (int i = 1; i <= E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            arr[u].add(new Node(v, w));
        }

        dk(K);

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= V; i++){
            if(distance[i] == INF) sb.append("INF");
            else sb.append(distance[i]);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void dk(int departure) {
        Arrays.fill(distance, INF);
        visit = new boolean[V + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(departure, 0));
        distance[departure] = 0;
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            int nowD = now.destination;
            long nowC = now.cost;

            if (!visit[nowD]) {
                visit[nowD] = true;
                for (Node next : arr[nowD]) {
                    int nextD = next.destination;
                    long nextC = next.cost;
                    if (distance[nextD] > nowC + nextC) {
                        distance[nextD] = nowC + nextC;
                        pq.add(new Node(nextD, distance[nextD]));
                    }
                }
            }
        }
    }
}
