import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int T, N, M, K, a, b, c, start;
	static ArrayList<Edge>[] listArr;
	static boolean[] visited;
	static int[] distance, rooms;
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	static int answer, sum;

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
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		init();

		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		while (T-- > 0) {
			// input
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			// refresh
			for (int i = 1; i <= N; i++) {
				listArr[i].clear();
			}

			Arrays.fill(rooms, 0);
			sum = INF;

			// input
			for (int i = 1; i <= M; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				listArr[a].add(new Edge(b, c));
				listArr[b].add(new Edge(a, c));
			}

			K = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= K; i++) {
				start = Integer.parseInt(st.nextToken());
				getMin();
			}

			for (int i = 1; i <= N; i++) {
				if (rooms[i] < sum) {
					sum = rooms[i];
					answer = i;
				}
			}
			sb.append(answer).append("\n");
		}

		System.out.print(sb);
	}

	static void init() {
		listArr = new ArrayList[101];
		for (int i = 1; i <= 100; i++) {
			listArr[i] = new ArrayList<>();
		}

		visited = new boolean[101];
		distance = new int[101];
		rooms = new int[101];
	}

	static void getMin() {
		// refresh
		Arrays.fill(visited, false);
		Arrays.fill(distance, INF);
		pq.clear();

		distance[start] = 0;
		pq.add(new Edge(start, 0));

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

		for (int i = 1; i <= N; i++) {
			rooms[i] += distance[i];
		}
	}
}
