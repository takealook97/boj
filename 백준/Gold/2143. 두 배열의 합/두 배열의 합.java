import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int T, N, M, aSize, bSize;
	static int[] sumA, sumB, arrA, arrB;
	static long answer = 0;

	static final int MAX = 500500;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		N = Integer.parseInt(br.readLine());
		sumA = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			sumA[i] = sumA[i - 1] + Integer.parseInt(st.nextToken());
		}

		M = Integer.parseInt(br.readLine());
		sumB = new int[M + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {
			sumB[i] = sumB[i - 1] + Integer.parseInt(st.nextToken());
		}

		setArr();

		int targetA, targetB, aLower, aUpper, bLower, bUpper, countA, countB;
		for (int idx = 0; idx < aSize; ) {
			targetA = arrA[idx];
			targetB = T - arrA[idx];

			aLower = binarySearch(arrA, aSize - 1, targetA, true);
			aUpper = binarySearch(arrA, aSize - 1, targetA, false);
			bLower = binarySearch(arrB, bSize - 1, targetB, true);
			bUpper = binarySearch(arrB, bSize - 1, targetB, false);

			countA = aUpper - aLower;
			countB = bUpper - bLower;

			answer += (long)countA * (long)countB;
			idx = aUpper;
		}

		System.out.println(answer);
	}

	static void setArr() {
		arrA = new int[MAX];
		arrB = new int[MAX];
		int idx = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < i; j++) {
				arrA[idx++] = sumA[i] - sumA[j];
			}
		}
		aSize = idx;

		idx = 0;
		for (int i = 1; i <= M; i++) {
			for (int j = 0; j < i; j++) {
				arrB[idx++] = sumB[i] - sumB[j];
			}
		}
		bSize = idx;

		Arrays.sort(arrA, 0, aSize);
		Arrays.sort(arrB, 0, bSize);
	}

	static int binarySearch(int[] arr, int hi, int target, boolean isLowerBound) {
		int lo = 0;
		int mid;

		while (lo <= hi) {
			mid = lo + (hi - lo) / 2;

			if (isLowerBound) {
				if (arr[mid] < target) {
					lo = mid + 1;
				} else {
					hi = mid - 1;
				}
			} else {
				if (arr[mid] <= target) {
					lo = mid + 1;
				} else {
					hi = mid - 1;
				}
			}
		}

		return lo;
	}
}
