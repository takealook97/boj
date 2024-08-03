import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
	static char[][][] board;
	static int[] dy = {-1, 0, 1, -1, 0, 1, -1, 0, 1}, dx = {-1, -1, -1, 0, 0, 0, 1, 1, 1};
	static int answer = 0;

	static final int N = 8, TRUE = 1, FALSE = 0;
	static final char EMPTY = '.', WALL = '#';

	static class Point {
		int time, y, x;

		public Point(int time, int y, int x) {
			this.time = time;
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		board = new char[N + 1][N][N];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				board[0][i][j] = line.charAt(j);
			}
		}

		setBoard();
		move();

		System.out.println(answer);
	}

	static void setBoard() {
		char[] emptyRow = new char[N];
		Arrays.fill(emptyRow, EMPTY);

		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < i; j++) {
				board[i][j] = Arrays.copyOf(emptyRow, N);
			}
			for (int j = i; j < N; j++) {
				board[i][j] = Arrays.copyOf(board[i - 1][j - 1], N);
			}
		}

	}

	static void move() {
		Queue<Point> queue = new ArrayDeque<>();
		queue.add(new Point(0, N - 1, 0));

		if (board[0][N - 1][0] == WALL) {
			return;
		}

		while (!queue.isEmpty()) {
			Point now = queue.poll();

			if (isPromising(now.time, now.y)) {
				answer = TRUE;
				return;
			}

			for (int i = 0; i < 9; i++) {
				int nextTime = now.time + 1;
				int nextY = now.y + dy[i];
				int nextX = now.x + dx[i];

				if (isInRange(nextY, nextX) && isMovable(nextTime, nextY, nextX)) {
					queue.add(new Point(nextTime, nextY, nextX));
				}
			}

		}
	}

	static boolean isPromising(int time, int row) {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < N; j++) {
				if (board[time][i][j] == WALL) {
					return false;
				}
			}
		}

		return true;
	}

	static boolean isInRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N;
	}

	static boolean isMovable(int time, int y, int x) {
		if (time > 8) {
			return false;
		}

		return board[time - 1][y][x] == EMPTY && board[time][y][x] == EMPTY;
	}
}
