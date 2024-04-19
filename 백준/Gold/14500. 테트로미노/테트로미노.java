import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] board;
	static boolean[][] visited;
	static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
	static ArrayList<Point> list = new ArrayList<>();
	static int answer = 0;

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
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited[i][j] = true;
				dfs(i, j, board[i][j], 1);
				visited[i][j] = false;

				bfs(i, j);
			}
		}

		System.out.println(answer);
	}

	static void dfs(int y, int x, int sum, int depth) {
		if (depth >= 4) {
			answer = Math.max(answer, sum);
			return;
		}

		for (int i = 0; i < 4; i++) {
			int nextY = y + dy[i];
			int nextX = x + dx[i];
			if (isPossible(nextY, nextX)) {
				visited[nextY][nextX] = true;
				dfs(nextY, nextX, sum + board[nextY][nextX], depth + 1);
				visited[nextY][nextX] = false;
			}
		}
	}

	static void bfs(int y, int x) {
		int sum = board[y][x];
		list.clear();

		for (int i = 0; i < 4; i++) {
			int nextY = y + dy[i];
			int nextX = x + dx[i];
			if (isPossible(nextY, nextX)) {
				list.add(new Point(nextY, nextX));
			}
		}

		if (list.size() == 3) {
			for (Point point : list) {
				sum += (board[point.y][point.x]);
			}
			answer = Math.max(answer, sum);
		} else if (list.size() == 4) {
			for (Point point : list) {
				sum += (board[point.y][point.x]);
			}

			for (Point point : list) {
				answer = Math.max(answer, sum - board[point.y][point.x]);
			}
		}
	}

	static boolean isPossible(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M && !visited[y][x];
	}
}
