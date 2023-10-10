import java.io.*;
import java.util.*;

public class Main {
	static int R, C, T;
	static int[][] board;
	static int[] dy = { -1, 1, 0, 0 }, dx = { 0, 0, -1, 1 };
	static Point upside, downside;
	static final int PURIFIER = -1;

	static class Point {
		int y, x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		board = new int[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j] == PURIFIER) {
					if (upside == null) {
						upside = new Point(i, j);
					} else if (upside != null && downside == null) {
						downside = new Point(i, j);
					}
				}
			}
		}

		while (T-- > 0) {
			spread();
			purify();
		}

		System.out.println(getDust());
	}

	static void spread() {
		int[][] plus = new int[R][C];
		Queue<Point> queue = new LinkedList<>();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (board[i][j] > 0) {
					queue.add(new Point(i, j));
				}
			}
		}

		while (!queue.isEmpty()) {
			Point now = queue.poll();
			int part = board[now.y][now.x] / 5;
			int addCount = 0;
			for (int i = 0; i < 4; i++) {
				int nextY = now.y + dy[i];
				int nextX = now.x + dx[i];
				if (isMovable(nextY, nextX)) {
					plus[nextY][nextX] += part;
					addCount++;
				}
			}
			board[now.y][now.x] -= (part * addCount);
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				board[i][j] += plus[i][j];
			}
		}
	}

	static void purify() {
		for (int i = upside.y - 1; i > 0; i--) {
			board[i][0] = board[i - 1][0];
		}
		for (int i = 0; i < C - 1; i++) {
			board[0][i] = board[0][i + 1];
		}
		for (int i = 0; i < upside.y; i++) {
			board[i][C - 1] = board[i + 1][C - 1];
		}
		for (int i = C - 1; i > 1; i--) {
			board[upside.y][i] = board[upside.y][i - 1];
		}
		board[upside.y][1] = 0;

		for (int i = downside.y + 1; i < R - 1; i++) {
			board[i][0] = board[i + 1][0];
		}
		for (int i = 0; i < C - 1; i++) {
			board[R - 1][i] = board[R - 1][i + 1];
		}
		for (int i = R - 1; i > downside.y; i--) {
			board[i][C - 1] = board[i - 1][C - 1];
		}
		for (int i = C - 1; i > 1; i--) {
			board[downside.y][i] = board[downside.y][i - 1];
		}
		board[downside.y][1] = 0;
	}

	static boolean isMovable(int y, int x) {
		return 0 <= y && y < R && 0 <= x && x < C && board[y][x] != PURIFIER;
	}

	static int getDust() {
		int result = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (board[i][j] != PURIFIER) {
					result += board[i][j];
				}
			}
		}
		return result;
	}
}
