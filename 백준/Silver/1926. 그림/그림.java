import java.io.*;
import java.util.*;

public class Main {
	static int n, m;
	static int[][] arr;
	static boolean[][] visit;
	static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
	static int count = 0;
	static int maxSize = 0;

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
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		visit = new boolean[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j] == 1 && !visit[i][j]) {
					count++;
					check(i, j);
				}
			}
		}

		System.out.println(count);
		System.out.println(maxSize);
	}

	static void check(int y, int x) {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(y, x));
		visit[y][x] = true;
		int size = 1;
		while (!queue.isEmpty()) {
			Point now = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nextY = now.y + dy[i];
				int nextX = now.x + dx[i];
				if (0 <= nextY && nextY < n && 0 <= nextX && nextX < m && !visit[nextY][nextX]
					&& arr[nextY][nextX] == 1) {
					visit[nextY][nextX] = true;
					queue.add(new Point(nextY, nextX));
					size++;
				}
			}
		}

		maxSize = Math.max(maxSize, size);
	}
}
