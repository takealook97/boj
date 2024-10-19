import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static ArrayList<Integer>[] listArr;
	static boolean[] visited;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		listArr = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		dp = new int[N + 1][2];

		for (int i = 1; i <= N; i++) {
			listArr[i] = new ArrayList<>();
		}

		StringTokenizer st;
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			listArr[from].add(to);
			listArr[to].add(from);
		}

		find(1);
		System.out.println(Math.min(dp[1][0], dp[1][1]));
	}

	static void find(int now) {
		visited[now] = true;

		dp[now][0] = 0;
		dp[now][1] = 1;

		for (int next : listArr[now]) {
			if (!visited[next]) {
				find(next);
				dp[now][0] += dp[next][1];
				dp[now][1] += Math.min(dp[next][0], dp[next][1]);
			}
		}
	}
}
