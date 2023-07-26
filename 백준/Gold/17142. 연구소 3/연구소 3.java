import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] board;
	static boolean[][] visit;
	static ArrayList<Point> virusList = new ArrayList<>();
	static boolean[] selected;
	static int emptyCount = 0;
	static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
	static int answer = Integer.MAX_VALUE;

	static class Point {
		int y, x, time;

		public Point(int y, int x, int time) {
			this.y = y;
			this.x = x;
			this.time = time;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				if (num == 2) {
					virusList.add(new Point(i, j, 0));
				} else if (num == 0) {
					emptyCount++;
				}
				board[i][j] = num;
			}
		}
		if (emptyCount == 0) {
			System.out.println(0);
			System.exit(0);
		}

		selected = new boolean[virusList.size()];
		dfs(0, 0);

		if (answer == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(answer);
		}
	}

	static void dfs(int index, int count) {
		if (count == M) {
			bfs();
			return;
		}
		for (int i = index; i < virusList.size(); i++) {
			if (!selected[i]) {
				selected[i] = true;
				dfs(i, count + 1);
				selected[i] = false;
			}
		}
	}

	static void bfs() {
		Queue<Point> queue = new LinkedList<>();
		visit = new boolean[N][N];
		for (int i = 0; i < selected.length; i++) {
			if (selected[i]) {
				Point pick = virusList.get(i);
				visit[pick.y][pick.x] = true;
				queue.add(pick);
			}
		}

		int count = emptyCount;
		while (!queue.isEmpty()) {
			Point now = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nextY = now.y + dy[i];
				int nextX = now.x + dx[i];
				int nextT = now.time + 1;
				if (0 <= nextY && nextY < N && 0 <= nextX && nextX < N && !visit[nextY][nextX]
					&& board[nextY][nextX] != 1) {
					if (board[nextY][nextX] == 0) {
						count--;
					}
					if (count == 0) {
						answer = Math.min(answer, nextT);
						return;
					}
					visit[nextY][nextX] = true;
					queue.add(new Point(nextY, nextX, nextT));
				}
			}
		}
	}
}
