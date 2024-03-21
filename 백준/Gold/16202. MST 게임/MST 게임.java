import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static ArrayList<Edge>[] listArr;
	static boolean[] visited;
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	static boolean flag = true;

	static class Edge implements Comparable<Edge> {
		int node, weight, src;
		boolean isPossible;

		public Edge(int node, int weight, int src, boolean isPossible) {
			this.node = node;
			this.weight = weight;
			this.src = src;
			this.isPossible = isPossible;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		listArr = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			listArr[i] = new ArrayList<>();
		}

		for (int weight = 1; weight <= M; weight++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			listArr[from].add(new Edge(to, weight, from, true));
			listArr[to].add(new Edge(from, weight, to, true));
		}

		StringBuilder sb = new StringBuilder();
		int temp = 1;
		while (K-- > 0) {
			int sum = getSum();
			if (isMst()) {
				sb.append(sum).append(" ");
			} else {
				flag = false;
				break;
			}
		}

		if (!flag) {
			K++;
			while (K-- > 0) {
				sb.append(0).append(" ");
			}
		}

		System.out.println(sb);
	}

	static int getSum() {
		int sum = 0;
		pq.clear();
		Arrays.fill(visited, false);
		for (int i = 1; i <= N; i++) {
			for (Edge edge : listArr[i]) {
				if (edge.isPossible) {
					pq.add(edge);
				}
			}
		}

		Edge start = null;
		while (!pq.isEmpty()) {
			Edge now = pq.poll();
			if (now.isPossible) {
				start = now;
				break;
			}
		}
		pq.clear();

		boolean check = false;
		sum += start.weight;
		pq.add(new Edge(start.node, 0, 0, true));
		pq.add(new Edge(start.src, 0, 0, true));
		while (!pq.isEmpty()) {
			Edge now = pq.poll();

			if (!visited[now.node] && now.isPossible) {
				if (!check && now.src != 0) {// rm head
					check = true;
					setImpossible(start);
				}

				visited[now.node] = true;
				sum += now.weight;

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

		return sum;
	}

	static void setImpossible(Edge edge) {
		edge.isPossible = false;

		int from = edge.node;
		int to = edge.src;

		for (Edge reverseEdge : listArr[from]) {
			if (reverseEdge.node == to) {
				reverseEdge.isPossible = false;
			}
		}
	}

	static boolean isMst() {
		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				return false;
			}
		}

		return true;
	}
}
