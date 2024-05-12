import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] board;
	static int[][][] dp;
	static int[] dx = {-1, 0, 1};
	static int answer = Integer.MAX_VALUE;

	static final int INF = 987654321;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		board = new int[N + 1][M + 1];
		dp = new int[N + 1][M + 1][3];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				Arrays.fill(dp[i][j], INF);
			}
		}

		for (int j = 1; j <= M; j++) {
			for (int dir = 0; dir < 3; dir++) {
				dp[1][j][dir] = board[1][j];
			}
		}

		for (int i = 1; i < N; i++) {
			for (int j = 1; j <= M; j++) {
				for (int dir = 0; dir < 3; dir++) {
					int prevX = j + dx[dir];
					if (prevX >= 1 && prevX <= M) {
						int nowY = i + 1;
						if (dir != 0 && dp[i][j][0] + board[nowY][prevX] < dp[nowY][prevX][dir]) {
							dp[nowY][prevX][dir] = dp[i][j][0] + board[nowY][prevX];
						}
						if (dir != 1 && dp[i][j][1] + board[nowY][prevX] < dp[nowY][prevX][dir]) {
							dp[nowY][prevX][dir] = dp[i][j][1] + board[nowY][prevX];
						}
						if (dir != 2 && dp[i][j][2] + board[nowY][prevX] < dp[nowY][prevX][dir]) {
							dp[nowY][prevX][dir] = dp[i][j][2] + board[nowY][prevX];
						}
					}
				}
			}
		}

		for (int j = 1; j <= M; j++) {
			for (int d = 0; d < 3; d++) {
				answer = Math.min(answer, dp[N][j][d]);
			}
		}

		System.out.println(answer);
	}
}
