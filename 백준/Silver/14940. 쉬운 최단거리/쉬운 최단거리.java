import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n, m, y, x;
	static int[][] arr;
	static int[][] answer;
	static boolean[][] visit;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};

	static class Point {
		int y, x, d;

		public Point(int y, int x, int d) {
			this.y = y;
			this.x = x;
			this.d = d;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		answer = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 2) {
					y = i;
					x = j;
				}
			}
		}
		bfs();
		checkUnreachable();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				sb.append(answer[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}

	static void bfs() {
		visit = new boolean[n][m];
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(y, x, 0));
		visit[y][x] = true;
		while (!queue.isEmpty()) {
			Point now = queue.poll();
			int nowY = now.y;
			int nowX = now.x;
			int nowD = now.d;
			answer[nowY][nowX] = nowD;
			for (int i = 0; i < 4; i++) {
				int nextY = nowY + dy[i];
				int nextX = nowX + dx[i];
				int nextD = nowD + 1;
				if (0 <= nextY && nextY < n && 0 <= nextX && nextX < m && !visit[nextY][nextX]) {
					if (arr[nextY][nextX] != 0) {
						visit[nextY][nextX] = true;
						queue.add(new Point(nextY, nextX, nextD));
					}
				}
			}
		}
	}

	static void checkUnreachable() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (!visit[i][j] && arr[i][j] != 0) {
					answer[i][j] = -1;
				}
			}
		}
	}
}
