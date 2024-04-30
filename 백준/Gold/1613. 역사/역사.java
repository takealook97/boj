import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int n, k, s;
	static int[][] path;
	static final int INF = 987654321;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		path = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			Arrays.fill(path[i], INF);
			path[i][i] = 0;
		}

		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			path[from][to] = 1;
			path[to][from] = INF;
		}

		getMin();

		StringBuilder sb = new StringBuilder();
		s = Integer.parseInt(br.readLine());
		for (int i = 0; i < s; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			if (path[from][to] >= INF && path[to][from] >= INF) {
				sb.append(0);
			} else if (path[from][to] < INF && path[to][from] == INF) {
				sb.append(-1);
			} else {
				sb.append(1);
			}
			sb.append("\n");
		}

		System.out.print(sb);
	}

	static void getMin() {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				for (int k = 1; k <= n; k++) {
					if (path[j][k] > path[j][i] + path[i][k]) {
						path[j][k] = path[j][i] + path[i][k];
					}
				}
			}
		}
	}
}
