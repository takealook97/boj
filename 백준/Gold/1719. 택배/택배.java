import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static int[][] arr, nextArr;
	static final int INF = 987654321;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n + 1][n + 1];
		nextArr = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			Arrays.fill(arr[i], INF);
			arr[i][i] = 0;
		}

		int from, to, weight;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			weight = Integer.parseInt(st.nextToken());
			arr[from][to] = weight;
			arr[to][from] = weight;
			nextArr[from][to] = to;
			nextArr[to][from] = from;
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				for (int k = 1; k <= n; k++) {
					if (i == j || j == k || i == k) {
						continue;
					}
					
					if (arr[j][k] > arr[j][i] + arr[i][k]) {
						nextArr[j][k] = nextArr[j][i];
						arr[j][k] = arr[j][i] + arr[i][k];
					}
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (i == j) {
					sb.append("-");
				} else {
					sb.append(nextArr[i][j]);
				}
				sb.append(" ");
			}
			sb.append("\n");
		}

		System.out.print(sb);
	}
}
