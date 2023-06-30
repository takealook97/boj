import java.util.*;

class Solution {
	static int[][] arr;
	static int n, m, r, c, resultK;
	static int[] dy = {1, 0, 0, -1};
	static int[] dx = {0, -1, 1, 0}; // d l r u
	static String resultRoute;

	static class Node {
		int y, x;
		String route;

		public Node(int y, int x, String route) {
			this.y = y;
			this.x = x;
			this.route = route;
		}
	}

	public String solution(int n, int m, int x, int y, int r, int c, int k) {
		Solution.r = r - 1;
		Solution.c = c - 1;
		resultK = k;
		makeArr(n, m);
		bfs(x - 1, y - 1);
		if (resultRoute == null) {
			return "impossible";
		}
		return resultRoute;
	}

	static void makeArr(int n, int m) {
		Solution.n = n;
		Solution.m = m;
		arr = new int[n][m];
	}

	static void bfs(int x, int y) {
		Queue<Node> queue = new LinkedList<>();
		boolean[][][] visited = new boolean[n][m][resultK + 1];

		queue.offer(new Node(x, y, ""));
		visited[x][y][0] = true;

		while (!queue.isEmpty()) {
			Node now = queue.poll();
			int nowY = now.y;
			int nowX = now.x;
			String nowR = now.route;
			int depth = nowR.length();

			if (depth == resultK && nowY == r && nowX == c) {
				resultRoute = nowR;
				return;
			}
			if (depth >= resultK) {
				continue;
			}

			for (int i = 0; i < 4; i++) {
				int nextY = nowY + dy[i];
				int nextX = nowX + dx[i];

				if (0 <= nextY && nextY < n && 0 <= nextX && nextX < m && !visited[nextY][nextX][depth + 1]) {
					visited[nextY][nextX][depth + 1] = true;
					switch (i) {
						case 0 -> queue.offer(new Node(nextY, nextX, nowR + "d"));
						case 1 -> queue.offer(new Node(nextY, nextX, nowR + "l"));
						case 2 -> queue.offer(new Node(nextY, nextX, nowR + "r"));
						case 3 -> queue.offer(new Node(nextY, nextX, nowR + "u"));
					}
				}
			}
		}
	}
}
