import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, target;
	static boolean[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for (int tc = 0; tc < 3; tc++) {
			N = Integer.parseInt(br.readLine());
			int sum = 0;

			int[] priceArr = new int[N];
			int[] countArr = new int[N];

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int price = Integer.parseInt(st.nextToken());
				int count = Integer.parseInt(st.nextToken());

				priceArr[i] = price;
				countArr[i] = count;
				sum += price * count;
			}

			if (sum % 2 != 0) {
				sb.append(0).append("\n");
				continue;
			}

			target = sum / 2;
			dp = new boolean[target + 1];
			dp[0] = true;

			for (int i = 0; i < N; i++) {
				int num = 1;
				while (countArr[i] > 0) {
					int multi = Math.min(num, countArr[i]);
					int value = multi * priceArr[i];
					for (int price = target; price >= value; price--) {
						if (!dp[price] && dp[price - value]) {
							dp[price] = true;
						}
					}
					countArr[i] -= multi;
					num *= 2;
				}
			}

			sb.append(dp[target] ? 1 : 0).append("\n");
		}

		System.out.print(sb);
	}
}
