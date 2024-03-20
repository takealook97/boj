import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] costArr;
	static int[][] board;
	static boolean[] visited;
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	static long answer = 0;

	static class Edge implements Comparable<Edge> {
		int node, weight;

		public Edge(int node, int weight) {
			this.node = node;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		costArr = new int[N + 1];
		board = new int[N + 1][N + 1];
		visited = new boolean[N + 1];
		pq = new PriorityQueue<>();

		for (int i = 1; i <= N; i++) {
			costArr[i] = Integer.parseInt(br.readLine());
		}

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 1; i <= N; i++) {
			pq.add(new Edge(i, costArr[i]));
		}

		while (!pq.isEmpty()) {
			Edge now = pq.poll();
			if (!visited[now.node]) {
				visited[now.node] = true;
				answer += now.weight;

				for (int next = 1; next <= N; next++) {
					if (!visited[next] && next != now.node) {
						pq.offer(new Edge(next, board[now.node][next]));
					}
				}
			}
		}

		System.out.println(answer);
	}
}
