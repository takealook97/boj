import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, s, e, from, to, weight;
	static ArrayList<Edge>[] listArr;
	static boolean[] visited;
	static int answer = 0;

	static class Edge implements Comparable<Edge> {
		int node, weight;

		public Edge(int node, int weight) {
			this.node = node;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return o.weight - this.weight;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());

		listArr = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			listArr[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			weight = Integer.parseInt(st.nextToken());
			listArr[from].add(new Edge(to, weight));
			listArr[to].add(new Edge(from, weight));
		}

		getMax();

		System.out.println(answer);
	}

	static void getMax() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(s, Integer.MAX_VALUE));

		while (!pq.isEmpty()) {
			Edge now = pq.poll();

			if (!visited[now.node]) {
				visited[now.node] = true;
				if (now.node == e) {
					answer = now.weight;
					break;
				}

				for (Edge next : listArr[now.node]) {
					if (!visited[next.node]) {
						pq.add(new Edge(next.node, Math.min(now.weight, next.weight)));
					}
				}
			}
		}
	}
}
