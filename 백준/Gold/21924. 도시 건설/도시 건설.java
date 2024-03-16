import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static ArrayList<Edge>[] listArr;
    static boolean[] visited;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    static long sum = 0, answer = 0;

    static class Edge implements Comparable<Edge> {
        int edge, weight;

        public Edge(int edge, int weight) {
            this.edge = edge;
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
        listArr = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        for (int i = 0; i <= N; i++) {
            listArr[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            listArr[from].add(new Edge(to, weight));
            listArr[to].add(new Edge(from, weight));
            sum += weight;
        }

        pq.add(new Edge(1, 0));
        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            if (!visited[now.edge]) {
                visited[now.edge] = true;
                answer += now.weight;

                if (listArr[now.edge].isEmpty()) {
                    continue;
                }

                for (Edge next : listArr[now.edge]) {
                    if (!visited[next.edge]) {
                        pq.add(next);
                    }
                }
            }
        }

        answer = sum - answer;

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                answer = -1;
                break;
            }
        }

        System.out.println(answer);
    }
}
