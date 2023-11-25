import java.io.*;
import java.util.*;

public class Main {
	static int R, C;
	static int[][] board;
	static boolean[] visit;
	static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
	static final int ALPHABET_COUNT = 26;
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new int[R][C];
		visit = new boolean[ALPHABET_COUNT];
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				board[i][j] = line.charAt(j) - 'A';
			}
		}
		dfs(0, 0, 0);
		if (answer == 0) {
			System.out.println(1);
		} else {
			System.out.println(answer);
		}
	}

	static void dfs(int y, int x, int count) {
		if (visit[board[y][x]]) {
			answer = Math.max(answer, count);
			return;
		}
		visit[board[y][x]] = true;
		for (int i = 0; i < 4; i++) {
			int nextY = y + dy[i];
			int nextX = x + dx[i];
			if (0 <= nextY && nextY < R && 0 <= nextX && nextX < C) {
				dfs(nextY, nextX, count + 1);
			}
		}
		visit[board[y][x]] = false;
	}
}
