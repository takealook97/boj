import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int N, answer = 0;
	static int[][] board;
	static final int INF = Integer.MAX_VALUE / 2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(board[i], INF);
			board[i][i] = 0;
		}

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				if (line.charAt(j) == 'Y') {
					board[i][j] = 1;
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					if (board[j][k] > board[j][i] + board[i][k]) {
						board[j][k] = board[j][i] + board[i][k];
					}
				}
			}
		}

		for (int i = 0; i < N; i++) {
			int max = 0;
			for (int j = 0; j < N; j++) {
				if (0 < board[i][j] && board[i][j] <= 2) {
					max++;
				}
			}
			answer = Math.max(answer, max);
		}

		System.out.println(answer);
	}
}
