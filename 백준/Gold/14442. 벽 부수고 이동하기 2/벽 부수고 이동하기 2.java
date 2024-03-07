import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static int[][] board;
	static boolean[][][] visited;
	static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
	static final int SPACE = 0, WALL = 1;

	static class Point implements Comparable<Point> {
		int y, x, dist, wallCnt;

		public Point(int y, int x, int dist, int wallCnt) {
			this.y = y;
			this.x = x;
			this.dist = dist;
			this.wallCnt = wallCnt;
		}

		@Override
		public int compareTo(Point o) {
			if (this.dist == o.dist) {
				return o.wallCnt - this.wallCnt;
			}

			return this.dist - o.dist;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		visited = new boolean[N][M][K + 1];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				board[i][j] = line.charAt(j) - '0';
			}
		}

		System.out.println(getDist());
	}

	static int getDist() {
		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.add(new Point(0, 0, 1, K));
		for (int i = 0; i < K; i++) {
			visited[0][0][i] = true;
		}

		while (!pq.isEmpty()) {
			Point now = pq.poll();
			if (now.y == N - 1 && now.x == M - 1) {
				return now.dist;
			}

			for (int i = 0; i < 4; i++) {
				int nextY = now.y + dy[i];
				int nextX = now.x + dx[i];
				if (isInRange(nextY, nextX)) {
					if (board[nextY][nextX] == SPACE) {// 빈칸
						if (!visited[nextY][nextX][now.wallCnt]) {// 방문 여부
							visited[nextY][nextX][now.wallCnt] = true;
							pq.add(new Point(nextY, nextX, now.dist + 1, now.wallCnt));
						}
					} else {// 벽이라면
						if (now.wallCnt > 0 && !visited[nextY][nextX][now.wallCnt]) {
							visited[nextY][nextX][now.wallCnt] = true;
							pq.add(new Point(nextY, nextX, now.dist + 1, now.wallCnt - 1));
						}
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
