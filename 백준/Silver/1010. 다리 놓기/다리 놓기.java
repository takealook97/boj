import java.util.*;
import java.io.*;
public class Main {
	static int T, n, m;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			n = Math.min(n, m - n);
			dp = new int[m + 1][m + 1];
			
			for (int i = 1; i <= m; i++) {
				for (int j = 0; j <= n; j++) {
					if (j == 0 || j == i) {
						dp[i][j] = 1;
					} else {
						dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
					}
				}
			}
			sb.append(dp[m][n]).append("\n");
		}
		System.out.print(sb);
	}
}
