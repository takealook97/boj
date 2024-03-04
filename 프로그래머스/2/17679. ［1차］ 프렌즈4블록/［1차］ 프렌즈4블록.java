import java.util.*;

class Solution {
    static int N, M;
	static int[][] board;
	static int[] dy = {0, 0, 1, 1}, dx = {0, 1, 0, 1};
	static int[] tempArr = new int[4];
	static Set<Point> set = new HashSet<>();
	static Queue<Integer> queue = new ArrayDeque<>();
	static boolean isFinished = false;

	static final int EMPTY = 0;

	static class Point {
		int y, x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}

		@Override
		public boolean equals(Object o) {
			Point point = (Point) o;
			return this.y == point.y && this.x == point.x;
		}

		@Override
		public int hashCode() {
			return Objects.hash(y, x);
		}
	}

	public int solution(int m, int n, String[] b) {
		N = m;
		M = n;
		board = new int[N][M];

		for(int i = 0; i < N; i++) {
			String line = b[i];
			for(int j = 0; j < M; j++) {
				board[i][j] = line.charAt(j) - 'A' + 1;
			}
		}

		while(!isFinished) {
			removeBlocks();
		}

		return getRemoveCount();
	}

	static void removeBlocks() {
		set.clear();

		boolean check = false;
		for(int y = 0; y < N; y++) {
			out:
			for(int x = 0; x < M; x++) {
				// 범위 검사
				for(int i = 0; i < 4; i++) {
					int nextY = y + dy[i];
					int nextX = x + dx[i];
					if(!isInRange(nextY, nextX)) {
						continue out;
					}
					tempArr[i] = board[nextY][nextX];
				}

				// 동일 검사
				for(int i = 0; i < 3; i++) {
					if(tempArr[i] != tempArr[i + 1] || tempArr[i] == EMPTY) {
						continue out;
					}
				}

				// 통과
				check = true;
				for(int i = 0; i < 4; i++) {
					set.add(new Point(y + dy[i], x + dx[i]));
				}
			}
		}

		if(!check) {
			isFinished = true;
			return;
		}

		// 삭제
		for(Point point : set) {
			board[point.y][point.x] = 0;
		}

		setBlocksDown();// 내리기
	}

	static boolean isInRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}

	static void setBlocksDown() {
		for(int j = 0; j < M; j++) {
			// save
			queue.clear();
			for(int i = N - 1; i >= 0; i--) {
				if(board[i][j] != EMPTY) {
					queue.add(board[i][j]);
				}
			}

			// paste
			int idx = N - 1;
			while(!queue.isEmpty()) {
				board[idx--][j] = queue.poll();
			}
			while(idx >= 0) {
				board[idx--][j] = EMPTY;
			}
		}
	}

	static int getRemoveCount() {
		int count = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(board[i][j] == 0) {
					count++;
				}
			}
		}

		return count;
	}
}
