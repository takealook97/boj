import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static ArrayList<Integer> srcArr = new ArrayList<>();
    static ArrayList<Edge>[] listArr;
    static boolean[] visited;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    static int answer = 0;

    static class Edge implements Comparable<Edge> {
        int node, weight;

        public Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        listArr = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        for (int i = 0; i <= N; i++) {
            listArr[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            srcArr.add(Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            listArr[from].add(new Edge(to, weight));
            listArr[to].add(new Edge(from, weight));
        }

        for (int idx : srcArr) {
            visited[idx] = true;
            pq.addAll(listArr[idx]);
        }

        while (!pq.isEmpty()) {
            Edge now = pq.poll();

            if (!visited[now.node]) {
                visited[now.node] = true;
                answer += now.weight;

                for (Edge next : listArr[now.node]) {
                    if (!visited[next.node]) {
                        pq.add(next);
                    }
                }
            }
        }

        System.out.println(answer);
    }
}
