import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int T, N, K;
	static int[] arr;
	static int minGap, count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		arr = new int[1000000];

		while (T-- > 0) {
			minGap = Integer.MAX_VALUE;
			count = 0;

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			Arrays.sort(arr, 0, N);
			search();

			sb.append(count).append("\n");
		}

		System.out.print(sb);
	}

	static void search() {
		int lo = 0;
		int hi = N - 1;
		int sum, gap;
		while (lo < hi) {
			sum = arr[lo] + arr[hi];
			gap = Math.abs(K - sum);

			if (gap == minGap) {
				count++;
			} else if (gap < minGap) {
				count = 1;
				minGap = gap;
			}

			if (sum > K) {
				hi--;
			} else {
				lo++;
			}
		}
	}
}
