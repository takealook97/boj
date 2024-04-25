import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int R, C;
	static char[][] board;
	static boolean[][] visited;
	static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
	static ArrayList<Point> sheepList = new ArrayList<>();
	static int answer = 0;

	static final char EMPTY = '.', SHEEP = 'S', WOLF = 'W', WALL = 'D';

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
		board = new char[R][C];
		visited = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				board[i][j] = line.charAt(j);
				if (board[i][j] == SHEEP) {
					sheepList.add(new Point(i, j));
				}
			}
		}

		if (!bfs()) {
			System.out.println(0);
			return;
		}

		StringBuilder sb = new StringBuilder();
		sb.append(1).append("\n");
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				sb.append(board[i][j]);
			}
			sb.append("\n");
		}

		System.out.print(sb);
	}

	static boolean bfs() {
		for (Point sheep : sheepList) {
			for (int i = 0; i < 4; i++) {
				int nextY = sheep.y + dy[i];
				int nextX = sheep.x + dx[i];

				if (isInRange(nextY, nextX)) {
					if (board[nextY][nextX] == WOLF) {
						return false;
					} else if (board[nextY][nextX] == EMPTY) {
						board[nextY][nextX] = WALL;
					}
				}
			}
		}
		return true;
	}

	static boolean isInRange(int y, int x) {
		return 0 <= y && y < R && 0 <= x && x < C;
	}
}
