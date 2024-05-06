import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int T, n;
	static int[] arr;
	static boolean[] visited, check;
	static int answer;

	static final int SIZE = 100000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		arr = new int[SIZE + 1];
		visited = new boolean[SIZE + 1];
		check = new boolean[SIZE + 1];
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		while (T-- > 0) {
			Arrays.fill(visited, false);
			Arrays.fill(check, false);
			answer = 0;

			n = Integer.parseInt(br.readLine());

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 1; i <= n; i++) {
				if (!check[i]) {
					dfs(i);
				}
			}
			sb.append(n - answer).append("\n");
		}

		System.out.print(sb);
	}

	static void dfs(int idx) {
		if (visited[idx]) {
			check[idx] = true;
			answer++;
		} else {
			visited[idx] = true;
		}

		if (!check[arr[idx]]) {
			dfs(arr[idx]);
		}
		visited[idx] = false;
		check[idx] = true;
	}
}
