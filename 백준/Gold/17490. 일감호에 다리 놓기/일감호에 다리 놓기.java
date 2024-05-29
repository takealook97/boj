import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static long K;
	static int[] S, parent;
	static List<Edge> listArr;

	static class Edge implements Comparable<Edge> {
		int start, end;
		long weight;

		public Edge(int start, int end, long weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.weight, o.weight);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Long.parseLong(st.nextToken());

		S = new int[N + 1];
		parent = new int[N + 1];
		listArr = new ArrayList<>();

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			S[i] = Integer.parseInt(st.nextToken());
		}

		make();

		for (int i = 1; i <= N; i++) {
			listArr.add(new Edge(0, i, S[i]));
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int min = Math.min(from, to);
			int max = Math.max(from, to);
			if (min == 1 && max == N) {
				parent[1] = 1;
				continue;
			}
			parent[max] = max;
		}

		if (M <= 1) {
			System.out.println("YES");
			return;
		}

		Collections.sort(listArr);

		long result = 0;

		for (Edge edge : listArr) {
			if (union(edge.start, edge.end)) {
				result += edge.weight;
			}
		}

		if (result <= K) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}

	static void make() {
		for (int i = 1; i <= N; i++) {
			parent[i] = i - 1;
		}
		parent[1] = N;
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
