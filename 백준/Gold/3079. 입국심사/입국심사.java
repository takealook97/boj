import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] T;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = new int[N];
		for (int i = 0; i < N; i++) {
			T[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(T);

		long lo = 0;
		long hi = (long)M * T[0];
		long answer = hi;

		while (lo <= hi) {
			long mid = lo + (hi - lo) / 2;
			long sum = 0;
			for (int i = 0; i < N; i++) {
				sum += mid / T[i];
			}
			if (sum >= M) {
				hi = mid - 1;
				answer = Math.min(answer, mid);
			} else {
				lo = mid + 1;
			}
		}

		System.out.println(answer);
	}
}
