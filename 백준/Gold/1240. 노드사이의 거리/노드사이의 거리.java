import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static ArrayList<Edge>[] listArr;
	static boolean[] visited;

	static class Edge {
		int node, weight;

		public Edge(int node, int weight) {
			this.node = node;
			this.weight = weight;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		listArr = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			listArr[i] = new ArrayList<>();
		}
		visited = new boolean[N + 1];

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			listArr[from].add(new Edge(to, weight));
			listArr[to].add(new Edge(from, weight));
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			Arrays.fill(visited, false);
			sb.append(search(u, v, 0)).append("\n");
		}

		System.out.print(sb);
	}

	static int search(int now, int end, int sum) {
		if (now == end) {
			return sum;
		}

		visited[now] = true;

		for (Edge next : listArr[now]) {
			if (!visited[next.node]) {
				int distance = search(next.node, end, sum + next.weight);
				if (distance != -1) {
					return distance;
				}
			}
		}

		return -1;
	}
}
