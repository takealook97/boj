import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[][] board;
	static int[][][] dp;
	static final int W = 0;
	static final int H = 1;
	static final int D = 2;
	static final int SPACE = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		board = new int[N + 1][N + 1];
		dp = new int[3][N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dp[0][1][2] = 1;
		for (int i = 1; i <= N; i++) {
			for (int j = 3; j <= N; j++) {
				if (board[i][j] == SPACE) {
					dp[W][i][j] = dp[W][i][j - 1] + dp[D][i][j - 1];
				}
				if (board[i][j] == SPACE) {
					dp[H][i][j] = dp[H][i - 1][j] + dp[D][i - 1][j];
				}
				if (board[i][j] == SPACE && board[i - 1][j] == SPACE && board[i][j - 1] == SPACE) {
					dp[D][i][j] = dp[W][i - 1][j - 1] + dp[H][i - 1][j - 1] + dp[D][i - 1][j - 1];
				}
			}
		}

		System.out.println(dp[W][N][N] + dp[H][N][N] + dp[D][N][N]);
	}
}
