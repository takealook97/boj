import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, a, b, d, answer = 0;
    static ArrayList<Edge>[] listArr;
    static long[] foxDist;
    static long[][] wolfDist;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    static final long INF = Long.MAX_VALUE / 2;

    static class Edge implements Comparable<Edge> {
        int node;
        long weight;
        int status;

        public Edge(int node, long weight) {
            this.node = node;
            this.weight = weight;
            this.status = 0;
        }

        public Edge(int node, long weight, int status) {
            this.node = node;
            this.weight = weight;
            this.status = status;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        listArr = new ArrayList[N + 1];
        foxDist = new long[N + 1];
        wolfDist = new long[N + 1][2];
        for (int i = 1; i <= N; i++) {
            listArr[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            listArr[a].add(new Edge(b, d * 2));
            listArr[b].add(new Edge(a, d * 2));
        }

        searchFoxPath();
        searchWolfPath();

        for (int i = 2; i <= N; i++) {
            if (foxDist[i] < Math.min(wolfDist[i][0], wolfDist[i][1])) {
                answer++;
            }
        }

        System.out.println(answer);
    }

    static void searchFoxPath() {
        Arrays.fill(foxDist, INF);
        pq.add(new Edge(1, 0));
        foxDist[1] = 0;

        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            if (foxDist[now.node] < now.weight) continue;

            for (Edge next : listArr[now.node]) {
                if (foxDist[next.node] > foxDist[now.node] + next.weight) {
                    foxDist[next.node] = foxDist[now.node] + next.weight;
                    pq.add(new Edge(next.node, foxDist[next.node]));
                }
            }
        }
    }

    static void searchWolfPath() {
        pq.clear();
        for (int i = 1; i <= N; i++) {
            Arrays.fill(wolfDist[i], INF);
        }
        pq.add(new Edge(1, 0, 0));
        wolfDist[1][0] = 0;

        while (!pq.isEmpty()) {
            Edge now = pq.poll();

            if (wolfDist[now.node][now.status] < now.weight) continue;

            for (Edge next : listArr[now.node]) {
                long nextDist;
                if (now.status == 0) {
                    nextDist = now.weight + next.weight / 2;
                } else {
                    nextDist = now.weight + next.weight * 2;
                }
                int nextState = 1 - now.status;

                if (wolfDist[next.node][nextState] > nextDist) {
                    wolfDist[next.node][nextState] = nextDist;
                    pq.add(new Edge(next.node, nextDist, nextState));
                }
            }
        }
    }
}
