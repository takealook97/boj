import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, A, B, C, K = 0;
	static ArrayList<Edge>[] listArr;
	static boolean[] visited;
	static int[] distance;
	static int[] answer;

	static final int INF = Integer.MAX_VALUE / 2;

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

		@Override
		public String toString() {
			return "Edge{" +
				"node=" + node +
				", weight=" + weight +
				'}';
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		listArr = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		distance = new int[N + 1];
		answer = new int[N + 1];
		Arrays.fill(distance, INF);
		Arrays.fill(answer, -1);

		for (int i = 1; i <= N; i++) {
			listArr[i] = new ArrayList<>();
		}

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			listArr[A].add(new Edge(B, C));
			listArr[B].add(new Edge(A, C));
		}

		getRoute();

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			if (answer[i] >= 1) {
				K++;
			}
		}
		sb.append(K).append("\n");
		for (int i = 1; i <= N; i++) {
			if (answer[i] >= 1) {
				sb.append(i).append(" ").append(answer[i]).append("\n");
			}
		}

		System.out.print(sb);
	}

	static void getRoute() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(1, 0));
		distance[1] = 0;

		while (!pq.isEmpty()) {
			Edge now = pq.poll();

			if (!visited[now.node]) {
				visited[now.node] = true;

				for (Edge next : listArr[now.node]) {
					if (!visited[next.node]) {
						if (distance[next.node] > distance[now.node] + next.weight) {
							distance[next.node] = distance[now.node] + next.weight;
							pq.add(new Edge(next.node, distance[next.node]));
							answer[next.node] = now.node;
						}
					}
				}
			}
		}
	}
}
