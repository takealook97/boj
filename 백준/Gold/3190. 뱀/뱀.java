import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, K, L;
	static int[][] board;
	static int[] dy = {-1, 0, 1, 0}, dx = {0, 1, 0, -1}; // 상우하좌
	static Queue<Direction> order = new ArrayDeque<>();
	static final int APPLE = 2, BODY = 1, EMPTY = 0;

	static class Point {
		int y, x, dir, time;

		public Point(int y, int x, int dir, int time) {
			this.y = y;
			this.x = x;
			this.dir = dir;
			this.time = time;
		}
	}

	static class Direction {
		int time;
		boolean isRight;

		public Direction(int time, boolean isRight) {
			this.time = time;
			this.isRight = isRight;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		board = new int[N][N];
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken()) - 1;
			int x = Integer.parseInt(st.nextToken()) - 1;
			board[y][x] = APPLE;
		}

		L = Integer.parseInt(br.readLine());
		for (int i = 0; i < L; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			boolean isRight = st.nextToken().charAt(0) == 'D';
			order.add(new Direction(time, isRight));
		}

		System.out.println(getEndTime());
	}

	static int getEndTime() {
		int timeSum = 0;
		Direction dirInfo = order.peek();
		Deque<Point> snake = new ArrayDeque<>();
		snake.add(new Point(0, 0, 1, 0));
		board[0][0] = BODY;

		while (!snake.isEmpty()) {
			Point now = snake.peekFirst();

			int updatedDir = now.dir;
			if (!order.isEmpty() && now.time == order.peek().time) {
				dirInfo = order.poll();
				if (dirInfo.isRight) {
					updatedDir = (now.dir + 1) % 4;
				} else {
					updatedDir = (now.dir + 3) % 4;
				}
			}
			now.dir = updatedDir;

			// stretch
			int nextY = now.y + dy[now.dir];
			int nextX = now.x + dx[now.dir];

			if (!isPossible(nextY, nextX)) { // head check
				break;
			}

			Point next = new Point(nextY, nextX, now.dir, now.time + 1);
			snake.addFirst(next);

			// remove tail
			if (board[nextY][nextX] == EMPTY) {
				Point tail = snake.pollLast();
				board[tail.y][tail.x] = EMPTY;
			}
			// add head
			board[nextY][nextX] = BODY;

			timeSum++;
		}

		return timeSum + 1;
	}

	static boolean isPossible(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N && board[y][x] != BODY;
	}
}
