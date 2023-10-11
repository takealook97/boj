import java.io.*;
import java.util.*;

public class Main {
	static int[][] board;
	static ArrayList<Point> blankList = new ArrayList<>();
	static final int BLANK = 0, BOARD_LENGTH = 9;

	static class Point {
		int y, x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		board = new int[BOARD_LENGTH][BOARD_LENGTH];

		for (int i = 0; i < BOARD_LENGTH; i++) {
			String line = br.readLine();
			for (int j = 0; j < BOARD_LENGTH; j++) {
				board[i][j] = line.charAt(j) - '0';
				if (board[i][j] == BLANK) {
					blankList.add(new Point(i, j));
				}
			}
		}

		dfs(0);
	}

	static void dfs(int idx) {
		if (idx == blankList.size()) {
			printBoard();
			System.exit(0);
		}

		Point now = blankList.get(idx);
		int state = getState(now.y, now.x);
		if (state == 1) {
			return;
		}
		for (int num = 1; num <= 9; num++) {
			if ((state & (1 << num)) != 0) {
				board[now.y][now.x] = num;
				dfs(idx + 1);
				board[now.y][now.x] = 0;
			}
		}
	}

	static int getState(int y, int x) {
		int state = (1 << (BOARD_LENGTH + 1)) - 1;
		// 가로, 세로 체크
		for (int i = 0; i < BOARD_LENGTH; i++) {
			if (board[y][i] != 0 && (state & 1 << board[y][i]) != 0) {
				state ^= (1 << board[y][i]);
			}
			if (board[i][x] != 0 && (state & 1 << board[i][x]) != 0) {
				state ^= (1 << board[i][x]);
			}
		}

		// 사각 체크
		int startY = y / 3 * 3;
		int startX = x / 3 * 3;
		for (int i = startY; i < startY + 3; i++) {
			for (int j = startX; j < startX + 3; j++) {
				if (board[i][j] != 0 && (state & 1 << board[i][j]) != 0) {
					state ^= (1 << board[i][j]);
				}
			}
		}
		return state;
	}

	static void printBoard() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < BOARD_LENGTH; i++) {
			for (int j = 0; j < BOARD_LENGTH; j++) {
				sb.append(board[i][j]);
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
}
