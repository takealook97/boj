import java.io.*;
import java.util.*;

public class Main {
	static int R, C;
	static char[][] inputInfo;
	static int[][] board;
	static boolean[][] visit;
	static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, 1, -1};
	static Point startPoint, endPoint;
	static Queue<Point> queue = new LinkedList<>();
	static int answer = Integer.MAX_VALUE;
	static final String IMPOSSIBLE = "KAKTUS";

	static final char WATER = '*', ROCK = 'X', START = 'S', END = 'D';

	static class Point implements Comparable<Point> {
		int y, x, time;

		public Point(int y, int x, int time) {
			this.y = y;
			this.x = x;
			this.time = time;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			Point point = (Point)o;
			return y == point.y && x == point.x;
		}

		@Override
		public int hashCode() {
			return Objects.hash(y, x);
		}

		@Override
		public int compareTo(Point o) {
			return this.time - o.time;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		inputInfo = new char[R][C];
		board = new int[R][C];
		visit = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				inputInfo[i][j] = line.charAt(j);
				if (inputInfo[i][j] == ROCK) {
					board[i][j] = -1;
					visit[i][j] = true;
				} else if (inputInfo[i][j] == WATER) {
					queue.add(new Point(i, j, 0));
					visit[i][j] = true;
				} else if (inputInfo[i][j] == START) {
					board[i][j] = -2;
					startPoint = new Point(i, j, 0);
					visit[i][j] = true;
				} else if (inputInfo[i][j] == END) {
					board[i][j] = -3;
					endPoint = new Point(i, j, 0);
				}
			}
		}

		setBoard();
		findPath();

		System.out.println(answer == Integer.MAX_VALUE ? IMPOSSIBLE : answer);
	}

	static void setBoard() {
		while (!queue.isEmpty()) {
			Point now = queue.poll();
			if (board[now.y][now.x] != 0) {
				continue;
			}
			board[now.y][now.x] = now.time;
			for (int i = 0; i < 4; i++) {
				int nextY = now.y + dy[i];
				int nextX = now.x + dx[i];
				if (isPossible(nextY, nextX)) {
					queue.add(new Point(nextY, nextX, now.time + 1));
				}
			}
		}
	}

	static void findPath() {
		queue = new LinkedList<>();
		queue.add(startPoint);
		while (!queue.isEmpty()) {
			Point now = queue.poll();
			if (now.equals(endPoint)) {
				answer = now.time;
				return;
			}

			for (int i = 0; i < 4; i++) {
				int nextY = now.y + dy[i];
				int nextX = now.x + dx[i];
				if (isPossible(nextY, nextX)) {
					if (board[nextY][nextX] == -3 || board[nextY][nextX] == 0 || (now.time + 1 < board[nextY][nextX])) {
						queue.add(new Point(nextY, nextX, now.time + 1));
						visit[nextY][nextX] = true;
					}
				}
			}
		}
	}

	static boolean isPossible(int y, int x) {
		return 0 <= y && y < R && 0 <= x && x < C && !visit[y][x];
	}
}
