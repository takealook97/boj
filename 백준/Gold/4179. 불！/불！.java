import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int R, C;
	static char[][] board;
	static int[][] fireArr;
	static int[][] moveArr;
	static boolean[][] visited;
	static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
	static PriorityQueue<Point> pq = new PriorityQueue<>();
	static int answer = Integer.MAX_VALUE;

	static class Point implements Comparable<Point> {
		int y, x, move;

		public Point(int y, int x, int move) {
			this.y = y;
			this.x = x;
			this.move = move;
		}

		@Override
		public int compareTo(Point o) {
			return this.move - o.move;
		}
	}

	static final char WALL = '#', EMPTY = '.', START = 'J', FIRE = 'F';

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new char[R][C];
		fireArr = new int[R][C];
		moveArr = new int[R][C];
		visited = new boolean[R][C];

		Point start = null;
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				board[i][j] = line.charAt(j);
				if (board[i][j] == FIRE) {
					fireArr[i][j] = 1;
					pq.add(new Point(i, j, 1));
				} else if (board[i][j] == START) {
					moveArr[i][j] = 1;
					start = new Point(i, j, 1);
					visited[i][j] = true;
				}
			}
		}

		simulateFire();
		simulateMove(start);
		checkEscaped();

		System.out.println(answer < Integer.MAX_VALUE ? answer : "IMPOSSIBLE");
	}

	static void simulateFire() {
		while (!pq.isEmpty()) {
			Point now = pq.poll();

			for (int i = 0; i < 4; i++) {
				int nextY = now.y + dy[i];
				int nextX = now.x + dx[i];
				int nextMove = now.move + 1;
				if (isInRange(nextY, nextX) && fireArr[nextY][nextX] == 0 &&
					(board[nextY][nextX] == EMPTY || board[nextY][nextX] == START)) {
					pq.add(new Point(nextY, nextX, nextMove));
					fireArr[nextY][nextX] = nextMove;
				}
			}
		}
	}

	static void simulateMove(Point start) {
		if (start == null) {
			return;
		}

		pq.clear();
		pq.add(start);

		while (!pq.isEmpty()) {
			Point now = pq.poll();

			for (int i = 0; i < 4; i++) {
				int nextY = now.y + dy[i];
				int nextX = now.x + dx[i];
				int nextMove = now.move + 1;
				if (isInRange(nextY, nextX) && board[nextY][nextX] == EMPTY && !visited[nextY][nextX] &&
					(fireArr[nextY][nextX] > nextMove || fireArr[nextY][nextX] == 0)) {
					visited[nextY][nextX] = true;
					pq.add(new Point(nextY, nextX, nextMove));
					moveArr[nextY][nextX] = nextMove;
				}
			}
		}
	}

	static boolean isInRange(int y, int x) {
		return 0 <= y && y < R && 0 <= x && x < C;
	}

	static void checkEscaped() {
		for (int i = 0; i < R; i++) {
			if (moveArr[i][0] > 0) {
				answer = Math.min(answer, moveArr[i][0]);
			}
			if (moveArr[i][C - 1] > 0) {
				answer = Math.min(answer, moveArr[i][C - 1]);
			}
		}

		for (int i = 0; i < C; i++) {
			if (moveArr[0][i] > 0) {
				answer = Math.min(answer, moveArr[0][i]);
			}

			if (moveArr[R - 1][i] > 0) {
				answer = Math.min(answer, moveArr[R - 1][i]);
			}
		}
	}
}
