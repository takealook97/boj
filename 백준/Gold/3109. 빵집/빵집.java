import java.io.*;
import java.util.*;

public class Main {
	static int R, C;
	static boolean[][] visit;
	static boolean check;
	static int[] dy = { -1, 0, 1 }, dx = { 1, 1, 1 };// 우상, 우, 우하
	static final int DIR_COUNT = 3;
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		visit = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				if (line.charAt(j) == '.') {
					visit[i][j] = false;
				} else if (line.charAt(j) == 'x') {
					visit[i][j] = true;
				}
			}
		}
		for (int i = 0; i < R; i++) {
			if (!visit[i][0]) {
				visit[i][0] = true;
				check = false;
				dfs(i, 0);
			}
		}
		System.out.println(answer);
	}
	
	

	static void dfs(int y, int x) {// dfs 방식으로 백트래킹
		if (x == C - 1) {
			answer++;
			check = true;
			return;
		}

		for (int i = 0; i < DIR_COUNT; i++) {
			int nextY = y + dy[i];
			int nextX = x + dx[i];
			if (isMovable(nextY, nextX)) {
				visit[nextY][nextX] = true;
				dfs(nextY, nextX);
				if(check) {
					return;
				}
//				visit[nextY][nextX] = false;
			}
		}
	}

	static boolean isMovable(int nextY, int nextX) {
		return 0 <= nextY && nextY < R && 0 <= nextX && nextX < C && !visit[nextY][nextX];
	}

}
