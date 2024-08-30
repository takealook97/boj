import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, start, end, limit;
	static ArrayList<Edge>[] listArr;
	static boolean[] visited;
	static int[] maxArr;

	static final int INF = Integer.MAX_VALUE / 2;

	static class Edge implements Comparable<Edge> {
		int node, weight, max;

		public Edge(int node, int weight, int max) {
			this.node = node;
			this.weight = weight;
			this.max = max;
		}

		@Override
		public int compareTo(Edge o) {
			return this.max - o.max;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		limit = Integer.parseInt(st.nextToken());

		listArr = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			listArr[i] = new ArrayList<>();
		}
		maxArr = new int[N + 1];
		visited = new boolean[N + 1];

		int from, to, weight;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			weight = Integer.parseInt(st.nextToken());
			listArr[from].add(new Edge(to, weight, weight));
			listArr[to].add(new Edge(from, weight, weight));
		}

		int answer = getMin();
		if (answer >= INF) {
			System.out.println(-1);
		} else {
			System.out.println(answer);
		}
	}

	static int getMin() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		Arrays.fill(maxArr, INF);
		maxArr[start] = 0;

		pq.add(new Edge(start, 0, 0));

		while (!pq.isEmpty()) {
			Edge now = pq.poll();

			if (!visited[now.node]) {
				visited[now.node] = true;

				for (Edge next : listArr[now.node]) {
					int nextWeight = now.weight + next.weight;
					if (!visited[next.node] && nextWeight <= limit) {
						int nextMax = Math.max(now.max, next.max);
						if (maxArr[next.node] > nextMax) {
							maxArr[next.node] = nextMax;
							pq.add(new Edge(next.node, nextWeight, nextMax));
						}
					}
				}
			}
		}

		return maxArr[end];
	}
}
