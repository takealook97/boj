import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] T, P;
	static boolean[] visit;
	static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		T = new int[N];
		P = new int[N];
		visit = new boolean[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < N; i++) {
			if (i + T[i] - 1 < N) {
				dfs(i + T[i] - 1, P[i]);
			}
		}
		System.out.println(result);
	}

	static void dfs(int end, int sum) {
		if (end < N) {
			result = Math.max(result, sum);
			for (int i = end + 1; i < N; i++) {
				dfs(i + T[i] - 1, sum + P[i]);
			}
		}
	}
}
