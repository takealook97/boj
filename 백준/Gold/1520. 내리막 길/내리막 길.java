import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] board, dp;
	static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N + 1][M + 1];
		dp = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			Arrays.fill(dp[i], -1);
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(getCount(1, 1));
	}

	static int getCount(int nowY, int nowX) {
		if (nowY == N && nowX == M) {
			return 1;
		}
		if (dp[nowY][nowX] != -1) {
			return dp[nowY][nowX];
		}

		dp[nowY][nowX] = 0;
		for (int i = 0; i < 4; i++) {
			int nextY = nowY + dy[i];
			int nextX = nowX + dx[i];
			if (isInRange(nowY + dy[i], nowX + dx[i])) {
				if (board[nowY][nowX] > board[nextY][nextX]) {
					dp[nowY][nowX] += getCount(nextY, nextX);
				}
			}
		}
		return dp[nowY][nowX];
	}

	static boolean isInRange(int y, int x) {
		return 1 <= y && y <= N && 1 <= x && x <= M;
	}
}
