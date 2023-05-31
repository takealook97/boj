import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static ArrayList<Node>[] arr;
    static int[] distance;
    static boolean[] visit;
    static int INF = 987654321;
    static int result = Integer.MAX_VALUE;


    static class Node implements Comparable<Node> {
        int destination, cost;

        public Node(int destination, int cost) {
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
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        distance = new int[N + 1];
        arr = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = new ArrayList<>();
        }
        for (int i = 1; i <= M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            arr[a].add(new Node(b, c));
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int departure = Integer.parseInt(st.nextToken());
        int arrival = Integer.parseInt(st.nextToken());
        dk(departure, arrival);
        System.out.println(result);
    }

    static void dk(int departure, int arrival) {
        Arrays.fill(distance, INF);
        visit = new boolean[N + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(departure, 0));
        distance[departure] = 0;
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            int nowD = now.destination;
            int nowC = now.cost;
            if (nowD == arrival) {
                result = Math.min(result, distance[nowD]);
            }
            if (!visit[nowD]) {
                visit[nowD] = true;
                for (Node next : arr[nowD]) {
                    int nextD = next.destination;
                    int nextC = next.cost;
                    if (distance[nextD] > nowC + nextC) {
                        distance[nextD] = nowC + nextC;
                        pq.add(new Node(nextD, distance[nextD]));
                    }
                }
            }
        }
    }
}
