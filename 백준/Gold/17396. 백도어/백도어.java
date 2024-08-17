import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static ArrayList<Edge>[] listArr;
	static boolean[] isVisible;
	static boolean[] visited;
	static long[] distance;
	static PriorityQueue<Edge> pq = new PriorityQueue<>();

	static final long INF = Long.MAX_VALUE / 2;

	static class Edge implements Comparable<Edge> {
		int node;
		long weight;

		public Edge(int node, long weight) {
			this.node = node;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.weight, o.weight);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		isVisible = new boolean[N];
		visited = new boolean[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int val = Integer.parseInt(st.nextToken());
			if (val == 1) {
				isVisible[i] = true;
			}
		}
		isVisible[N - 1] = false;

		listArr = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			listArr[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			listArr[a].add(new Edge(b, t));
			listArr[b].add(new Edge(a, t));
		}

		distance = new long[N];
		Arrays.fill(distance, INF);
		distance[0] = 0;

		pq.add(new Edge(0, 0));

		while (!pq.isEmpty()) {
			Edge now = pq.poll();
			if (!visited[now.node]) {
				visited[now.node] = true;

				for (Edge next : listArr[now.node]) {
					if (!visited[next.node] && !isVisible[next.node]) {
						if (distance[next.node] > distance[now.node] + next.weight) {
							distance[next.node] = distance[now.node] + next.weight;
							pq.add(new Edge(next.node, distance[next.node]));
						}
					}
				}
			}
		}

		if (distance[N - 1] >= INF) {
			System.out.println(-1);
		} else {
			System.out.println(distance[N - 1]);
		}
	}
}
