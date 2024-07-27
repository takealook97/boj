import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N, M;
	static Box[][] board;
	static int[][] idxArr;
	static boolean[][] visited;
	static int[] dy = {1, 0, -1, 0}, dx = {0, 1, 0, -1}; // 하우상좌
	static int idx = 0;
	static Map<Integer, Integer> map = new HashMap<>();
	static Queue<Point> queue = new ArrayDeque<>();
	static int countSum = 0, maxSize = 0, maxSumSize = 0;

	static class Box {
		boolean[] isWall;

		public Box() {
			this.isWall = new boolean[4];
		}
	}

	static class Point {
		int y, x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		board = new Box[N][M];
		visited = new boolean[N][M];
		idxArr = new int[N][M];
		setBoard();
		countRoom();
		setAnswer();

		System.out.println(countSum + "\n" + maxSize + "\n" + maxSumSize);
	}

	static void setBoard() throws IOException {
		StringBuilder sb;
		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < M; x++) {
				String info = Integer.toBinaryString(Integer.parseInt(st.nextToken()));
				sb = new StringBuilder();
				int temp = 4 - info.length();
				while (temp-- > 0) {
					sb.append(0);
				}
				sb.append(info);
				info = sb.toString();

				Box box = new Box();
				for (int i = 0; i < 4; i++) {
					if (info.charAt(i) == '1') {
						box.isWall[i] = true;
					}
				}

				board[y][x] = box;
			}
		}
	}

	static void countRoom() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j]) {
					paintRoom(i, j);
					idx++;
				}
			}
		}
	}

	static void paintRoom(int startY, int startX) {
		queue.clear();
		queue.add(new Point(startY, startX));
		visited[startY][startX] = true;

		int count = 0;

		while (!queue.isEmpty()) {
			Point now = queue.poll();
			idxArr[now.y][now.x] = idx;
			count++;

			for (int i = 0; i < 4; i++) {
				if (board[now.y][now.x].isWall[i]) {
					continue;
				}

				int nextY = now.y + dy[i];
				int nextX = now.x + dx[i];
				if (isInRange(nextY, nextX) && !visited[nextY][nextX]) {
					visited[nextY][nextX] = true;
					queue.add(new Point(nextY, nextX));
				}
			}
		}

		map.put(idx, count);
	}

	static void setAnswer() {
		countSum = idx;
		for (int i : map.values()) {
			maxSize = Math.max(maxSize, i);
		}

		for (int nowY = 0; nowY < N; nowY++) {
			for (int nowX = 0; nowX < M; nowX++) {
				for (int i = 0; i < 4; i++) {
					int nextY = nowY + dy[i];
					int nextX = nowX + dx[i];
					if (isInRange(nextY, nextX) && idxArr[nowY][nowX] != idxArr[nextY][nextX]) {
						int tempSum = map.get(idxArr[nowY][nowX]) + map.get(idxArr[nextY][nextX]);
						maxSumSize = Math.max(maxSumSize, tempSum);
					}
				}
			}
		}
	}

	static boolean isInRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}
}
