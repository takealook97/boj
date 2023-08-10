import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] board;
	static boolean[] visit;
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		visit = new boolean[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0, 0);
		System.out.println(answer);
	}

	static void dfs(int index, int depth) {
		if (depth == N / 2) {
			int startTeam = 0;
			int linkTeam = 0;
			for (int i = 0; i < N - 1; i++) {
				for (int j = i + 1; j < N; j++) {
					if (visit[i] && visit[j]) {
						startTeam += (board[i][j] + board[j][i]);
					} else if (!visit[i] && !visit[j]) {
						linkTeam += (board[i][j] + board[j][i]);
					}
				}
			}
			answer = Math.min(answer, Math.abs(startTeam - linkTeam));
			return;
		}

		for (int i = index; i < N; i++) {
			if (!visit[i]) {
				visit[i] = true;
				dfs(i + 1, depth + 1);
				visit[i] = false;
			}
		}
	}
}
