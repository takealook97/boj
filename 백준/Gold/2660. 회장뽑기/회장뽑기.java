import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] arr;
	static ArrayList<Integer> candidates = new ArrayList<>();
	static int minScore = Integer.MAX_VALUE;
	static final int INF = 987654321;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			Arrays.fill(arr[i], INF);
			arr[i][i] = 0;
		}

		StringTokenizer st;
		int from, to;
		while (true) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			if (from == -1 && to == -1) {
				break;
			}

			arr[from][to] = 1;
			arr[to][from] = 1;
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				for (int k = 1; k <= N; k++) {
					if (arr[j][k] > arr[j][i] + arr[i][k]) {
						arr[j][k] = arr[j][i] + arr[i][k];
					}
				}
			}
		}

		for (int i = 1; i <= N; i++) {
			int maxDistance = 0;
			for (int j = 1; j <= N; j++) {
				if (arr[i][j] != INF && arr[i][j] > maxDistance) {
					maxDistance = arr[i][j];
				}
			}
			minScore = Math.min(minScore, maxDistance);
		}

		for (int i = 1; i <= N; i++) {
			int maxDistance = 0;
			for (int j = 1; j <= N; j++) {
				if (arr[i][j] != INF && arr[i][j] > maxDistance) {
					maxDistance = arr[i][j];
				}
			}
			if (maxDistance == minScore) {
				candidates.add(i);
			}
		}

		StringBuilder sb = new StringBuilder();
		sb.append(minScore).append(" ").append(candidates.size()).append("\n");
		for (int candidate : candidates) {
			sb.append(candidate).append(" ");
		}

		System.out.println(sb);
	}
}
