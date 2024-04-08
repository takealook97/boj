import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] board, dp, temp;
	static final int FROM_LEFT = 0, FROM_RIGHT = 1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N + 1][M + 1];
		dp = new int[N + 1][M + 1];
		temp = new int[2][M + 2];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// init
		dp[1][1] = board[1][1];
		for (int j = 2; j <= M; j++) {
			dp[1][j] = board[1][j] + dp[1][j - 1];
		}

		// dp
		for (int i = 2; i <= N; i++) {
			temp[FROM_LEFT][0] = dp[i - 1][1];
			for (int j = 1; j <= M; j++) {
				temp[FROM_LEFT][j] = Math.max(temp[FROM_LEFT][j - 1], dp[i - 1][j]) + board[i][j];
			}

			temp[FROM_RIGHT][M + 1] = dp[i - 1][M];
			for (int j = M; j >= 1; j--) {
				temp[FROM_RIGHT][j] = Math.max(temp[FROM_RIGHT][j + 1], dp[i - 1][j]) + board[i][j];
			}

			for (int j = 1; j <= M; j++) {
				dp[i][j] = Math.max(temp[FROM_LEFT][j], temp[FROM_RIGHT][j]);
			}
		}

		System.out.println(dp[N][M]);
	}
}
