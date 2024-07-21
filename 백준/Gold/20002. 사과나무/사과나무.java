import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, answer = Integer.MIN_VALUE;
	static int[][] arr, sumArr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		sumArr = new int[N + 1][N + 1];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				sumArr[i][j] = arr[i - 1][j - 1]
					+ sumArr[i - 1][j]
					+ sumArr[i][j - 1]
					- sumArr[i - 1][j - 1];
			}
		}

		for (int i = 1; i <= N; i++) {
			answer = Math.max(answer, find(sumArr, N, i));
		}

		System.out.println(answer);
	}

	static int find(int[][] sum, int N, int K) {
		int max = Integer.MIN_VALUE;

		for (int i = K; i <= N; i++) {
			for (int j = K; j <= N; j++) {
				int cur = sum[i][j]
					- sum[i - K][j]
					- sum[i][j - K]
					+ sum[i - K][j - K];
				max = Math.max(max, cur);
			}
		}

		return max;
	}
}
