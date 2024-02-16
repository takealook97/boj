import java.io.*;
import java.util.*;

public class Main {
	static int[] arr, LIS;
	static int answer = 1;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[N];
		LIS = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		LIS[0] = arr[0];

		for (int i = 1; i < N; i++) {
			if (LIS[answer - 1] < arr[i]) {
				answer++;
				LIS[answer - 1] = arr[i];
			} else {
				int target = lowerBound(arr[i], answer);
				LIS[target] = arr[i];
			}
		}

		System.out.println(answer);
	}

	static int lowerBound(int target, int size) {
		int lo = 0;
		int hi = size - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (LIS[mid] < target) {
				lo = mid + 1;
			} else {
				hi = mid - 1;
			}
		}

		return lo;
	}
}
