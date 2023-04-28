import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static ArrayList<Node>[] arr;
    static int[] distance;
    static boolean[] visit;
    static int INF = 1000000;

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
        n = Integer.parseInt(br.readLine());
        arr = new ArrayList[n + 1];
        distance = new int[n + 1];
        visit = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = new ArrayList<>();
        }

        if(n == 1){
            System.out.println(0);
            System.exit(0);
        }

        for (int i = 1; i <= n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            arr[a].add(new Node(b, c));
            arr[b].add(new Node(a, c));
        }
        dk(1);
        int toDeepest = 0;
        int deepestIndex = 0;
        for (int i = 1; i < distance.length; i++) {
            if (distance[i] > toDeepest) {
                toDeepest = distance[i];
                deepestIndex = i;
            }
        }
        dk(deepestIndex);
        int toFarthest = 0;
        for (int i = 1; i < distance.length; i++) {
            if (distance[i] > toFarthest) {
                toFarthest = distance[i];
            }
        }
        System.out.println(toFarthest);
    }

    static int dk(int departure) {
        Arrays.fill(distance, INF);
        visit = new boolean[n + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        distance[departure] = 0;
        pq.add(new Node(departure, 0));
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
        return distance[departure];
    }
}