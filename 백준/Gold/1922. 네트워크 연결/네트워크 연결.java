import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static ArrayList<Edge>[] listArr;
	static boolean[] visited;
	static int answer = 0;

	static class Edge implements Comparable<Edge> {
		int node, dist;

		public Edge(int node, int dist) {
			this.node = node;
			this.dist = dist;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.dist, o.dist);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		listArr = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		for (int i = 0; i <= N; i++) {
			listArr[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			listArr[from].add(new Edge(to, weight));
			listArr[to].add(new Edge(from, weight));
		}

		getMinDist();

		System.out.println(answer);
	}

	static void getMinDist() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(1, 0));

		while (!pq.isEmpty()) {
			Edge now = pq.poll();
			if (!visited[now.node]) {
				visited[now.node] = true;
				answer += now.dist;

				if (listArr[now.node].isEmpty()) {
					continue;
				}

				for (Edge next : listArr[now.node]) {
					if (!visited[next.node]) {
						pq.add(next);
					}
				}
			}
		}
	}
}
