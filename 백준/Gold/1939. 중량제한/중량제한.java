import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, A, B, C;
	static ArrayList<Edge>[] listArr;
	static int[] maxArr;
	static boolean[] visited;

	static final int INF = Integer.MAX_VALUE / 2;

	static class Edge implements Comparable<Edge> {
		int node, weight, min;

		public Edge(int node, int weight, int min) {
			this.node = node;
			this.weight = weight;
			this.min = min;
		}

		@Override
		public int compareTo(Edge o) {
			return o.min - this.min;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		listArr = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			listArr[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			if (listArr[A] == null) {
				listArr[A] = new ArrayList<>();
			}
			if (listArr[B] == null) {
				listArr[B] = new ArrayList<>();
			}

			if (!isExisted(A, B)) {
				listArr[A].add(new Edge(B, C, C));
			}
			if (!isExisted(B, A)) {
				listArr[B].add(new Edge(A, C, C));
			}
		}

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

		getMaxWeight(start, end);
		System.out.println(maxArr[end]);
	}

	static boolean isExisted(int from, int to) {
		boolean flag = false;
		for (Edge edge : listArr[from]) {
			if (edge.node == to) {
				if (edge.weight < C) {
					edge.weight = C;
					edge.min = C;
				}
				return true;
			}
		}

		return flag;
	}

	static void getMaxWeight(int start, int end) {
		visited = new boolean[N + 1];
		maxArr = new int[N + 1];
		Arrays.fill(maxArr, INF);
		maxArr[start] = 0;

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(start, 0, INF));

		while (!pq.isEmpty()) {
			Edge now = pq.poll();

			if (!visited[now.node]) {
				visited[now.node] = true;
				maxArr[now.node] = Math.min(maxArr[now.node], now.min);

				for (Edge next : listArr[now.node]) {
					if (!visited[next.node]) {
						int nextMin = Math.min(now.min, next.min);
						pq.add(new Edge(next.node, next.weight, nextMin));
					}
				}
			}
		}
	}
}
