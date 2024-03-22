import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int T, K;
	static int[] arr, sum;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while (T-- > 0) {
			K = Integer.parseInt(br.readLine());
			arr = new int[K + 1];
			dp = new int[K + 1][K + 1];
			sum = new int[K + 1];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= K; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				sum[i] = sum[i - 1] + arr[i];
			}

			for (int i = 1; i <= K; i++) {
				for (int from = 1; from <= K - i; from++) {
					int to = from + i;
					dp[from][to] = Integer.MAX_VALUE;
					for (int j = from; j < to; j++) {
						dp[from][to] = Math.min(dp[from][to],
							dp[from][j] + dp[j + 1][to] + sum[to] - sum[from - 1]);
					}
				}
			}
			sb.append(dp[1][K]).append("\n");
		}

		System.out.print(sb);
	}
}
