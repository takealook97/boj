import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, C;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if (arr[i] == C) {
				System.out.println(1);
				return;
			}
		}

		Arrays.sort(arr);

		int sum = 0;
		boolean answer = false;
		out:
		for (int lo = 0; lo < N - 1; lo++) {
			for (int hi = lo + 1; hi < N; hi++) {
				sum = arr[lo] + arr[hi];
				if (sum == C) {
					answer = true;
					break out;
				} else if (sum < C) {
					if (binarySearch(lo, hi, C - (arr[lo] + arr[hi]))) {
						answer = true;
						break out;
					}
				}
			}
		}

		System.out.println(answer ? 1 : 0);
	}

	static boolean binarySearch(int lo, int hi, int target) {
		lo++;
		hi--;

		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (arr[mid] == target) {
				return true;
			} else if (arr[mid] < target) {
				lo = mid + 1;
			} else {
				hi = mid - 1;
			}
		}

		return false;
	}
}
