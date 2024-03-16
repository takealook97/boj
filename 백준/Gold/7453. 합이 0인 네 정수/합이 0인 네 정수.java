import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static long[] A, B, C, D, AB, CD;
	static long answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		A = new long[N];
		B = new long[N];
		C = new long[N];
		D = new long[N];
		AB = new long[N * N];
		CD = new long[N * N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			A[i] = Long.parseLong(st.nextToken());
			B[i] = Long.parseLong(st.nextToken());
			C[i] = Long.parseLong(st.nextToken());
			D[i] = Long.parseLong(st.nextToken());
		}

		int idx = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				AB[idx] = A[i] + B[j];
				CD[idx++] = C[i] + D[j];
			}
		}

		Arrays.sort(AB);
		Arrays.sort(CD);

		int lo = 0, hi = N * N - 1;
		long sum;
		while (lo < N * N && 0 <= hi) {
			sum = AB[lo] + CD[hi];
			if (sum < 0) {
				lo++;
			} else if (sum > 0) {
				hi--;
			} else {
				long loCnt = 1, hiCnt = 1;
				while (lo + 1 < N * N && AB[lo] == AB[lo + 1]) {
					loCnt++;
					lo++;
				}
				while (hi - 1 >= 0 && CD[hi] == CD[hi - 1]) {
					hiCnt++;
					hi--;
				}
				answer += loCnt * hiCnt;

				lo++;
			}
		}

		System.out.println(answer);
	}
}
