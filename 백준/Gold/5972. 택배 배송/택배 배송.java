import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static ArrayList<Edge>[] listArr;
	static boolean[] visited;
	static int[] distance;

	static final int INF = 987654321;

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
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		listArr = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			listArr[i] = new ArrayList<>();
		}

		int A, B, C;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken()) - 1;
			B = Integer.parseInt(st.nextToken()) - 1;
			C = Integer.parseInt(st.nextToken());
			listArr[A].add(new Edge(B, C));
			listArr[B].add(new Edge(A, C));
		}

		setMin();

		System.out.println(distance[N - 1]);
	}

	static void setMin() {
		visited = new boolean[N];
		distance = new int[N];
		Arrays.fill(distance, INF);

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(0, 0));
		distance[0] = 0;

		while (!pq.isEmpty()) {
			Edge now = pq.poll();
			if (!visited[now.node]) {
				visited[now.node] = true;
				for (Edge next : listArr[now.node]) {
					if (distance[next.node] > distance[now.node] + next.weight) {
						distance[next.node] = distance[now.node] + next.weight;
						pq.add(new Edge(next.node, distance[next.node]));
					}
				}
			}
		}
	}
}
