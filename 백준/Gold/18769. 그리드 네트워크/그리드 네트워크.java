import java.io.*;
import java.util.*;

public class Main {
	static int T, R, C, answer;
	static HashMap<Point, ArrayList<Edge>> map = new HashMap<>();
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	static boolean[][] visited;

	static class Point {
		int y, x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;

			Point point = (Point)o;

			if (y != point.y)
				return false;
			return x == point.x;
		}

		@Override
		public int hashCode() {
			int result = y;
			result = 31 * result + x;
			return result;
		}
	}

	static class Edge implements Comparable<Edge> {
		Point point;
		int weight;

		public Edge(Point point, int weight) {
			this.point = point;
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
		StringBuilder sb = new StringBuilder();

		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			init();

			for (int y = 0; y < R; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 0; x < C - 1; x++) {
					int weight = Integer.parseInt(st.nextToken());
					addEdge(new Point(y, x), new Edge(new Point(y, x + 1), weight));
					addEdge(new Point(y, x + 1), new Edge(new Point(y, x), weight));
				}
			}

			for (int y = 0; y < R - 1; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 0; x < C; x++) {
					int weight = Integer.parseInt(st.nextToken());
					addEdge(new Point(y, x), new Edge(new Point(y + 1, x), weight));
					addEdge(new Point(y + 1, x), new Edge(new Point(y, x), weight));
				}
			}

			prim();

			sb.append(answer).append("\n");
		}

		System.out.print(sb);
	}

	static void init() {
		answer = 0;
		map.clear();
		pq.clear();
		visited = new boolean[R][C];
	}

	static void addEdge(Point point, Edge edge) {
		if (!map.containsKey(point)) {
			map.put(point, new ArrayList<>());
		}

		map.get(point).add(edge);
	}

	static void prim() {
		pq.add(new Edge(new Point(0, 0), 0));

		while (!pq.isEmpty()) {
			Edge now = pq.poll();
			if (!visited[now.point.y][now.point.x]) {
				visited[now.point.y][now.point.x] = true;
				answer += now.weight;

				ArrayList<Edge> list = map.get(now.point);
				for (Edge next : list) {
					if (!visited[next.point.y][next.point.x]) {
						pq.add(next);
					}
				}
			}
		}
	}
}
