import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static int[][] arr;
	static int[][] next;
	static final int INF = 987654321;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());

		arr = new int[n + 1][n + 1];
		next = new int[n + 1][n + 1];

		for (int i = 1; i <= n; i++) {
			Arrays.fill(arr[i], INF);
			arr[i][i] = 0;
		}

		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			arr[a][b] = Math.min(arr[a][b], c);
			next[a][b] = b;
		}

		setMin();

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (arr[i][j] >= INF) {
					sb.append(0).append(" ");
				} else {
					sb.append(arr[i][j]).append(" ");
				}
			}
			sb.append("\n");
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (i == j || arr[i][j] >= INF) {
					sb.append(0).append("\n");
				} else {
					StringBuilder path = new StringBuilder();
					getPath(i, j, path);
					String[] cities = path.toString().split(" ");
					sb.append(cities.length).append(" ").append(path).append("\n");
				}
			}
		}

		System.out.print(sb);
	}

	static void setMin() {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				for (int k = 1; k <= n; k++) {
					if (arr[j][k] > arr[j][i] + arr[i][k]) {
						arr[j][k] = arr[j][i] + arr[i][k];
						next[j][k] = next[j][i];
					}
				}
			}
		}
	}

	static void getPath(int start, int end, StringBuilder path) {
		if (next[start][end] == 0) {
			return;
		}
		path.append(start).append(" ");
		while (start != end) {
			start = next[start][end];
			path.append(start).append(" ");
		}
	}
}
