import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, K, answer = 0, max = 0, maxNode = 0;
	static int[] parent;
	static ArrayList<Edge>[] listArr;
	static boolean[] visited;

	static class Edge implements Comparable<Edge> {
		int from, to, weight;

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
		K = Integer.parseInt(st.nextToken());

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			pq.add(new Edge(from, to, weight));
		}

		make();

		while (!pq.isEmpty()) {
			Edge edge = pq.poll();
			if (union(edge.from, edge.to)) {
				answer += edge.weight;
				listArr[edge.from].add(new Edge(edge.from, edge.to, edge.weight));
				listArr[edge.to].add(new Edge(edge.to, edge.from, edge.weight));
			}
		}

		visited = new boolean[N];
		dfs(0, 0);
		visited = new boolean[N];
		max = 0;
		dfs(maxNode, 0);

		System.out.println(answer + "\n" + max);
	}

	static void make() {
		listArr = new ArrayList[N];
		parent = new int[N];

		for (int i = 0; i < N; i++) {
			listArr[i] = new ArrayList<>();
			parent[i] = i;
		}
	}

	static boolean union(int x, int y) {
		x = find(x);
		y = find(y);
		if (x != y) {
			parent[y] = x;
			return true;
		}
		return false;
	}

	static int find(int x) {
		if (parent[x] == x) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}

	static void dfs(int node, int distance) {
		visited[node] = true;
		if (distance > max) {
			max = distance;
			maxNode = node;
		}
		for (Edge edge : listArr[node]) {
			if (!visited[edge.to]) {
				dfs(edge.to, distance + edge.weight);
			}
		}
	}
}
