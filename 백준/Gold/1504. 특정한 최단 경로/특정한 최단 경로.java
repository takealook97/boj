import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int E;
    static ArrayList<Node>[] arr;
    static int[] distance;
    static boolean[] visit;
    static int INF = 200000000;

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
        E = Integer.parseInt(st.nextToken());
        arr = new ArrayList[N + 1];
        distance = new int[N + 1];
        visit = new boolean[N + 1];
        for (int i = 0; i <= N; i++) {
            arr[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            arr[a].add(new Node(b, c));
            arr[b].add(new Node(a, c));
        }
        st = new StringTokenizer(br.readLine());
        int u = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());

        int uFirst = dk(1, u) + dk(u, v) + dk(v, N);
        int vFirst = dk(1, v) + dk(v, u) + dk(u, N);

        if (uFirst >= INF && vFirst >= INF) System.out.println(-1);
        else System.out.println(Math.min(uFirst, vFirst));
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