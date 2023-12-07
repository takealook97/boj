import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] board, answer;
	static PointInfo[][] spaceInfo;
	static boolean[][] visit;
	static Queue<Point> queue = new LinkedList<>();
	static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
	static int id = 0;

	static final int WALL = 1, SPACE = 0;

	static class Point {
		int y, x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	static class PointInfo {
		int id;
		int count;

		public PointInfo(int id, int count) {
			this.id = id;
			this.count = count;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		spaceInfo = new PointInfo[N][M];
		answer = new int[N][M];
		visit = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				board[i][j] = line.charAt(j) - '0';
			}
		}

		// set space count
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visit[i][j] && board[i][j] == SPACE) {
					setSpaceSize(i, j);
				}
			}
		}

		// set move count
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] == WALL) {
					answer[i][j] = getCount(i, j) % 10;
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(answer[i][j]);
			}
			sb.append("\n");
		}

		System.out.print(sb);
	}

	static void setSpaceSize(int y, int x) {
		int count = 1;
		List<Point> list = new ArrayList<>();
		visit[y][x] = true;
		queue.add(new Point(y, x));
		list.add(new Point(y, x));
		visit[y][x] = true;

		while (!queue.isEmpty()) {
			Point now = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nextY = now.y + dy[i];
				int nextX = now.x + dx[i];
				if (isPossible(nextY, nextX) && !visit[nextY][nextX]) {
					count++;
					visit[nextY][nextX] = true;
					queue.add(new Point(nextY, nextX));
					list.add(new Point(nextY, nextX));
				}
			}
		}

		for (Point point : list) {
			spaceInfo[point.y][point.x] = new PointInfo(id, count);
		}
		id++;
	}

	static int getCount(int y, int x) {
		int count = 1;
		List<Integer> idList = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			int nextY = y + dy[i];
			int nextX = x + dx[i];
			if (isPossible(nextY, nextX) && !idList.contains(spaceInfo[nextY][nextX].id)) {
				idList.add(spaceInfo[nextY][nextX].id);
				count += spaceInfo[nextY][nextX].count;
			}
		}
		return count;
	}

	static boolean isPossible(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M && board[y][x] == SPACE;
	}
}
