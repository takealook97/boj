import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int C, N;
	static int[] price, client, dp;
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		price = new int[N];
		client = new int[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			price[i] = Integer.parseInt(st.nextToken());
			client[i] = Integer.parseInt(st.nextToken());
		}

		int size = C + 101;
		dp = new int[size];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;

		for (int i = 0; i < N; i++) {
			for (int j = client[i]; j < size; j++) {
				if (dp[j - client[i]] != Integer.MAX_VALUE) {
					dp[j] = Math.min(dp[j], dp[j - client[i]] + price[i]);
				}
			}
		}

		for (int i = C; i < size; i++) {
			answer = Math.min(answer, dp[i]);
		}

		System.out.println(answer);
	}
}
