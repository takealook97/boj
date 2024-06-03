import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, D;
	static ArrayList<Edge>[] listArr;
	static int[] distance;
	static final int INF = Integer.MAX_VALUE;

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
		D = Integer.parseInt(st.nextToken());
		listArr = new ArrayList[10001];
		for (int i = 0; i <= 10000; i++) {
			listArr[i] = new ArrayList<>();
		}
		distance = new int[10001];
		Arrays.fill(distance, INF);

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			if (to <= D) {
				listArr[from].add(new Edge(to, weight));
			}
		}

		dijkstra();

		System.out.println(distance[D]);
	}

	static void dijkstra() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(0, 0));
		distance[0] = 0;

		while (!pq.isEmpty()) {
			Edge now = pq.poll();

			if (now.weight > distance[now.node]) {
				continue;
			}

			for (Edge edge : listArr[now.node]) {
				int nextNode = edge.node;
				int nextWeight = now.weight + edge.weight;

				if (nextWeight < distance[nextNode]) {
					distance[nextNode] = nextWeight;
					pq.add(new Edge(nextNode, nextWeight));
				}
			}

			if (now.node + 1 <= D && now.weight + 1 < distance[now.node + 1]) {
				distance[now.node + 1] = now.weight + 1;
				pq.add(new Edge(now.node + 1, now.weight + 1));
			}
		}
	}
}
