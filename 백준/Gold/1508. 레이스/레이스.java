import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static int[] arr;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());  // 트랙의 길이
		M = Integer.parseInt(st.nextToken());  // 배치할 심판의 수
		K = Integer.parseInt(st.nextToken());  // 심판이 배치 가능한 위치의 수

		arr = new int[K];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int lo = 1;
		int hi = arr[K - 1] - arr[0];

		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (isPossible(mid)) {
				lo = mid + 1;
			} else {
				hi = mid - 1;
			}
		}

		setVisited(hi);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < K; i++) {
			sb.append(visited[i] ? 1 : 0);
		}
		System.out.println(sb);
	}

	static boolean isPossible(int gap) {
		int count = 1;
		int cur = arr[0];

		for (int i = 1; i < K; i++) {
			if (arr[i] - cur >= gap) {
				count++;
				cur = arr[i];
			}
			if (count == M) {
				return true;
			}
		}

		return false;
	}

	static void setVisited(int gap) {
		int count = 1;
		int cur = arr[0];

		visited = new boolean[K];
		visited[0] = true;

		for (int i = 1; i < K; i++) {
			if (arr[i] - cur >= gap) {
				visited[i] = true;
				count++;
				cur = arr[i];
			}
			if (count == M) {
				return;
			}
		}

		for (int i = K - 1; i >= 0 && count < M; i--) {
			if (!visited[i] && arr[i] - cur >= gap) {
				visited[i] = true;
				count++;
				cur = arr[i];
			}
		}
	}
}
