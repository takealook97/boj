import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, y, x;
	static char[][] arr;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static int count = 0;

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
		arr = new char[N][M];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = line.charAt(j);
				if (arr[i][j] == 'I') {
					y = i;
					x = j;
				}
			}
		}

		bfs();
		if (count != 0) {
			System.out.println(count);
		} else {
			System.out.println("TT");
		}
	}

	static void bfs() {
		boolean[][] visit = new boolean[N][M];
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(y, x));
		visit[y][x] = true;
		while (!queue.isEmpty()) {
			Point now = queue.poll();
			int nowY = now.y;
			int nowX = now.x;
			if (arr[nowY][nowX] == 'P') {
				count++;
			}
			for (int i = 0; i < 4; i++) {
				int nextY = nowY + dy[i];
				int nextX = nowX + dx[i];
				if (0 <= nextY && nextY < N && 0 <= nextX && nextX < M && !visit[nextY][nextX]) {
					if (arr[nextY][nextX] != 'X') {
						visit[nextY][nextX] = true;
						queue.add(new Point(nextY, nextX));
					}
				}
			}
		}
	}
}
