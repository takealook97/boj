import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int T, R, C, answer;
	static int[] parent;
	static PriorityQueue<Edge> pq = new PriorityQueue<>();

	static class Edge implements Comparable<Edge> {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
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
					int fromIdx = y * C + x;
					int toIdx = y * C + x + 1;
					pq.add(new Edge(fromIdx, toIdx, weight));
				}
			}

			for (int y = 0; y < R - 1; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 0; x < C; x++) {
					int weight = Integer.parseInt(st.nextToken());
					int fromIdx = y * C + x;
					int toIdx = (y + 1) * C + x;
					pq.add(new Edge(fromIdx, toIdx, weight));
				}
			}

			getMin();

			sb.append(answer).append("\n");
		}

		System.out.print(sb);
	}

	static void init() {
		answer = 0;
		pq.clear();
		make();
	}

	static void getMin() {
		int count = 0;
		while (!pq.isEmpty()) {
			Edge edge = pq.poll();

			if (union(edge.from, edge.to)) {
				count++;
				answer += edge.weight;
				if (count == R * C - 1) {
					break;
				}
			}
		}
	}

	static void make() {
		parent = new int[R * C];
		for (int i = 0; i < R * C; i++) {
			parent[i] = i;
		}
	}

	static boolean union(int x, int y) {
		x = find(x);
		y = find(y);

		if (x != y) {
			parent[y] = x;
			return true;
		}

		return false;
	}

	static int find(int x) {
		if (parent[x] == x) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}
}
