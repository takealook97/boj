import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int n, m;
	static int[][] board;
	static final int INF = Integer.MAX_VALUE / 2, SIZE = 26;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		board = new int[SIZE][SIZE];

		for (int i = 0; i < SIZE; i++) {
			Arrays.fill(board[i], INF);
			board[i][i] = 0;
		}

		String[] line;
		int from, to;

		n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			line = br.readLine().split(" ");
			from = line[0].charAt(0) - 'a';
			to = line[2].charAt(0) - 'a';
			board[from][to] = 1;
		}

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				for (int k = 0; k < SIZE; k++) {
					if (board[j][k] > board[j][i] + board[i][k]) {
						board[j][k] = board[j][i] + board[i][k];
					}
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		m = Integer.parseInt(br.readLine());
		for (int i = 0; i < m; i++) {
			line = br.readLine().split(" ");
			from = line[0].charAt(0) - 'a';
			to = line[2].charAt(0) - 'a';
			if (board[from][to] < INF) {
				sb.append("T");
			} else {
				sb.append("F");
			}
			sb.append("\n");
		}

		System.out.print(sb);
	}
}
