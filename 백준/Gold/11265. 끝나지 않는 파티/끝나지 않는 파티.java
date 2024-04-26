import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, A, B, C;
	static int[][] times;
	static final String ENJOY = "Enjoy other party", STAY = "Stay here";

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		times = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				times[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		setMinDist();

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken()) - 1;
			B = Integer.parseInt(st.nextToken()) - 1;
			C = Integer.parseInt(st.nextToken());
			sb.append(isPossible() ? ENJOY : STAY).append("\n");
		}

		System.out.print(sb);
	}

	static void setMinDist() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					if (times[j][k] > times[j][i] + times[i][k]) {
						times[j][k] = times[j][i] + times[i][k];
					}
				}
			}
		}
	}

	static boolean isPossible() {
		return times[A][B] <= C;
	}
}
