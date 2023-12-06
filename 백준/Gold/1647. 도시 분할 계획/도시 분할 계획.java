import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static ArrayList<Edge>[] listArr;
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	static boolean[] visit;
	static int sum = 0;
	static int maxDist = 0;

	static class Edge implements Comparable<Edge> {
		int node, distance;

		public Edge(int node, int distance) {
			this.node = node;
			this.distance = distance;
		}

		@Override
		public int compareTo(Edge o) {
			return this.distance - o.distance;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		listArr = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			listArr[i] = new ArrayList<>();
		}
		visit = new boolean[N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int distance = Integer.parseInt(st.nextToken());
			listArr[from].add(new Edge(to, distance));
			listArr[to].add(new Edge(from, distance));
		}

		setTree();

		System.out.println(sum - maxDist);
	}

	static void setTree() {
		pq.add(new Edge(1, 0));
		while (!pq.isEmpty()) {
			Edge now = pq.poll();
			if (!visit[now.node]) {
				visit[now.node] = true;
				maxDist = Math.max(maxDist, now.distance);
				sum += now.distance;
				if (!listArr[now.node].isEmpty()) {
					for (Edge next : listArr[now.node]) {
						if (!visit[next.node]) {
							pq.add(next);
						}
					}
				}
			}
		}
	}
}
