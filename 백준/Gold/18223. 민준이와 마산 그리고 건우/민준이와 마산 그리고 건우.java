import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int V, E, P, a, b, c;
    static ArrayList<Edge>[] listArr;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    static int[] distance;
    static boolean[] visited;
    static final int INF = 987654321;

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
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        listArr = new ArrayList[V + 1];
        distance = new int[V + 1];
        visited = new boolean[V + 1];
        for (int i = 1; i <= V; i++) {
            listArr[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            listArr[a].add(new Edge(b, c));
            listArr[b].add(new Edge(a, c));
        }

        searchPath(1);
        int startToDest = distance[P];
        int startToEnd = distance[V];
        searchPath(P);
        int destToEnd = distance[V];

        if (startToEnd == startToDest + destToEnd) {
            System.out.println("SAVE HIM");
        } else {
            System.out.println("GOOD BYE");
        }
    }

    static void searchPath(int start) {
        Arrays.fill(visited, false);
        Arrays.fill(distance, INF);
        pq.clear();

        pq.add(new Edge(start, 0));
        distance[start] = 0;

        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            if (!visited[now.node]) {
                visited[now.node] = true;

                for (Edge next : listArr[now.node]) {
                    if (!visited[next.node]) {
                        if (distance[next.node] > distance[now.node] + next.weight) {
                            distance[next.node] = distance[now.node] + next.weight;
                            pq.add(new Edge(next.node, distance[next.node]));
                        }
                    }
                }
            }
        }
    }
}
