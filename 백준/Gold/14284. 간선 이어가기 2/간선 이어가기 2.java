import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int n, m, a, b, c, s, t;
	static ArrayList<Edge>[] listArr;
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
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		listArr = new ArrayList[n + 1];
		for (int i = 0; i <= n; i++) {
			listArr[i] = new ArrayList<>();
		}

		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			listArr[a].add(new Edge(b, c));
			listArr[b].add(new Edge(a, c));
		}

		st = new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());

		getMin();

		System.out.println(distance[t]);
	}

	static void getMin() {
		distance = new int[n + 1];
		visited = new boolean[n + 1];
		Arrays.fill(distance, INF);
		distance[s] = 0;

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(s, 0));

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
