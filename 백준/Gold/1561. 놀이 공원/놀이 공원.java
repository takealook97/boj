import java.io.*;
import java.util.*;

public class Main {
	static long N;
	static int M;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Long.parseLong(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		if (N <= M) {
			System.out.println(N);
			return;
		}

		long left = 0;
		long right = N * 30L;
		long time = 0;

		while (left <= right) {
			long mid = (left + right) / 2;
			long count = M;
			for (int i = 0; i < M; i++) {
				count += mid / arr[i];
			}
			if (count >= N) {
				time = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}

		long prevCount = M;
		for (int i = 0; i < M; i++) {
			prevCount += (time - 1) / arr[i];
		}

		for (int i = 0; i < M; i++) {
			if (time % arr[i] == 0) {
				prevCount++;
			}
			if (prevCount == N) {
				System.out.println(i + 1);
				break;
			}
		}
	}
}
