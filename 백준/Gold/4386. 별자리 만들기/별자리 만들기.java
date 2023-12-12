import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static Star[] stars;
	static ArrayList<Edge>[] listArr;
	static boolean[] visit;
	static double answer = 0;

	static class Star {
		double x, y;

		public Star(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Edge implements Comparable<Edge> {
		int node;
		double distance;

		public Edge(int node, double distance) {
			this.node = node;
			this.distance = distance;
		}

		public int compareTo(Edge o) {
			if (this.distance > o.distance) {
				return 1;
			} else if (this.distance < o.distance) {
				return -1;
			}
			return 0;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		stars = new Star[n];
		visit = new boolean[n];
		listArr = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			listArr[i] = new ArrayList<>();
		}

		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			stars[i] = new Star(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
		}

		for (int from = 0; from < n - 1; from++) {
			for (int to = from + 1; to < n; to++) {
				double dist = Math.sqrt(
					Math.pow(stars[from].y - stars[to].y, 2) + Math.pow(stars[from].x - stars[to].x, 2));
				listArr[from].add(new Edge(to, dist));
				listArr[to].add(new Edge(from, dist));
			}
		}

		setTree();

		System.out.println(Math.round(answer * 100) / 100.0);
	}

	static void setTree() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(0, 0));
		while (!pq.isEmpty()) {
			Edge now = pq.poll();
			if (!visit[now.node]) {
				visit[now.node] = true;
				answer += now.distance;
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
