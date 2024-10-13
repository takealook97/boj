import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] parent, size;
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	static long sum = 0, answer = 0;
	
	static final int MOD = 1_000_000_000;

	static class Edge implements Comparable<Edge> {
		int from, to;
		long weight;

		public Edge(int from, int to, long weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Long.compare(o.weight, this.weight);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			long weight = Integer.parseInt(st.nextToken());
			sum += weight;
			pq.add(new Edge(from, to, weight));
		}

		make();

		while (!pq.isEmpty()) {
			Edge edge = pq.poll();
			answer = (answer + sum * union(edge.from, edge.to)) % MOD;
			sum -= edge.weight;
		}

		System.out.println(answer);
	}

	static void make() {
		parent = new int[N + 1];
		size = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
			size[i] = 1;
		}
	}

	static long union(int x, int y) {
		x = find(x);
		y = find(y);

		if (x != y) {
			long result = (long)size[x] * size[y];
			if (size[x] > size[y]) {
				parent[y] = x;
				size[x] += size[y];
			} else {
				parent[x] = y;
				size[y] += size[x];
			}
			return result;
		}
		return 0;
	}

	static int find(int x) {
		if (parent[x] == x)
			return x;
		return parent[x] = find(parent[x]);
	}
}
