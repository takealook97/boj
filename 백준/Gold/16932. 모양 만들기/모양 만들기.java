import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] board;
	static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
	static Map<Integer, Integer> countMap = new HashMap<>();
	static Queue<Point> queue = new ArrayDeque<>();
	static Set<Integer> set = new HashSet<>();
	static int idx = 1, answer = 0;

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
		board = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] == 1) {
					countBoard(i, j);
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] == 0) {
					update(i, j);
				}
			}
		}

		System.out.println(answer);
	}

	static void countBoard(int startY, int startX) {
		idx++;
		queue.clear();
		queue.add(new Point(startY, startX));
		board[startY][startX] = idx;

		int count = 1;
		while (!queue.isEmpty()) {
			Point now = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nextY = now.y + dy[i];
				int nextX = now.x + dx[i];
				if (isInRange(nextY, nextX) && board[nextY][nextX] == 1) {
					board[nextY][nextX] = idx;
					count++;
					queue.add(new Point(nextY, nextX));
				}
			}
		}

		countMap.put(idx, count);
	}

	static void update(int y, int x) {
		set.clear();

		for (int i = 0; i < 4; i++) {
			int nextY = y + dy[i];
			int nextX = x + dx[i];
			if (isInRange(nextY, nextX) && board[nextY][nextX] > 1) {
				set.add(board[nextY][nextX]);
			}
		}

		int sum = 1;
		for (int target : set) {
			sum += countMap.get(target);
		}

		answer = Math.max(answer, sum);
	}

	static boolean isInRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}
}
