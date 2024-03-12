import java.util.*;

class Solution {
	static int N, M;
	static int[][] board;
	static boolean[][] visited;
	static int[] dy = {0, 1, 0, -1}, dx = {1, 0, -1, 0};
	static Point item;

	static class Point {
		int y, x, count;

		Point(int y, int x, int count) {
			this.y = y;
			this.x = x;
			this.count = count;
		}
	}

	public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
		N = M = 101;
		board = new int[N + 1][M + 1];
		visited = new boolean[N + 1][M + 1];
		for (int[] rect : rectangle) {
			for (int y = rect[1] * 2; y <= rect[3] * 2; y++) {
				for (int x = rect[0] * 2; x <= rect[2] * 2; x++) {
					if ((x == rect[0] * 2 || x == rect[2] * 2 || y == rect[1] * 2 || y == rect[3] * 2)
						&& board[y][x] != 2)
						board[y][x] = 1;
					else
						board[y][x] = 2;
				}
			}
		}
		item = new Point(itemY * 2, itemX * 2, 0);
		return bfs(new Point(characterY * 2, characterX * 2, 0)) / 2;
	}

	private int bfs(Point start) {
		Queue<Point> queue = new LinkedList<>();
		queue.add(start);
		visited[start.y][start.x] = true;
		while (!queue.isEmpty()) {
			Point current = queue.poll();
			if (current.y == item.y && current.x == item.x)
				return current.count;
			for (int i = 0; i < 4; i++) {
				int nextY = current.y + dy[i], nextX = current.x + dx[i];
				if (nextY >= 0 && nextY <= N && nextX >= 0 && nextX <= M && board[nextY][nextX] == 1
					&& !visited[nextY][nextX]) {
					visited[nextY][nextX] = true;
					queue.add(new Point(nextY, nextX, current.count + 1));
				}
			}
		}
		return -1;
	}
}
