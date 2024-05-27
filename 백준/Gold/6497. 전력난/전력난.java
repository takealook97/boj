import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int m, n, x, y, z;
	static ArrayList<Edge>[] listArr;
	static boolean[] visited;
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	static int sum, amount;

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
		StringBuilder sb = new StringBuilder();

		while (true) {
			sum = 0;
			amount = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());

			if (m == 0) {
				break;
			}

			listArr = new ArrayList[m];
			visited = new boolean[m];
			for (int i = 0; i < m; i++) {
				listArr[i] = new ArrayList<>();
			}

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				z = Integer.parseInt(st.nextToken());
				sum += z;
				listArr[x].add(new Edge(y, z));
				listArr[y].add(new Edge(x, z));
			}

			getMin();

			sb.append(sum - amount).append("\n");
		}

		System.out.print(sb);
	}

	static void getMin() {
		pq.clear();
		pq.add(new Edge(x, 0));

		while (!pq.isEmpty()) {
			Edge now = pq.poll();

			if (!visited[now.node]) {
				visited[now.node] = true;
				amount += now.weight;

				for (Edge next : listArr[now.node]) {
					if (!visited[next.node]) {
						pq.add(next);
					}
				}
			}
		}
	}
}
