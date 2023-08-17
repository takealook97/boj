import java.io.*;
import java.util.*;

public class Main {
	static int N, M, V;
	static int[][] board;
	static boolean[] visit;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		board = new int[N + 1][N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			board[a][b] = board[b][a] = 1;
		}

		visit = new boolean[N + 1];
		dfs(V);
		sb.append("\n");

		visit = new boolean[N + 1];
		bfs(V);

		System.out.println(sb);
	}

	static void dfs(int num) {
		sb.append(num).append(" ");
		visit[num] = true;
		for (int i = 0; i <= N; i++) {
			if (board[num][i] == 1 && !visit[i])
				dfs(i);
		}
	}

	static void bfs(int num) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(num);
		visit[num] = true;
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			sb.append(cur).append(" ");
			for (int i = 0; i <= N; i++) {
				if (board[cur][i] == 1 && !visit[i]) {
					queue.add(i);
					visit[i] = true;
				}
			}
		}
	}
}
