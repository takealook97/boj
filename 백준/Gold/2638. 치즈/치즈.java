import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] board;
	static boolean[][] visit;
	static boolean[][] isAir;
	static boolean isOutSide;
	static ArrayList<Point> meltingList = new ArrayList<>();
	static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};

	static int answer = 0;

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
		visit = new boolean[N][M];
		isAir = new boolean[N][M];

		// set board
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// find
		while (!isClear()) {
			answer++;

			// set air block
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (board[i][j] == 0 && !visit[i][j]) {
						findAir(i, j);
					}
				}
			}

			// set melting list
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (board[i][j] == 1 && isMeltingSided(i, j)) {
						meltingList.add(new Point(i, j));
					}
				}
			}

			// melt
			melt();

			//reset
			resetConfig();
		}

		System.out.println(answer);
	}

	static void findAir(int y, int x) {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(y, x));
		boolean[][] temp = new boolean[N][M];
		visit[y][x] = true;
		temp[y][x] = true;

		isOutSide = false;

		while (!queue.isEmpty()) {
			Point now = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nextY = now.y + dy[i];
				int nextX = now.x + dx[i];
				if (isInRange(nextY, nextX)) {
					if (!visit[nextY][nextX] && board[nextY][nextX] == 0) {
						visit[nextY][nextX] = true;
						temp[nextY][nextX] = true;
						queue.add(new Point(nextY, nextX));
					}
				} else {
					isOutSide = true;
				}
			}
		}

		// set isAir array
		if (isOutSide) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (!isAir[i][j] && temp[i][j]) {
						isAir[i][j] = true;
					}
				}
			}
		}
	}

	static boolean isMeltingSided(int y, int x) {
		int count = 0;
		for (int i = 0; i < 4; i++) {
			int nextY = y + dy[i];
			int nextX = x + dx[i];
			if (isInRange(nextY, nextX) && isAir[nextY][nextX]) {
				count++;
			}
		}

		return count >= 2;
	}

	static void melt() {
		for (Point point : meltingList) {
			board[point.y][point.x] = 0;
		}
	}

	static boolean isInRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}

	static void resetConfig() {
		meltingList.clear();

		for (int i = 0; i < N; i++) {
			Arrays.fill(visit[i], false);
			Arrays.fill(isAir[i], false);
		}
	}

	static boolean isClear() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] == 1) {
					return false;
				}
			}
		}
		return true;
	}
}
