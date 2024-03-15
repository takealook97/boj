import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] parent;
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	static int answer = 0;

	static class Edge implements Comparable<Edge> {
		int from, to, dist;

		public Edge(int from, int to, int dist) {
			this.from = from;
			this.to = to;
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

		parent = new int[N + 1];

		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			pq.add(new Edge(from, to, dist));
		}

		make();

		int count = 0;
		while (!pq.isEmpty()) {
			Edge edge = pq.poll();
			if (union(edge.from, edge.to)) {
				answer += edge.dist;
				count++;
				if (count == N - 1) {
					break;
				}
			}
		}

		System.out.println(answer);
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
