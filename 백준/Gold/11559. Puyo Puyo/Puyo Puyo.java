import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Queue;

public class Main {
	static int[][] board;
	static boolean[][] visited;
	static Queue<Point> queue = new ArrayDeque<>();
	static Deque<Point> deque = new ArrayDeque<>();
	static Queue<Integer> tempQueue = new ArrayDeque<>();
	static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
	static int answer = 0;

	static final int N = 12, M = 6, EMPTY = 0, RED = 1, GREEN = 2, BLUE = 3, YELLOW = 4, LIMIT = 4;

	static class Point {
		int y, x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		board = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				board[i][j] = getValue(line.charAt(j));
			}
		}

		while (true) {
			find();
			if (deque.isEmpty()) {
				break;
			}
			remove();
			setDown();
			answer++;
		}

		System.out.println(answer);
	}

	static int getValue(char c) {
		switch (c) {
			case '.':
				return 0;
			case 'R':
				return 1;
			case 'G':
				return 2;
			case 'B':
				return 3;
			case 'Y':
				return 4;
		}
		return -1;
	}

	static void find() {
		for (int i = 0; i < N; i++) {
			Arrays.fill(visited[i], false);
		}
		deque.clear();

		for (int i = N - 1; i >= 0; i--) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] != EMPTY && !visited[i][j]) {
					check(i, j);
				}
			}
		}
	}

	static void check(int startY, int startX) {
		queue.clear();
		visited[startY][startX] = true;
		Point start = new Point(startY, startX);
		queue.add(start);
		deque.add(start);
		int count = 1;
		while (!queue.isEmpty()) {
			Point now = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nextY = now.y + dy[i];
				int nextX = now.x + dx[i];
				if (isPossible(nextY, nextX) && board[startY][startX] == board[nextY][nextX]) {
					visited[nextY][nextX] = true;
					Point next = new Point(nextY, nextX);
					queue.add(next);
					deque.add(next);
					count++;
				}
			}
		}

		if (count < LIMIT) {
			while (count-- > 0) {
				Point point = deque.pollLast();
				visited[point.y][point.x] = false;
			}
		}
	}

	static boolean isPossible(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M && !visited[y][x];
	}

	static void remove() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (visited[i][j]) {
					board[i][j] = EMPTY;
				}
			}
		}
	}

	static void setDown() {
		int lim;
		for (int j = 0; j < M; j++) {
			tempQueue.clear();
			for (int i = N - 1; i >= 0; i--) {
				if (board[i][j] != EMPTY) {
					tempQueue.add(board[i][j]);
				}
			}

			lim = N - 1;
			while (!tempQueue.isEmpty()) {
				board[lim--][j] = tempQueue.poll();
			}
			if (lim > 0) {
				for (int i = lim; i >= 0; i--) {
					board[i][j] = EMPTY;
				}
			}
		}
	}
}
