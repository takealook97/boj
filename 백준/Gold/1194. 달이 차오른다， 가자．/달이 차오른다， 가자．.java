import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static char[][] board;
	static boolean[][][] visit;
	static int[] dy = {-1, 0, 1, 0}, dx = {0, -1, 0, 1};
	static int answer;

	static final char PERSON = '0', DEST = '1', BLANK = '.', WALL = '#';

	static class Point {
		int y, x, count;
		boolean[] isKeyExist;

		Point(int y, int x, int count, boolean[] isKeyExist) {
			this.y = y;
			this.x = x;
			this.count = count;
			this.isKeyExist = isKeyExist.clone();
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new char[N][M];
		visit = new boolean[N][M][(int)Math.pow(2, 6)];
		Point startPoint = null;

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				board[i][j] = line.charAt(j);
				if (board[i][j] == PERSON) {
					startPoint = new Point(i, j, 0, new boolean[6]);
				}
			}
		}

		bfs(startPoint);
        
		System.out.println(answer);
	}

	static void bfs(Point startPoint) {
		Queue<Point> queue = new LinkedList<>();
		queue.add(startPoint);

		while (!queue.isEmpty()) {
			Point now = queue.poll();
			if (board[now.y][now.x] == DEST) {
				answer = now.count;
				return;
			}
			for (int i = 0; i < 4; i++) {
				int nextY = now.y + dy[i];
				int nextX = now.x + dx[i];
				int nextIdx = getKeyIndex(now.isKeyExist);
				if (isMovable(nextY, nextX) && !visit[nextY][nextX][nextIdx]) {
					char nextBox = board[nextY][nextX];
					Point nextPoint = new Point(nextY, nextX, now.count + 1, now.isKeyExist);
					if ('a' <= nextBox && nextBox <= 'f') {
						nextPoint.isKeyExist[nextBox - 'a'] = true;
					}
					if (nextBox == BLANK || nextBox == PERSON || nextBox == DEST || ('a' <= nextBox
						&& nextBox <= 'f')
						|| ('A' <= nextBox && nextBox <= 'F' && nextPoint.isKeyExist[nextBox - 'A'])) {
						queue.add(nextPoint);
						visit[nextY][nextX][nextIdx] = true;
					}
				}
			}
		}
		answer = -1;
	}

	static boolean isMovable(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M && board[y][x] != WALL;
	}

	static int getKeyIndex(boolean[] keys) {
		int idx = 0;
		for (int i = 0; i < 6; i++) {
			if (keys[i]) {
				idx |= (1 << i);
			}
		}
		return idx;
	}
}
