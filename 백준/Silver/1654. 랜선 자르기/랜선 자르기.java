import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int K, N;
	static long[] arr;
	static long hi;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		arr = new long[K];
		for (int i = 0; i < K; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			hi = Math.max(arr[i], hi);
		}

		hi++;
		long lo = 0;
		long mid = 0;
		while (lo < hi) {
			mid = lo + (hi - lo) / 2;

			// set count
			long count = 0;
			for (int i = 0; i < K; i++) {
				count += arr[i] / mid;
			}

			if (count < N) {
				hi = mid;
			} else {
				lo = mid + 1;
			}
		}

		System.out.println(lo - 1);
	}
}
