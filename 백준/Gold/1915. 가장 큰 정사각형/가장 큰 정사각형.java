import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] dp;
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		if (N == 1 && M == 1) {
			System.out.println(1);
			return;
		}

		dp = new int[N + 1][M + 1];

		String line;
		int num;
		for (int i = 1; i <= N; i++) {
			line = br.readLine();
			for (int j = 1; j <= M; j++) {
				num = line.charAt(j - 1) - '0';

				if (i == 1 && j == 1) {
					dp[i][j] = num;
					continue;
				}

				if (num == 1) {
					dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
					answer = Math.max(answer, dp[i][j] * dp[i][j]);
				}
			}
		}

		System.out.println(answer);
	}
}
