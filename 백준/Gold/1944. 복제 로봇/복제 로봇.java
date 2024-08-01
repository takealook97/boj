import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, idx = 0;
	static char[][] board;
	static boolean[] visited;
	static ArrayList<Integer> robotNode = new ArrayList<>();
	static Map<Integer, Point> keyMap = new HashMap<>();
	static Map<Point, Integer> valMap = new HashMap<>();
	static ArrayList<Edge>[] listArr;
	static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
	static PriorityQueue<Edge> pq = new PriorityQueue<>();

	static final char EMPTY = '0', WALL = '1', ROBOT = 'S', KEY = 'K';

	static class Point {
		int y, x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;

			Point point = (Point)o;

			if (y != point.y)
				return false;
			return x == point.x;
		}

		@Override
		public int hashCode() {
			return Objects.hash(this.y, this.x);
		}
	}

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

		board = new char[N][N];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				board[i][j] = line.charAt(j);
				if (board[i][j] == ROBOT || board[i][j] == KEY) {
					Point point = new Point(i, j);
					keyMap.put(idx, point);
					valMap.put(point, idx);
					if (board[i][j] == ROBOT) {
						robotNode.add(idx);
					}
					idx++;
				}
			}
		}

		setListArr();

		int result = find();
		System.out.println(result);
	}

	static void setListArr() {
		listArr = new ArrayList[idx];
		for (int i = 0; i < idx; i++) {
			listArr[i] = new ArrayList<>();
		}

		for (int nowNode : keyMap.keySet()) {
			Point now = keyMap.get(nowNode);
			bfs(nowNode, now);
		}
	}

	static void bfs(int startNode, Point start) {
		Queue<Point> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		queue.add(start);
		visited[start.y][start.x] = true;
		int distance = 0;

		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Point now = queue.poll();
				if (valMap.containsKey(now) && valMap.get(now) != startNode) {
					int targetNode = valMap.get(now);
					listArr[startNode].add(new Edge(targetNode, distance));
				}

				for (int d = 0; d < 4; d++) {
					int nextY = now.y + dy[d];
					int nextX = now.x + dx[d];
					if (nextY >= 0 && nextY < N && nextX >= 0 && nextX < N && !visited[nextY][nextX]
						&& board[nextY][nextX] != WALL) {
						visited[nextY][nextX] = true;
						queue.add(new Point(nextY, nextX));
					}
				}
			}
			distance++;
		}
	}

	static int find() {
		visited = new boolean[idx];
		for (int node : robotNode) {
			pq.add(new Edge(node, 0));
		}

		int sum = 0;
		int count = 0;
		while (!pq.isEmpty()) {
			Edge now = pq.poll();
			if (!visited[now.node]) {
				visited[now.node] = true;
				sum += now.distance;
				count++;

				for (Edge next : listArr[now.node]) {
					if (!visited[next.node]) {
						pq.add(next);
					}
				}
			}
		}
		return count == idx ? sum : -1;
	}
}
