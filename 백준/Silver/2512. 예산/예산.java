import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M, sum;
	static int[] arr;
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		Arrays.sort(arr);

		int lo = 0;
		int hi = arr[N - 1];

		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			sum = 0;
			for (int i = 0; i < N; i++) {
				sum += Math.min(arr[i], mid);
			}

			if (sum > M) {
				hi = mid - 1;
			} else {
				lo = mid + 1;
				answer = mid;
			}
		}

		System.out.println(answer);
	}
}
