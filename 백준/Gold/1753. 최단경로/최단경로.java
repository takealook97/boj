import java.io.*;
import java.util.*;

public class Main {
	static int V, E, K;
	static ArrayList<Edge>[] listArr;
	static long[] distance;
	static boolean[] visit;
	static long INF = 60_000_000_000L;

	static class Edge implements Comparable<Edge> {
		int node;
		long weight;

		public Edge(int node, long weight) {
			this.node = node;
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
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		listArr = new ArrayList[V + 1];
		distance = new long[V + 1];
		visit = new boolean[V + 1];
		for (int i = 1; i <= V; i++) {
			listArr[i] = new ArrayList<>();
		}
		
		for (int i = 1; i <= E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			listArr[u].add(new Edge(v, w));
		}

		dk(K);

		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= V; i++){
			if(distance[i] == INF) sb.append("INF");
			else sb.append(distance[i]);
			sb.append("\n");
		}
		System.out.print(sb);
	}

	static void dk(int departure) {
		Arrays.fill(distance, INF);
		visit = new boolean[V + 1];
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(departure, 0));
		distance[departure] = 0;
		while (!pq.isEmpty()) {
			Edge now = pq.poll();
			if (!visit[now.node]) {
				visit[now.node] = true;
				for (Edge next : listArr[now.node]) {
					int nextD = next.node;
					long nextC = next.weight;
					if (distance[nextD] > now.weight + nextC) {
						distance[nextD] = now.weight + nextC;
						pq.add(new Edge(nextD, distance[nextD]));
					}
				}
			}
		}
	}
}
