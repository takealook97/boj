import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, X, Y, A, B, C;
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
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());

		listArr = new ArrayList[N];
		visited = new boolean[N];
		distance = new int[N];
		for (int i = 0; i < N; i++) {
			listArr[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			listArr[A].add(new Edge(B, C));
			listArr[B].add(new Edge(A, C));
		}

		getDist();

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			if (distance[i] >= INF) {
				System.out.println(-1);
				return;
			}
			distance[i] *= 2;
			pq.add(distance[i]);
		}

		int sum = 0;
		int answer = 1;
		while (!pq.isEmpty()) {
			int now = pq.poll();
			if (sum + now <= X) {
				sum += now;
			} else {
				answer++;
				sum = now;
				if (now > X) {
					System.out.println(-1);
					return;
				}
			}
		}

		System.out.println(answer);
	}

	static void getDist() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		Arrays.fill(distance, INF);

		distance[Y] = 0;
		pq.add(new Edge(Y, 0));

		while (!pq.isEmpty()) {
			Edge now = pq.poll();

			if (!visited[now.node]) {
				visited[now.node] = true;

				for (Edge next : listArr[now.node]) {
					if (!visited[next.node]) {
						if (distance[next.node] > distance[now.node] + next.weight) {
							distance[next.node] = distance[now.node] + next.weight;
							pq.add(new Edge(next.node, distance[next.node]));
						}
					}
				}
			}
		}
	}
}
