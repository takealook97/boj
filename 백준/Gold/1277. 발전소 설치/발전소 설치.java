import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, W;
	static double M;
	static int[][] points;
	static ArrayList<Edge>[] listArr;
	static double[] distance;
	static boolean[] visited;
	static final double INF = 987645321.0;

	static class Edge implements Comparable<Edge> {
		int node;
		double weight;

		public Edge(int node, double weight) {
			this.node = node;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.weight, o.weight);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		M = Double.parseDouble(br.readLine());
		points = new int[N][2];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			points[i][0] = Integer.parseInt(st.nextToken());
			points[i][1] = Integer.parseInt(st.nextToken());
		}

		init();
		for (int i = 0; i < W; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			listArr[start].add(new Edge(end, 0.0));
			listArr[end].add(new Edge(start, 0.0));
		}

		double result = getMin(0);
		System.out.println((int)(result * 1000));
	}

	static void init() {
		listArr = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			listArr[i] = new ArrayList<>();
			for (int j = 0; j < i; j++) {
				double dSquare = Math.pow(points[i][0] - points[j][0], 2) + Math.pow(points[i][1] - points[j][1], 2);
				if (dSquare <= M * M) {
					double dist = Math.sqrt(dSquare);
					listArr[i].add(new Edge(j, dist));
					listArr[j].add(new Edge(i, dist));
				}
			}
		}
	}

	static double getMin(int start) {
		distance = new double[N];
		visited = new boolean[N];
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		Arrays.fill(distance, INF);
		distance[start] = 0;
		pq.add(new Edge(start, 0));

		while (!pq.isEmpty()) {
			Edge now = pq.poll();

			if (visited[now.node])
				continue;
			visited[now.node] = true;

			for (Edge next : listArr[now.node]) {
				if (distance[next.node] > distance[now.node] + next.weight) {
					distance[next.node] = distance[now.node] + next.weight;
					pq.add(new Edge(next.node, distance[next.node]));
				}
			}
		}

		return distance[N - 1];
	}
}
