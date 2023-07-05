import java.io.*;
import java.util.*;

public class Main {
	static int[][] arr;
	static int R, C, T;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static Point upP;
	static Point downP;

	static class Point {
		int y, x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		arr = new int[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == -1 && upP == null) {
					upP = new Point(i, j);
				} else if (arr[i][j] == -1 && upP != null && downP == null) {
					downP = new Point(i, j);
				}
			}
		}
		while (T-- > 0) {
			spread();
			purify();
		}
		System.out.println(getDust());
	}

	static void spread() {
		int[][] plus = new int[R][C];
		Queue<Point> queue = new LinkedList<>();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (arr[i][j] > 0) {
					queue.add(new Point(i, j));
				}
			}
		}

		while (!queue.isEmpty()) {
			Point now = queue.poll();
			int nowY = now.y;
			int nowX = now.x;
			int part = arr[nowY][nowX] / 5;
			int count = 0;
			for (int i = 0; i < 4; i++) {
				int nextY = nowY + dy[i];
				int nextX = nowX + dx[i];
				if (0 <= nextY && nextY < R && 0 <= nextX && nextX < C && arr[nextY][nextX] != -1) {
					plus[nextY][nextX] += part;
					count++;
				}
			}
			arr[nowY][nowX] -= (part * count);
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				arr[i][j] += plus[i][j];
			}
		}
	}

	static void purify() {
		for (int i = upP.y - 1; i > 0; i--) {
			arr[i][0] = arr[i - 1][0];
		}
		for (int i = 0; i < C - 1; i++) {
			arr[0][i] = arr[0][i + 1];
		}
		for (int i = 0; i < upP.y; i++) {
			arr[i][C - 1] = arr[i + 1][C - 1];
		}
		for (int i = C - 1; i > 1; i--) {
			arr[upP.y][i] = arr[upP.y][i - 1];
		}
		arr[upP.y][1] = 0;

		for (int i = downP.y + 1; i < R - 1; i++) {
			arr[i][0] = arr[i + 1][0];
		}
		for (int i = 0; i < C - 1; i++) {
			arr[R - 1][i] = arr[R - 1][i + 1];
		}
		for (int i = R - 1; i > downP.y; i--) {
			arr[i][C - 1] = arr[i - 1][C - 1];
		}
		for (int i = C - 1; i > 1; i--) {
			arr[downP.y][i] = arr[downP.y][i - 1];
		}
		arr[downP.y][1] = 0;
	}

	static int getDust() {
		int result = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (arr[i][j] != -1) {
					result += arr[i][j];
				}
			}
		}
		return result;
	}
}
