import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static char[][] normalBoard, blindBoard;
	static boolean[][] visitNormal, visitBlind;
	static int[] dy = { 1, 0, -1, 0 }, dx = { 0, -1, 0, 1 };
	static int countNormal = 0;
	static int countBlind = 0;

	static class Point {
		int y, x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}

		@Override
		public String toString() {
			return "Point [y=" + y + ", x=" + x + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		normalBoard = new char[N][N];
		blindBoard = new char[N][N];
		visitNormal = new boolean[N][N];
		visitBlind = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				char input = line.charAt(j);
				normalBoard[i][j] = input;
				if (input == 'G') {
					input = 'R';
				}
				blindBoard[i][j] = input;
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visitNormal[i][j]) {
					bfsNormal(i, j, normalBoard[i][j]);
					countNormal++;
				}
				if (!visitBlind[i][j]) {
					bfsBlind(i, j, blindBoard[i][j]);
					countBlind++;
				}
			}
		}
		System.out.println(countNormal + " " + countBlind);
	}

	static void bfsBlind(int y, int x, char color) {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(y, x));
		visitBlind[y][x] = true;

		while (!queue.isEmpty()) {
			Point now = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nextY = now.y + dy[i];
				int nextX = now.x + dx[i];
				if (isInRange(nextY, nextX) && !visitBlind[nextY][nextX] && blindBoard[nextY][nextX] == color) {
					queue.add(new Point(nextY, nextX));
					visitBlind[nextY][nextX] = true;
				}
			}
		}
	}

	static void bfsNormal(int y, int x, char color) {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(y, x));
		visitNormal[y][x] = true;

		while (!queue.isEmpty()) {
			Point now = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nextY = now.y + dy[i];
				int nextX = now.x + dx[i];
				if (isInRange(nextY, nextX) && !visitNormal[nextY][nextX] && normalBoard[nextY][nextX] == color) {
					queue.add(new Point(nextY, nextX));
					visitNormal[nextY][nextX] = true;
				}
			}
		}
	}

	static boolean isInRange(int nextY, int nextX) {
		return 0 <= nextY && nextY < N && 0 <= nextX && nextX < N;
	}
}
