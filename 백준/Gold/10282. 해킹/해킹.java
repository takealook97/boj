import java.io.*;
import java.util.*;

public class Main {
	static int T, n, d, c;
	static ArrayList<Edge>[] listArr;
	static int[] timeArr;
	static boolean[] visited;
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	static final int INF = 1987654321;

	static class Edge implements Comparable<Edge> {
		int node, sec;

		public Edge(int node, int sec) {
			this.node = node;
			this.sec = sec;
		}

		@Override
		public int compareTo(Edge o) {
			return this.sec - o.sec;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();

		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());

			listArr = new ArrayList[n + 1];
			timeArr = new int[n + 1];
			visited = new boolean[n + 1];

			for (int i = 0; i <= n; i++) {
				listArr[i] = new ArrayList<>();
			}

			for (int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine());
				int to = Integer.parseInt(st.nextToken());
				int from = Integer.parseInt(st.nextToken());
				int sec = Integer.parseInt(st.nextToken());
				listArr[from].add(new Edge(to, sec));
			}

			int time = getTime();
			int count = getCount();

			sb.append(count).append(" ").append(time).append("\n");
		}

		System.out.print(sb);
	}

	static int getTime() {
		pq.clear();
		Arrays.fill(timeArr, INF);
		pq.add(new Edge(c, 0));
		timeArr[c] = 0;// self

		while (!pq.isEmpty()) {
			Edge now = pq.poll();

			if (!visited[now.node]) {
				visited[now.node] = true;

				for (Edge next : listArr[now.node]) {
					if (!visited[next.node]) {
						if (timeArr[next.node] > timeArr[now.node] + next.sec) {
							timeArr[next.node] = timeArr[now.node] + next.sec;
							pq.add(new Edge(next.node, timeArr[next.node]));
						}
					}
				}
			}
		}

		int maxTime = 0;
		for (int i = 1; i <= n; i++) {
			if (timeArr[i] < INF) {
				maxTime = Math.max(maxTime, timeArr[i]);
			}
		}

		return maxTime;
	}

	static int getCount() {
		int count = 0;
		for (int i = 1; i <= n; i++) {
			if (visited[i]) {
				count++;
			}
		}

		return count;
	}
}
