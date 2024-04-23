import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] parent, countArr;
	static Queue<Edge> pq = new ArrayDeque<>();
	static ArrayList<Integer>[] listArr;

	static class Edge {
		int from, to;

		public Edge(int from, int to) {
			this.from = from;
			this.to = to;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		countArr = new int[N];
		listArr = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			listArr[i] = new ArrayList<>();
		}

		for (int from = 0; from < N - 1; from++) {
			String line = br.readLine();
			for (int to = from + 1; to < N; to++) {
				if (line.charAt(to) == 'Y') {
					listArr[from].add(to);
					pq.add(new Edge(from, to));
				}
			}
		}

		if (pq.size() < M) {
			System.out.println(-1);
			System.exit(0);
		}

		make();

		int count = 0;
		Queue<Edge> temp = new ArrayDeque<>();
		while (!pq.isEmpty()) {
			Edge edge = pq.poll();
			if (union(edge.from, edge.to)) {
				countArr[edge.from]++;
				countArr[edge.to]++;
				count++;
			} else {
				temp.add(edge);
			}
		}

		if (count != N - 1) {
			System.out.println(-1);
			return;
		}

		for (int i = N - 1; i < M; i++) {
			if (temp.isEmpty()) {
				System.out.println(-1);
				return;
			}
			Edge edge = temp.poll();
			countArr[edge.from]++;
			countArr[edge.to]++;
		}

		StringBuilder sb = new StringBuilder();
		for (int i : countArr) {
			sb.append(i).append(" ");
		}

		System.out.println(sb);
	}

	static void make() {
		parent = new int[N];
		for (int i = 0; i < N; i++) {
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
