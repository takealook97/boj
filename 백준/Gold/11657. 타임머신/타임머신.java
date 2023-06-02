import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static ArrayList<Node>[] arr;
    static long[] distance;
    static int INF = 987654321;

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
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new ArrayList[N + 1];
        distance = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            arr[a].add(new Node(b, c));
        }

        if (!bf()) {
            System.out.println(-1);
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 2; i <= N; i++) {
                if (distance[i] == INF || arr[1].isEmpty()) distance[i] = -1;
                sb.append(distance[i]).append("\n");
            }
            System.out.print(sb);
        }
    }

    static boolean bf() {
        Arrays.fill(distance, INF);
        distance[1] = 0;
        boolean check = false;
        for (int i = 1; i < N; i++) {
            check = false;
            for (int now = 1; now <= N; now++) {
                for (Node next : arr[now]) {
                    if (distance[now] == INF) break;

                    int nextD = next.destination;
                    long nextC = next.cost;
                    if (distance[nextD] > distance[now] + nextC) {
                        distance[nextD] = distance[now] + nextC;
                        check = true;
                    }
                }
            }
            if (!check) break;
        }

        if (check) {
            for (int now = 1; now <= N; now++) {
                for (Node next : arr[now]) {
                    if (distance[now] == INF) break;

                    int nextD = next.destination;
                    long nextC = next.cost;
                    if (distance[nextD] > distance[now] + nextC) return false;
                }
            }
        }
        return true;
    }
}
