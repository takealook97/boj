import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static ArrayList<Edge>[] listArr;
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	static boolean[] visited;
	static long answer = 0;

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
		N = Integer.parseInt(br.readLine());
		listArr = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		for (int i = 0; i <= N; i++) {
			listArr[i] = new ArrayList<>();
		}

		for (int from = 1; from <= N; from++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int to = 1; to <= N; to++) {
				int weight = Integer.parseInt(st.nextToken());
				listArr[from].add(new Edge(to, weight));
			}
		}

		pq.add(new Edge(1, 0));

		while (!pq.isEmpty()) {
			Edge now = pq.poll();
			if (!visited[now.node]) {
				visited[now.node] = true;
				answer += now.weight;
				
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

		System.out.println(answer);
	}
}
