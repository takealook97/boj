import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int W, H;
	static int[][] board;
	static boolean[][] visited;
	static int[] dx1 = {0, 1, -1, 1, 0, 1}, dx2 = {-1, 0, -1, 1, -1, 0}, dy = {-1, -1, 0, 0, 1, 1};
	static List<Point> list = new ArrayList<>();
	static Queue<Point> queue = new ArrayDeque<>();
	static int answer = 0;

	static final int EMPTY = 0, BUILDING = 1;

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		board = new int[H + 1][W + 1];
		visited = new boolean[H + 1][W + 1];
		for (int i = 1; i <= H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= W; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int y = 1; y <= H; y++) {
			for (int x = 1; x <= W; x++) {
				if (!visited[y][x] && board[y][x] == EMPTY) {
					paintHoles(x, y);
				}
			}
		}

		// printBoard();

		for (int i = 1; i <= H; i++) {
			Arrays.fill(visited[i], false);
		}

		for (int y = 1; y <= H; y++) {
			for (int x = 1; x <= W; x++) {
				if (!visited[y][x] && board[y][x] == BUILDING) {
					count(x, y);
				}
			}
		}

		System.out.println(answer);
	}

	static void paintHoles(int x, int y) {
		list.clear();
		queue.clear();

		boolean isInside = true;
		queue.add(new Point(x, y));
		visited[y][x] = true;

		while (!queue.isEmpty()) {
			Point now = queue.poll();
			list.add(now);

			if (now.x == 1 || now.x == W || now.y == 1 || now.y == H) {
				isInside = false;
			}

			for (int i = 0; i < 6; i++) {
				int nextX = now.x + (now.y % 2 == 1 ? dx1[i] : dx2[i]);
				int nextY = now.y + dy[i];
				if (isInRange(nextX, nextY) && !visited[nextY][nextX] && board[nextY][nextX] == EMPTY) {
					visited[nextY][nextX] = true;
					queue.add(new Point(nextX, nextY));
				}
			}
		}

		if (isInside) {
			for (Point point : list) {
				board[point.y][point.x] = BUILDING;
			}
		}
	}

	static void count(int x, int y) {
		queue.clear();

		queue.add(new Point(x, y));
		visited[y][x] = true;

		while (!queue.isEmpty()) {
			Point now = queue.poll();

			for (int i = 0; i < 6; i++) {
				int nextX = now.x + (now.y % 2 == 1 ? dx1[i] : dx2[i]);
				int nextY = now.y + dy[i];
				if (isInRange(nextX, nextY) && !visited[nextY][nextX] && board[nextY][nextX] == BUILDING) {
					visited[nextY][nextX] = true;
					queue.add(new Point(nextX, nextY));
				} else {
					if (!isInRange(nextX, nextY) || board[nextY][nextX] == EMPTY) {
						answer++;
					}
				}
			}
		}
	}

	static boolean isInRange(int x, int y) {
		return 1 <= x && x <= W && 1 <= y && y <= H;
	}
}
