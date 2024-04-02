import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, t;
	static ArrayList<Edge>[] listArr;
	static boolean[] visited;
	static long answer = 0;

	static class Edge implements Comparable<Edge> {
		int node, cost;

		public Edge(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());

		listArr = new ArrayList[N + 1];
		visited = new boolean[N + 1];

		for (int i = 0; i <= N; i++) {
			listArr[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			listArr[from].add(new Edge(to, cost));
			listArr[to].add(new Edge(from, cost));
		}

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(1, 0));

		int depth = -1;
		while (!pq.isEmpty()) {
			Edge now = pq.poll();
			if (!visited[now.node]) {
				visited[now.node] = true;

				answer += (now.cost + (long)depth * t);
				depth++;

				for (Edge next : listArr[now.node]) {
					if (!visited[next.node]) {
						pq.add(next);
					}
				}
			}
		}

		System.out.println(answer + t);
	}
}
