import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] parent;
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	static PriorityQueue<Edge> reversePq = new PriorityQueue<>(Collections.reverseOrder());
	static int goodCase = 0, badCase = 0;

	static class Edge implements Comparable<Edge> {
		int from, to;
		boolean isUpward;

		public Edge(int from, int to, boolean isUpward) {
			this.from = from;
			this.to = to;
			this.isUpward = isUpward;
		}

		@Override
		public int compareTo(Edge o) {
			if (this.isUpward == o.isUpward) {
				return 0;
			} else if (!this.isUpward) {
				return 1;
			}

			return -1;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parent = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		int sA = Integer.parseInt(st.nextToken());
		int sB = Integer.parseInt(st.nextToken());
		int sC = Integer.parseInt(st.nextToken());

		int A, B, C;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			Edge edge = new Edge(A, B, C == 0);
			pq.add(edge);
			reversePq.add(edge);
		}

		make();
		int count = 0;
		if (sC == 0) {
			goodCase++;
			badCase++;
		}
		while (!pq.isEmpty()) {
			Edge now = pq.poll();

			if (union(now.from, now.to)) {
				if (now.isUpward) {
					goodCase++;
				}
				count++;

				if (count == N - 1) {
					break;
				}
			}
		}
		goodCase *= goodCase;

		make();
		count = 0;
		while (!reversePq.isEmpty()) {
			Edge now = reversePq.poll();

			if (union(now.from, now.to)) {
				if (now.isUpward) {
					badCase++;
				}
				count++;

				if (count == N - 1) {
					break;
				}
			}
		}
		badCase *= badCase;

		System.out.println(Math.abs(goodCase - badCase));

	}

	static void make() {
		for (int i = 1; i <= N; i++) {
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
