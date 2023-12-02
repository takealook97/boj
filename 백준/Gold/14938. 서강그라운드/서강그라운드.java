import java.io.*;
import java.util.*;

public class Main {
	static int n, m, r;
	static int[] items;
	static long[][] dist;
	static final int INF = 987654321;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		items = new int[n + 1];
		dist = new long[n + 1][n + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			items[i] = Integer.parseInt(st.nextToken());
		}

		// set inf
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (i != j) {
					dist[i][j] = INF;
				}
			}
		}

		// set dist
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int distance = Integer.parseInt(st.nextToken());
			dist[start][end] = distance;
			dist[end][start] = distance;
		}

		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}

		System.out.println(getMax());
	}

	static int getMax() {
		int answer = 0;
		for (int i = 1; i <= n; i++) {
			int sum = 0;
			for (int j = 1; j <= n; j++) {
				if (dist[i][j] <= m) {
					sum += items[j];
				}
			}
			answer = Math.max(answer, sum);
		}
		return answer;
	}
}
