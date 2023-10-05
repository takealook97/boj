import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] board, costBoard;
	static boolean[][] visit;
	static int[] dy = { -1, 1, 0, 0 }, dx = { 0, 0, -1, 1 };

	static final int INF = 987654321;

	static class Point implements Comparable<Point> {
		int y, x, cost;

		public Point(int y, int x, int cost) {
			this.y = y;
			this.x = x;
			this.cost = cost;
		}

		@Override
		public int compareTo(Point o) {
			return this.cost - o.cost;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int index = 1;
		while (true) {
			N = Integer.parseInt(br.readLine());
			if (N == 0) {
				break;
			}
			board = new int[N][N];
			costBoard = new int[N][N];
			visit = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				Arrays.fill(costBoard[i], INF);
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			findMin();

			sb.append("Problem ").append(index++).append(": ").append(costBoard[N - 1][N - 1]).append("\n");
		}
		System.out.print(sb);
	}

	static void findMin() {
		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.add(new Point(0, 0, board[0][0]));
//		costBoard[0][0] = board[0][0];
		visit[0][0] = true;
		while (!pq.isEmpty()) {
			Point now = pq.poll();

			for (int i = 0; i < 4; i++) {
				int nextY = now.y + dy[i];
				int nextX = now.x + dx[i];
				if (isMovable(nextY, nextX)) {
					if (costBoard[nextY][nextX] > board[nextY][nextX] + now.cost) {
						visit[nextY][nextX] = true;
						costBoard[nextY][nextX] = board[nextY][nextX] + now.cost;
						pq.add(new Point(nextY, nextX, costBoard[nextY][nextX]));
					}
				}
			}
		}
	}

	static boolean isMovable(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N && !visit[y][x];
	}
}
