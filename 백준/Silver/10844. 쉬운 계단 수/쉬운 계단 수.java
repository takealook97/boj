import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int N;
	static int[][] dp;
	static long answer = 0;

	static final int MOD = 1000000000;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		dp = new int[N + 1][10];

		Arrays.fill(dp[1], 1);
		dp[1][0] = 0;

		for (int i = 2; i <= N; i++) {
			for (int j = 0; j < 10; j++) {
				if (j > 0) {
					dp[i][j] += dp[i - 1][j - 1];
				}
				if (j < 9) {
					dp[i][j] += dp[i - 1][j + 1];
				}
				dp[i][j] %= MOD;
			}
		}

		for (int i : dp[N]) {
			answer += i;
		}

		System.out.println(answer % MOD);
	}
}
