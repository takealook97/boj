import java.io.*;
import java.util.*;

public class Main {
	static int t, h, w;
	static char[][] board;
	static boolean[][] visit;
	static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
	static ArrayList<Point> entries;
	static Queue<Point> queue;
	static List<Point> tempList = new ArrayList<>();
	static List<Point> removeList = new ArrayList<>();
	static int answer;

	static final char WALL = '*', EMPTY = '.', DOCUMENT = '$';
	static final String NONE = "0";

	static class Point {
		int y, x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while (t-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			board = new char[h][w];
			visit = new boolean[h][w];
			entries = new ArrayList<>();
			answer = 0;

			for (int i = 0; i < h; i++) {
				String line = br.readLine();
				for (int j = 0; j < w; j++) {
					board[i][j] = line.charAt(j);
					if (board[i][j] != WALL && (i == 0 || j == 0 || i == h - 1 || j == w - 1)) {
						entries.add(new Point(i, j));
					}
				}
			}

			String keys = br.readLine();
			if (!keys.equals(NONE)) {
				for (char key : keys.toCharArray()) {
					removeKeys(key);
				}
			}

			findDocuments();

			sb.append(answer).append("\n");
		}

		System.out.print(sb);
	}

	static void findDocuments() {
		tempList = new ArrayList<>();
		queue = new LinkedList<>();
		for (Point entry : entries) {
			queue.add(entry);
			visit[entry.y][entry.x] = true;
		}

		while (!queue.isEmpty()) {
			Point now = queue.poll();
			char status = board[now.y][now.x];

			if (isKey(status)) {
				removeKeys(status);
			} else if (isDoor(status)) {
				tempList.add(now);
				continue;
			} else if (status == DOCUMENT) {
				board[now.y][now.x] = EMPTY;
				answer++;
			}

			status = board[now.y][now.x];// update

			if (status == EMPTY || status == DOCUMENT) {
				for (int i = 0; i < 4; i++) {
					int nextY = now.y + dy[i];
					int nextX = now.x + dx[i];
					if (isPossible(nextY, nextX)) {
						visit[nextY][nextX] = true;
						queue.add(new Point(nextY, nextX));
					}
				}
			}
		}
	}

	static boolean isPossible(int y, int x) {
		return 0 <= y && y < h && 0 <= x && x < w && !visit[y][x] && board[y][x] != WALL;
	}

	static void removeKeys(char key) {
		// remove from board
		char door = Character.toUpperCase(key);
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (board[i][j] == key) {
					board[i][j] = EMPTY;
					updateTempList(door);
				}
			}
		}
		removeDoors(door);
	}

	static void removeDoors(char door) {
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (board[i][j] == door) {
					board[i][j] = EMPTY;
				}
			}
		}
	}

	static void updateTempList(char door) {
		if (!tempList.isEmpty()) {
			for (Point point : tempList) {
				if (board[point.y][point.x] == door) {
					queue.add(new Point(point.y, point.x));
					removeList.add(point);
					board[point.y][point.x] = EMPTY;
				}
			}
		}
		tempList.removeAll(removeList);
		removeList.clear();
	}

	static boolean isDoor(char input) {
		return Character.isUpperCase(input);
	}

	static boolean isKey(char input) {
		return Character.isLowerCase(input);
	}
}
