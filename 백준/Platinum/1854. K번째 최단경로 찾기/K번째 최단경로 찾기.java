import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, m, k;
    static ArrayList<Node>[] arr;
    static PriorityQueue<Long>[] time;
    static long[] result;

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
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new ArrayList[n + 1];
        time = new PriorityQueue[n + 1];
        result = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = new ArrayList<>();
            time[i] = new PriorityQueue<>(Collections.reverseOrder());
        }
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            arr[a].add(new Node(b, c));
        }

        dk();


        for (int i = 1; i <= n; i++) {
            if (time[i].size() < k) {
                result[i] = -1;
            } else {
                result[i] = time[i].peek();
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(result[i]).append("\n");
        }
        System.out.print(sb);
    }

    static void dk() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));
        time[1].add(0L);
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            int nowD = now.destination;
            long nowC = now.cost;
            for (Node next : arr[nowD]) {
                int nextD = next.destination;
                long nextC = next.cost;
                if (time[nextD].size() < k || time[nextD].peek() > nowC + nextC) {
                    if (time[nextD].size() == k) time[nextD].poll();
                    time[nextD].add(nowC + nextC);
                    pq.add(new Node(nextD, nowC + nextC));
                }
            }
        }
    }
}
