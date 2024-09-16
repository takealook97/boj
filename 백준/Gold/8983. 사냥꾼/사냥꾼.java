import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int M, N, L, x, y;
	static int[] arr;
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		arr = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		int idx, prev, now;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());

			idx = lowerBound();
			if (idx >= M) {
				idx--;
			} else if (idx > 0) {
				prev = arr[idx - 1];
				now = arr[idx];
				if (Math.abs(x - prev) < Math.abs(x - now)) {
					idx--;
				}
			}

			if (Math.abs(x - arr[idx]) + y <= L) {
				answer++;
			}
		}

		System.out.println(answer);
	}

	static int lowerBound() {
		int lo = 0;
		int hi = M - 1;
		int mid;

		while (lo <= hi) {
			mid = lo + (hi - lo) / 2;

			if (arr[mid] < x) {
				lo = mid + 1;
			} else {
				hi = mid - 1;
			}
		}

		return lo;
	}
}
