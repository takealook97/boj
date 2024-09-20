import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, K, D;
	static int[][] rules;
	static int answer = 0;

	static final int FROM = 0, TO = 1, GAP = 2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		rules = new int[K][3];

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			rules[i][FROM] = Integer.parseInt(st.nextToken());
			rules[i][TO] = Integer.parseInt(st.nextToken());
			rules[i][GAP] = Integer.parseInt(st.nextToken());
		}

		int lo = 1, hi = N, mid;

		while (lo <= hi) {
			mid = lo + (hi - lo) / 2;
			if (isPossible(mid)) {
				answer = mid;
				hi = mid - 1;
			} else {
				lo = mid + 1;
			}
		}

		System.out.println(answer);
	}

	static boolean isPossible(int target) {
		long count = 0;
		for (int[] rule : rules) {
			if (rule[FROM] > target) {
				continue;
			}
			count += (Math.min(rule[TO], target) - rule[FROM]) / rule[GAP] + 1;
			if (count >= D) {
				return true;
			}
		}

		return count >= D;
	}
}
