import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, W;
    static ArrayList<Node>[] arr;
    static long[] distance;
    static long INF = 250_000_000;

    static class Node {
        int destination;
        long cost;

        public Node(int destination, long cost) {
            this.destination = destination;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < TC; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            arr = new ArrayList[N + 1];
            distance = new long[N + 1];
            for (int i = 0; i <= N; i++) {
                arr[i] = new ArrayList<>();
            }

            for (int i = 1; i <= M; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());
                arr[S].add(new Node(E, T));
                arr[E].add(new Node(S, T));
            }
            for (int i = 1; i <= W; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());
                arr[S].add(new Node(E, -T));
            }

            if (bf()) sb.append("YES");
            else sb.append("NO");
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static boolean bf() {
        boolean check = true;
        Arrays.fill(distance, INF);
        distance[0] = 0;
        int count = 0;
        while (check && count < N) {
            check = false;
            count++;
            for (int now = 1; now <= N; now++) {
                for (Node next : arr[now]) {
                    if (distance[next.destination] > distance[now] + next.cost) {
                        distance[next.destination] = distance[now] + next.cost;
                        check = true;
                    }
                }
            }
        }
        return check;
    }
}
