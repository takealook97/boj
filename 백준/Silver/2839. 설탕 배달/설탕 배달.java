import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static final int INF = 987654321;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] dp = new int[N + 1];
		Arrays.fill(dp, INF);
		dp[3] = 1;
		if (N >= 5) {
			dp[5] = 1;
		}

		for (int i = 6; i <= N; i++) {
			dp[i] = Math.min(dp[i - 3] + 1, dp[i - 5] + 1);
		}
		if (dp[N] < INF) {
			System.out.println(dp[N]);
		} else {
			System.out.println(-1);
		}
	}
}
