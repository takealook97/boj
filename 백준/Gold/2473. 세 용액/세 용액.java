import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static long[] arr;
	static long closeSum = Long.MAX_VALUE;
	static long[] answer = new long[3];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new long[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}

		Arrays.sort(arr);

		for (int i = 0; i < N - 2; i++) {
			binarySearch(i);
		}

		Arrays.sort(answer);

		System.out.println(answer[0] + " " + answer[1] + " " + answer[2]);
	}

	static void binarySearch(int idx) {
		int lo = idx + 1;
		int hi = N - 1;

		while (lo < hi) {
			long sum = arr[idx] + arr[lo] + arr[hi];

			if (Math.abs(sum) < Math.abs(closeSum)) {
				closeSum = sum;
				answer[0] = arr[idx];
				answer[1] = arr[lo];
				answer[2] = arr[hi];
			}

			if (sum < 0) {
				lo++;
			} else {
				hi--;
			}
		}
	}
}
