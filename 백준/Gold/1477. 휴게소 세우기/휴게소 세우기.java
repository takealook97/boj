import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M, L;
	static int[] arr;
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		arr = new int[N + 2];
		if (N > 0) {
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
		}

		arr[N + 1] = L;
		Arrays.sort(arr);

		int lo = 1, hi = L - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (isPossible(mid)) {
				answer = mid;
				hi = mid - 1;
			} else {
				lo = mid + 1;
			}
		}
		
		System.out.println(answer);
	}

	static boolean isPossible(int max) {
		int count = 0;
		for (int i = 1; i < arr.length; i++) {
			count += (arr[i] - arr[i - 1] - 1) / max;
		}
		return count <= M;
	}
}
