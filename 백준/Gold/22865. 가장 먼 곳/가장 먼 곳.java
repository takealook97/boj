import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, A, B, C, M, idx = 0;
	static ArrayList<Edge>[] listArr;
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	static int[] distance;
	static int[][] distances;
	static boolean[] visited;
	static int answer = 0, amount = 0;

	static final int INF = 987654321;

	static class Edge implements Comparable<Edge> {
		int node, weight;

		public Edge(int node, int weight) {
			this.node = node;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			if (this.weight == o.weight) {
				return this.node - o.node;
			}
			return this.weight - o.weight;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		listArr = new ArrayList[N + 1];
		distances = new int[3][N + 1];
		visited = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			listArr[i] = new ArrayList<>();
		}

		M = Integer.parseInt(br.readLine());
		int D, E, L;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			listArr[D].add(new Edge(E, L));
			listArr[E].add(new Edge(D, L));
		}

		getLongest(A);
		getLongest(B);
		getLongest(C);

		for (int i = 1; i <= N; i++) {
			int min = Math.min(distances[0][i], Math.min(distances[1][i], distances[2][i]));
			if (min > amount) {
				answer = i;
				amount = min;
			}
		}

		System.out.println(answer);
	}

	static void getLongest(int start) {
		distance = new int[N + 1];
		Arrays.fill(distance, INF);
		Arrays.fill(visited, false);
		pq.clear();

		distance[start] = 0;
		pq.add(new Edge(start, 0));

		while (!pq.isEmpty()) {
			Edge now = pq.poll();

			if (!visited[now.node]) {
				visited[now.node] = true;

				for (Edge next : listArr[now.node]) {
					if (!visited[next.node]) {
						if (distance[next.node] > next.weight + distance[now.node]) {
							distance[next.node] = next.weight + distance[now.node];
							pq.add(new Edge(next.node, distance[next.node]));
						}
					}
				}
			}
		}

		distances[idx++] = distance;
	}
}
