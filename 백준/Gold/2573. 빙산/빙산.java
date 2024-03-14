import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] board, temp, meltCount;
	static boolean[][] visited, visitedCopy;
	static Queue<Point> queue = new ArrayDeque<>();
	static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
	static boolean isDone = false;
	static int answer = 0;
	static final int EMPTY = 0;

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
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		temp = new int[N][M];
		meltCount = new int[N][M];
		visited = new boolean[N][M];
		visitedCopy = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			Arrays.fill(visited[i], true);
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] != EMPTY) {
					visited[i][j] = false;
				}
			}
		}

		while (!isSeperated()) {
			if (melt()) {
				answer++;
				continue;
			}
			break;
		}

		if (!isDone) {
			System.out.println(0);
		} else {
			System.out.println(answer);
		}
	}

	static boolean melt() {
		// init
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				meltCount[i][j] = 0;
				temp[i][j] = board[i][j];
			}
		}

		boolean isBoardChanged = false;
		// find
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {
				if (board[y][x] <= EMPTY) {
					continue;
				}
				int count = 0;
				for (int i = 0; i < 4; i++) {
					int nextY = y + dy[i];
					int nextX = x + dx[i];
					if (isInRange(nextY, nextX) && board[nextY][nextX] == EMPTY) {
						count++;
					}
				}
				meltCount[y][x] = count;
			}
		}

		// melt
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				board[i][j] -= meltCount[i][j];
				if (board[i][j] < EMPTY) {
					board[i][j] = EMPTY;
				}
				if (board[i][j] == EMPTY) {
					visited[i][j] = true;
				}

				if (!isBoardChanged && board[i][j] != temp[i][j]) {
					isBoardChanged = true;
				}
			}
		}

		return isBoardChanged;
	}

	static boolean isSeperated() {
		int startY = 0, startX = 0;
		boolean flag = false;
		// find start Point
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visitedCopy[i][j] = visited[i][j];
				if (!flag && !visited[i][j]) {
					flag = true;
					startY = i;
					startX = j;
				}
			}
		}

		queue.clear();
		queue.add(new Point(startY, startX));
		visitedCopy[startY][startX] = true;

		while (!queue.isEmpty()) {
			Point now = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nextY = now.y + dy[i];
				int nextX = now.x + dx[i];
				if (isInRange(nextY, nextX) && !visitedCopy[nextY][nextX]) {
					visitedCopy[nextY][nextX] = true;
					queue.add(new Point(nextY, nextX));
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visitedCopy[i][j]) {
					isDone = true;
					return true;
				}
			}
		}

		return false;
	}

	static boolean isInRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}
}
