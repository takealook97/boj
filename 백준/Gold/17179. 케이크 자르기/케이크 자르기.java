import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, L;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		arr = new int[M];
		for (int i = 0; i < M; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.append(binarySearch(Integer.parseInt(br.readLine()))).append("\n");
		}

		System.out.print(sb);
	}

	static int binarySearch(int piece) {
		int lo = 1;
		int hi = L;
		int answer = 0;

		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (isPossible(mid, piece)) {
				answer = mid;
				lo = mid + 1;
			} else {
				hi = mid - 1;
			}
		}

		return answer;
	}

	static boolean isPossible(int min, int cuts) {
		int prev = 0;
		int count = 0;

		for (int i = 0; i < M; i++) {
			if (arr[i] - prev >= min) {
				count++;
				prev = arr[i];
			}
		}

		if (L - prev >= min) {
			count++;
		}

		return count >= cuts + 1;
	}
}
