import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] arr;
	static int[] targets;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);

		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());

		targets = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			targets[i] = Integer.parseInt(st.nextToken());
		}

		StringBuilder sb = new StringBuilder();
		for (int target : targets) {
			sb.append(binarySearch(target) ? 1 : 0).append("\n");
		}

		System.out.print(sb);
	}

	static boolean binarySearch(int target) {
		int lo = 0;
		int hi = arr.length - 1;
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
