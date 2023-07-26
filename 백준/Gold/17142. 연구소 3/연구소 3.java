import java.util.*;
import java.io.*;

class Main {
	static int N, M;
	static int[][] arr;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static List<Point> points = new ArrayList<>();
	static Point[] active;
	static int answer = Integer.MAX_VALUE;
	static int empty = 0;
	static class Point {
		int x, y, time;

		Point(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N][N];
		active = new Point[M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 0) {
					empty++;
				} else if (arr[i][j] == 2) {
					points.add(new Point(i, j, 0));
				}
			}
		}

		if (empty == 0) {
			System.out.println(0);
		} else {
			select(0, 0);
			System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
		}
	}

	static void select(int start, int selectCount) {
		if (selectCount == M) {
			bfs(empty);
			return;
		}
		for (int i = start; i < points.size(); i++) {
			active[selectCount] = points.get(i);
			select(i + 1, selectCount + 1);
		}
	}

	static void bfs(int emptySpace) {
		Queue<Point> queue = new LinkedList<>();
		boolean[][] visit = new boolean[N][N];
		for (int i = 0; i < M; i++) {
			Point point = active[i];
			visit[point.x][point.y] = true;
			queue.add(point);
		}
		while (!queue.isEmpty()) {
			Point point = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nx = point.x + dx[i];
				int ny = point.y + dy[i];
				if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
				if (visit[nx][ny] || arr[nx][ny] == 1) continue;
				if (arr[nx][ny] == 0) {
					emptySpace--;
				}
				if (emptySpace == 0) {
					answer = Math.min(answer, point.time + 1);
					return;
				}
				visit[nx][ny] = true;
				queue.add(new Point(nx, ny, point.time + 1));
			}
		}
	}
}
