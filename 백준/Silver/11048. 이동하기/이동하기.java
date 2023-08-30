import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static int[][] dp;
	static int[] dy = { -1, -1, 0 }, dx = { -1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dp = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				dp[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				int prevMax = 0;
				for (int idx = 0; idx < 3; idx++) {
					prevMax = Math.max(prevMax, dp[i + dy[idx]][j + dx[idx]]);
				}
				dp[i][j] += prevMax;
			}
		}
		System.out.println(dp[N][M]);
	}
}
