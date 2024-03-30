import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, T;
	static int[][] board;
	static boolean[][] visited, sVisited;
	static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
	static int answer = 0;

	static final int EMPTY = 0, WALL = 1, SWORD = 2;

	static class Point implements Comparable<Point> {
		int y, x, count;
		boolean hasSword;

		public Point(int y, int x, int count, boolean hasSword) {
			this.y = y;
			this.x = x;
			this.count = count;
			this.hasSword = hasSword;
		}

		@Override
		public int compareTo(Point o) {
			if (this.count == o.count) {
				if (this.hasSword) {
					return -1;
				} else {
					if (o.hasSword) {
						return 1;
					}
					return 0;
				}
			}
			return this.count - o.count;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		visited = new boolean[N][M];
		sVisited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		answer = getTime();

		System.out.println(answer == -1 ? "Fail" : answer);
	}

	static int getTime() {
		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.add(new Point(0, 0, 0, false));
		visited[0][0] = true;

		while (!pq.isEmpty()) {
			Point now = pq.poll();
			if (now.y == N - 1 && now.x == M - 1) {
				if (now.count <= T) {
					return now.count;
				}
				return -1;
			}

			if (now.count > T) {
				continue;
			}

			for (int i = 0; i < 4; i++) {
				int nextY = now.y + dy[i];
				int nextX = now.x + dx[i];
				if (!isInRange(nextY, nextX)) {
					continue;
				}
				if (board[nextY][nextX] != WALL || now.hasSword) {
					if (now.hasSword && !sVisited[nextY][nextX]) {
						sVisited[nextY][nextX] = true;
						pq.add(new Point(nextY, nextX, now.count + 1, true));
					} else if (!now.hasSword && board[nextY][nextX] != WALL && !visited[nextY][nextX]) {
						visited[nextY][nextX] = true;
						pq.add(new Point(nextY, nextX, now.count + 1, board[nextY][nextX] == SWORD));
					}
				}
			}
		}
		return -1;
	}

	static boolean isInRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}
}
