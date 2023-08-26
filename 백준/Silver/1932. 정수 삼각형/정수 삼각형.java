import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] arr = new int[n][n];
		int[][] dp = new int[n][n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < i + 1; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dp[n - 1] = Arrays.copyOf(arr[n - 1], n);
		for (int col = n - 2; col >= 0; col--) {
			for (int row = 0; row <= col; row++) {
				dp[col][row] = Math.max(dp[col + 1][row], dp[col + 1][row + 1]) + arr[col][row];
			}
		}
		System.out.println(dp[0][0]);
	}
}
