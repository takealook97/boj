import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static char[] arr;
    static ArrayList<Edge>[] listArr;
    static boolean[] visited;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    static long answer = 0;

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
        listArr = new ArrayList[N + 1];
        arr = new char[N + 1];
        visited = new boolean[N + 1];
        for (int i = 0; i <= N; i++) {
            listArr[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = st.nextToken().charAt(0);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            if (arr[from] == arr[to]) {
                continue;
            }
            listArr[from].add(new Edge(to, weight));
            listArr[to].add(new Edge(from, weight));
        }

        pq.add(new Edge(1, 0));

        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            if (!visited[now.node]) {
                visited[now.node] = true;
                answer += now.weight;

                if (listArr[now.node].isEmpty()) {
                    continue;
                }

                for (Edge next : listArr[now.node]) {
                    if (!visited[next.node]) {
                        pq.add(next);
                    }
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                answer = -1;
                break;
            }
        }
        
        if (answer == 0) {
            answer = -1;
        }
        
        System.out.println(answer);
    }
}
