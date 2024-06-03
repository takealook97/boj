import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, K, A, B;
	static int[][] arr;

	static final int INF = Integer.MAX_VALUE / 2;
	static final String POSSIBLE = "Small World!", IMPOSSIBLE = "Big World!";

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			Arrays.fill(arr[i], INF);
			arr[i][i] = 0;
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			arr[A][B] = 1;
			arr[B][A] = 1;
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

		for (int i = 1; i <= N - 1; i++) {
			for (int j = i + 1; j <= N; j++) {
				if (arr[i][j] > 6) {
					System.out.println(IMPOSSIBLE);
					return;
				}
			}
		}

		System.out.println(POSSIBLE);
	}
}
