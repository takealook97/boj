import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int T, n, m, t, s, g, h, a, b, d;
	static BufferedReader br;
	static StringTokenizer st;
	static ArrayList<Edge>[] listArr;
	static PriorityQueue<Edge> pq;
	static int[] distS, distG, distH;
	static final int INF = 987654321;

	static class Edge implements Comparable<Edge> {
		int node, weight;

		Edge(int node, int weight) {
			this.node = node;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			g = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());

			listArr = new ArrayList[n + 1];
			for (int i = 1; i <= n; i++) {
				listArr[i] = new ArrayList<>();
			}

			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				d = Integer.parseInt(st.nextToken());

				listArr[a].add(new Edge(b, d));
				listArr[b].add(new Edge(a, d));
			}

			distS = getMin(s);
			distG = getMin(g);
			distH = getMin(h);

			int[] targets = new int[t];
			for (int i = 0; i < t; i++) {
				targets[i] = Integer.parseInt(br.readLine());
			}

			Arrays.sort(targets);
			for (int target : targets) {
				int directDist = distS[target];
				int g2h = distS[g] + distG[h] + distH[target];
				int h2g = distS[h] + distH[g] + distG[target];
				if (directDist == g2h || directDist == h2g) {
					sb.append(target).append(" ");
				}
			}
			sb.append("\n");
		}

		System.out.print(sb);
	}

	static int[] getMin(int start) {
		int[] distance = new int[n + 1];
		Arrays.fill(distance, INF);
		distance[start] = 0;
		pq = new PriorityQueue<>();
		pq.add(new Edge(start, 0));

		while (!pq.isEmpty()) {
			Edge now = pq.poll();

			if (distance[now.node] < now.weight) {
				continue;
			}
			for (Edge next : listArr[now.node]) {
				if (distance[next.node] > distance[now.node] + next.weight) {
					distance[next.node] = distance[now.node] + next.weight;
					pq.offer(new Edge(next.node, distance[next.node]));
				}
			}
		}

		return distance;
	}
}
