import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
	static int N = 3;
	static String line;
	static Set<String> set = new HashSet<>();
	static char[][] board = new char[N][N];
	static int[] dy = {-1, 1, 0, 0, -1, 1, -1, 1},
		dx = {0, 0, -1, 1, -1, 1, 1, -1}; // 상하 좌우 좌상우하 좌하우상

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// init
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				board[i][j] = '.';
			}
		}

		dfs(true);

		while (true) {
			line = br.readLine();
			if (line.equals("end")) {
				break;
			}

			sb.append(set.contains(line) ? "valid" : "invalid").append("\n");
		}

		System.out.print(sb);
	}

	static void dfs(boolean isPrevO) {
		if (isFinished()) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sb.append(board[i][j]);
				}
			}
			set.add(sb.toString());
			return;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] == '.') {
					if (isPrevO) {
						board[i][j] = 'X';
					} else {
						board[i][j] = 'O';
					}
					dfs(!isPrevO);
					board[i][j] = '.';
				}
			}
		}
	}

	static boolean isFinished() {
		// check center
		if (board[1][1] != '.') {
			int center = board[1][1];
			for (int i = 0; i < 4; i++) {
				int a = board[1 + dy[2 * i]][1 + dx[2 * i]];
				int b = board[1 + dy[2 * i + 1]][1 + dx[2 * i + 1]];
				if (center == a && center == b) {
					return true;
				}
			}
		}

		// check col & row
		for (int i = 0; i < N; i++) {
			// check col
			if (board[0][i] != '.') {
				if (board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
					return true;
				}
			}

			// check row
			if (board[i][0] != '.') {
				if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
					return true;
				}
			}
		}

		// check full
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] == '.') {
					return false;
				}
			}
		}

		return true;
	}
}
