import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] arr;
	static boolean[][] visit;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static int maxHeight = 0;
	static int result = 1;

	static class Point {
		int y, x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				maxHeight = Math.max(arr[i][j], maxHeight);
			}
		}
		for (int k = 0; k < maxHeight; k++) {
			int count = 0;
			visit = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visit[i][j] && arr[i][j] > k) {
						bfs(i, j, k);
						count++;
					}
				}
			}
			result = Math.max(result, count);
		}
		System.out.println(result);

	}

	static void bfs(int y, int x, int height) {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(y, x));
		while (!queue.isEmpty()) {
			Point now = queue.poll();
			int nowY = now.y;
			int nowX = now.x;
			for (int i = 0; i < 4; i++) {
				int nextY = nowY + dy[i];
				int nextX = nowX + dx[i];
				if (0 <= nextY && nextY < N && 0 <= nextX && nextX < N && !visit[nextY][nextX]
					&& arr[nextY][nextX] > height) {
					visit[nextY][nextX] = true;
					queue.add(new Point(nextY, nextX));
				}
			}
		}
	}
}
