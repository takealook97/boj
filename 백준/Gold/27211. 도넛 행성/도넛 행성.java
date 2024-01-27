import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] board;
	static boolean[][] visited;
	static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
	static int groupIdx = 2;
	static int answer = 0;

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
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				visited[i][j] = board[i][j] == 1;
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j] && board[i][j] == 0) {
					board[i][j] = groupIdx;
					getCount(new Point(i, j));
					answer++;
					groupIdx++;
				}
			}
		}

		System.out.println(answer);
	}

	static void getCount(Point start) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.add(start);
		visited[start.y][start.x] = true;
		while (!queue.isEmpty()) {
			Point now = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nextY = now.y + dy[i];
				int nextX = now.x + dx[i];

				if (!isInRange(nextY, nextX)) {
					if (nextY < 0) {
						nextY += N;
					} else if (nextY >= N) {
						nextY -= N;
					} else if (nextX < 0) {
						nextX += M;
					} else {
						nextX -= M;
					}
				}
				if (board[nextY][nextX] != 0) {
					continue;
				}
				if (!visited[nextY][nextX]) {
					visited[nextY][nextX] = true;
					queue.add(new Point(nextY, nextX));
					board[nextY][nextX] = groupIdx;
				}
			}
		}
	}

	static boolean isInRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}
}
