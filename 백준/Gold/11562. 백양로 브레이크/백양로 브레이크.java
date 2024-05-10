import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int n, m, u, v, b, k, s, e;
	static int[][] arr;
	static final int INF = 987654321;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(arr[i], INF);
			arr[i][i] = 0;
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken()) - 1;
			v = Integer.parseInt(st.nextToken()) - 1;
			b = Integer.parseInt(st.nextToken());
			arr[u][v] = 0;
			if (b == 1) {
				arr[v][u] = 0;
			} else {
				arr[v][u] = 1;
			}
		}

		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (arr[i][j] > arr[i][k] + arr[k][j]) {
						arr[i][j] = arr[i][k] + arr[k][j];
					}
				}
			}
		}

		k = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken()) - 1;
			e = Integer.parseInt(st.nextToken()) - 1;
			sb.append(arr[s][e]).append("\n");
		}

		System.out.print(sb);
	}
}
