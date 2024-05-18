import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static long[] arr, right, left;
	static long a, b, temp, answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new long[N];
		right = new long[N];
		left = new long[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}

		for (int i = 0; i < N; i++) {
			temp += arr[i];
			right[i] = temp;
		}

		temp = 0;
		for (int i = N - 1; i >= 0; i--) {
			temp += arr[i];
			left[i] = temp;
		}

		long total = right[N - 1];

		for (int i = 1; i < N - 1; i++) {
			a = total - arr[0] - arr[i];
			b = total - right[i];
			answer = Math.max(answer, a + b);
		}

		for (int i = N - 2; i >= 1; i--) {
			a = total - arr[N - 1] - arr[i];
			b = total - left[i];
			answer = Math.max(answer, a + b);
		}

		for (int i = 1; i < N - 1; i++) {
			a = right[i] - arr[0];
			b = left[i] - arr[N - 1];
			answer = Math.max(answer, a + b);
		}

		System.out.println(answer);
	}
}
