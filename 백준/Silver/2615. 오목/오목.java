import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] board;
	static boolean[][] visit;
	static final int BOARD_LENGTH = 19;
	static int[] dy = { 1, -1, 0, 1, -1, 1, 0, -1 };
	static int[] dx = { 0, 1, 1, 1, 0, -1, -1, -1 };// 남, 동북, 동, 동남 / 북, 서남, 서, 북서

	static class Point {
		int y, x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	static int answer = 0;

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("Test5.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		board = new int[BOARD_LENGTH][BOARD_LENGTH];
		visit = new boolean[BOARD_LENGTH][BOARD_LENGTH];
		for (int i = 0; i < BOARD_LENGTH; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < BOARD_LENGTH; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < BOARD_LENGTH; i++) {
			for (int j = 0; j < BOARD_LENGTH; j++) {
				if (!visit[i][j] && board[i][j] != 0) {
					for (int dir = 0; dir < 4; dir++) {
						int checkY = i + dy[dir + 4];
						int checkX = j + dx[dir + 4];
						if (0 <= checkY && checkY < BOARD_LENGTH && 0 <= checkX && checkX < BOARD_LENGTH) {
							if (board[i][j] != board[checkY][checkX]) {
								visit[i][j] = true;
								dfs(new Point(i, j), i, j, dir, 1);
								visit[i][j] = false;
							}
						} else {
							visit[i][j] = true;
							dfs(new Point(i, j), i, j, dir, 1);
							visit[i][j] = false;
						}

					}
				}
			}
		}
		System.out.println(0);

	}

	static void dfs(Point from, int y, int x, int dir, int depth) {
		if (depth == 5) {
			int nextY = y + dy[dir];
			int nextX = x + dx[dir];
			if (0 <= nextY && nextY < BOARD_LENGTH && 0 <= nextX & nextX < BOARD_LENGTH) {
				if (board[y][x] == board[nextY][nextX] && !visit[nextY][nextX]) {
					return;
				}
			}

			System.out.println(board[from.y][from.x]);
			System.out.println((from.y + 1) + " " + (from.x + 1));
			System.exit(0);
		}

		int nextY = y + dy[dir];
		int nextX = x + dx[dir];
		if (0 <= nextY && nextY < BOARD_LENGTH && 0 <= nextX & nextX < BOARD_LENGTH) {
			if (board[y][x] == board[nextY][nextX] && !visit[nextY][nextX]) {
				visit[nextY][nextX] = true;
				dfs(from, nextY, nextX, dir, depth + 1);
				visit[nextY][nextX] = false;
			}

		}
	}
}
