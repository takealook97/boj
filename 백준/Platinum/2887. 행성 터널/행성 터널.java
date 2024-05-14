import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] parent;
	static Comparator<Point>[] comparators = new Comparator[3];
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	static ArrayList<Point> list = new ArrayList<>();
	static long answer = 0;

	static final int X = 0, Y = 1, Z = 2;

	static class Edge implements Comparable<Edge> {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}

	static class Point implements Comparator<Point> {
		int idx, x, y, z;

		public Point(int idx, int x, int y, int z) {
			this.idx = idx;
			this.x = x;
			this.y = y;
			this.z = z;
		}

		@Override
		public int compare(Point o1, Point o2) {
			return 0;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		int x, y, z;
		for (int idx = 0; idx < N; idx++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			z = Integer.parseInt(st.nextToken());
			list.add(new Point(idx, x, y, z));
		}

		setComparators();
		setDist();

		make();

		int count = 0;
		while (!pq.isEmpty()) {
			Edge now = pq.poll();
			if (union(now.from, now.to)) {
				answer += now.weight;
				count++;

				if (count == N - 1) {
					break;
				}
			}
		}

		System.out.println(answer);
	}

	static void setComparators() {
		comparators[X] = Comparator.comparingInt(o -> o.x);
		comparators[Y] = Comparator.comparingInt(o -> o.y);
		comparators[Z] = Comparator.comparingInt(o -> o.z);
	}

	static void setDist() {
		Point now, next;
		for (int i = 0; i < 3; i++) {
			list.sort(comparators[i]);
			for (int j = 0; j < N - 1; j++) {
				now = list.get(j);
				next = list.get(j + 1);
				if (i == X) {
					pq.add(new Edge(now.idx, next.idx, Math.abs(now.x - next.x)));
				} else if (i == Y) {
					pq.add(new Edge(now.idx, next.idx, Math.abs(now.y - next.y)));
				} else {// Z
					pq.add(new Edge(now.idx, next.idx, Math.abs(now.z - next.z)));
				}
			}
		}
	}

	static void make() {
		parent = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
	}

	static boolean union(int x, int y) {
		x = find(x);
		y = find(y);
		if (x != y) {
			parent[y] = x;
			return true;
		}
		return false;
	}

	static int find(int x) {
		if (parent[x] == x) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}
}
