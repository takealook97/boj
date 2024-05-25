import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, X, Y;
	static Point[] points;
	static HashSet<Edge>[] listSet;
	static boolean[] visited;
	static double answer = 0;

	static class Point {
		int y, x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	static class Edge implements Comparable<Edge> {
		int node;
		double weight;

		public Edge(int node, double weight) {
			this.node = node;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.weight, o.weight);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;

			Edge edge = (Edge)o;

			return this.node == edge.node;
		}

		@Override
		public int hashCode() {
			int result = node;
			result = 31 * result + (int)weight;
			return result;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		points = new Point[N + 1];
		visited = new boolean[N + 1];
		listSet = new HashSet[N + 1];
		for (int i = 1; i <= N; i++) {
			listSet[i] = new HashSet<>();
		}

		// 좌표 정보
		for (int idx = 1; idx <= N; idx++) {
			st = new StringTokenizer(br.readLine());
			X = Integer.parseInt(st.nextToken());
			Y = Integer.parseInt(st.nextToken());
			points[idx] = new Point(Y, X);
		}

		double weight;
		for (int from = 1; from <= N - 1; from++) {
			for (int to = from + 1; to <= N; to++) {
				weight = Math.sqrt(
					Math.pow(points[to].y - points[from].y, 2) + Math.pow(points[to].x - points[from].x, 2));
				listSet[from].add(new Edge(to, weight));
				listSet[to].add(new Edge(from, weight));
			}
		}

		// 연결된 통로
		int from, to;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			listSet[from].add(new Edge(to, 0));
			listSet[to].add(new Edge(from, 0));
		}

		findMin();

		System.out.printf("%.2f", (double)Math.round(answer * 100) / 100);
	}

	static void findMin() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(1, 0));

		while (!pq.isEmpty()) {
			Edge now = pq.poll();

			if (!visited[now.node]) {
				visited[now.node] = true;
				answer += now.weight;

				for (Edge next : listSet[now.node]) {
					if (!visited[next.node]) {
						pq.add(next);
					}
				}
			}
		}
	}
}
