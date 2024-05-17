import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, a, b, t;
	static ArrayList<Edge>[] listArr;
	static int[] distance;
	static int[] parent;
	static final int INF = Integer.MAX_VALUE / 2;

	static class Edge implements Comparable<Edge> {
		int to, weight;
		int from;

		public Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
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
		for (int i = 1; i <= N; i++) {
			listArr[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());
			listArr[a].add(new Edge(b, t));
			listArr[b].add(new Edge(a, t));
		}

		int shortest = dijkstra(1, N);
		if (shortest == INF) {
			System.out.println(-1);
			return;
		}

		ArrayList<Edge> pathEdges = new ArrayList<>();
		int now = N;
		while (now != 1) {
			int parentNode = parent[now];
			for (Edge edge : listArr[parentNode]) {
				if (edge.to == now && distance[now] - distance[parentNode] == edge.weight) {
					pathEdges.add(new Edge(parentNode, now, edge.weight));
					break;
				}
			}
			now = parentNode;
		}

		int answer = 0;
		for (Edge edge : pathEdges) {
			int from = edge.from;
			int to = edge.to;

			listArr[from].removeIf(e -> e.to == to && e.weight == edge.weight);
			listArr[to].removeIf(e -> e.to == from && e.weight == edge.weight);

			int newShortestTime = dijkstra(1, N);
			if (newShortestTime == INF) {
				answer = -1;
				break;
			}

			answer = Math.max(answer, newShortestTime - shortest);

			listArr[from].add(new Edge(to, edge.weight));
			listArr[to].add(new Edge(from, edge.weight));
		}

		System.out.println(answer);
	}

	static int dijkstra(int start, int end) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(start, 0));
		distance = new int[N + 1];
		Arrays.fill(distance, INF);
		distance[start] = 0;

		parent = new int[N + 1];
		Arrays.fill(parent, -1);

		boolean[] visited = new boolean[N + 1];

		while (!pq.isEmpty()) {
			Edge now = pq.poll();

			if (visited[now.to])
				continue;
			visited[now.to] = true;

			for (Edge next : listArr[now.to]) {
				if (!visited[next.to] && distance[next.to] > distance[now.to] + next.weight) {
					distance[next.to] = distance[now.to] + next.weight;
					pq.add(new Edge(next.to, distance[next.to]));
					parent[next.to] = now.to;
				}
			}
		}

		return distance[end];
	}
}
