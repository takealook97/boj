import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static char[][] board;
	static int[][] adjCountBoard;
	static Point[][] distance;
	static boolean[][] visited;
	static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};

	static final char EMPTY = '.', START = 'S', END = 'F', GARBAGE = 'g';
	static final int INF = Integer.MAX_VALUE / 2;

	static class Point implements Comparable<Point> {
		int y, x, garbageCount, adjCount;

		public Point(int y, int x, int garbageCount, int adjCount) {
			this.y = y;
			this.x = x;
			this.garbageCount = garbageCount;
			this.adjCount = adjCount;
		}

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}

		@Override
		public int compareTo(Point o) {
			if (this.garbageCount == o.garbageCount) {
				return this.adjCount - o.adjCount;
			}
			return this.garbageCount - o.garbageCount;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new char[N][M];
		adjCountBoard = new int[N][M];
		distance = new Point[N][M];
		visited = new boolean[N][M];

		Point start = null, end = null;
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				board[i][j] = line.charAt(j);
				if (board[i][j] == START) {
					start = new Point(i, j);
				} else if (board[i][j] == END) {
					end = new Point(i, j);
				}
			}
		}

		setAdjCountBoard();

		search(start);

		System.out.println(distance[end.y][end.x].garbageCount + " " + distance[end.y][end.x].adjCount);
	}

	static void search(Point start) {
		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.add(start);

		initDistance();
		distance[start.y][start.x].garbageCount = 0;
		distance[start.y][start.x].adjCount = 0;

		while (!pq.isEmpty()) {
			Point now = pq.poll();

			if (!visited[now.y][now.x]) {
				visited[now.y][now.x] = true;

				for (int i = 0; i < 4; i++) {
					int nextY = now.y + dy[i];
					int nextX = now.x + dx[i];
					if (isInRange(nextY, nextX)) {
						int nextGarbageCount = now.garbageCount;
						int nextAdjCount = now.adjCount;

						if (board[nextY][nextX] == GARBAGE) {
							nextGarbageCount++;
						} else if (board[nextY][nextX] == EMPTY) {
							nextAdjCount += adjCountBoard[nextY][nextX];
						}

						Point next = new Point(nextY, nextX, nextGarbageCount, nextAdjCount);
						if (next.compareTo(distance[next.y][next.x]) < 0) {
							distance[nextY][nextX] = next;
							pq.add(next);
						}
					}
				}
			}
		}
	}

	static void initDistance() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				distance[i][j] = new Point(i, j, INF, INF);
			}
		}
	}

	static void setAdjCountBoard() {
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {
				int count = 0;
				for (int i = 0; i < 4; i++) {
					int nextY = y + dy[i];
					int nextX = x + dx[i];
					if (isInRange(nextY, nextX) && board[nextY][nextX] == GARBAGE) {
						count++;
						break;
					}
				}

				adjCountBoard[y][x] = count;
			}
		}
	}

	static boolean isInRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}
}
