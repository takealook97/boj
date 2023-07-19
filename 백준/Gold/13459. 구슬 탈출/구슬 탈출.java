import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] board;
	static boolean[][] visit;
	static int destinationY = 0, destinationX = 0;
	static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
	static int result = Integer.MAX_VALUE;

	static class Marble {
		int ry, rx, by, bx, prevDir;

		public Marble(int ry, int rx, int by, int bx, int prevDir) {
			this.ry = ry;
			this.rx = rx;
			this.by = by;
			this.bx = bx;
			this.prevDir = prevDir;// 0 상, 1 하, 3 좌, 4 우
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
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()) - 2;
		M = Integer.parseInt(st.nextToken()) - 2;
		board = new int[N][M];
		visit = new boolean[N][M];

		int ry = 0, rx = 0, by = 0, bx = 0;
		for (int i = 0; i < N + 2; i++) {
			String line = br.readLine();
			for (int j = 0; j < M + 2; j++) {
				if (1 <= i && i <= N && 1 <= j && j <= M) {
					char point = line.charAt(j);
					int num = 0;
					switch (point) {
						case '#':
							num = 1;
							visit[i - 1][j - 1] = true;
							break;
						case 'O':
							num = 2;
							destinationY = i - 1;
							destinationX = j - 1;
							break;
						case 'R':
							ry = i - 1;
							rx = j - 1;
							visit[ry][rx] = true;
							break;
						case 'B':
							by = i - 1;
							bx = j - 1;
							visit[by][bx] = true;
							break;
					}
					board[i - 1][j - 1] = num;
				}
			}
		}

		dfs(new Marble(ry, rx, by, bx, -1), 0);//초기값 != 0,1,2,3

		if (result > 10) {
			System.out.println(0);
		} else {
			System.out.println(1);
		}
	}

	static void dfs(Marble now, int depth) {
		if (depth > 10) {
			return;
		}
		// System.out.println("[" + now.ry + "," + now.rx + "],[" + now.by + "," + now.bx + "] " + "dir = " + now.prevDir + " " + "depth = " + depth);
		if (now.by == destinationY && now.bx == destinationX) {//파란공이 들어갈경우
			return;
		} else if (now.ry == destinationY && now.rx == destinationX) {//빨간공만 들어갈경우
			result = Math.min(result, depth);
			return;
		}
		for (int i = 0; i < 4; i++) {
			boolean checkPrev = now.prevDir < 2;
			boolean checkNext = i < 2;// 상~하 or 좌~우 -> 불필요
			if (checkPrev != checkNext || now.prevDir == -1) {//왔던방향 or 상하/좌우 왔다갔다 방지
				Point updatedR, updatedB; // [0] = nextY, [1] = nextX
				if (isRedFirst(new Marble(now.ry, now.rx, now.by, now.bx, i))) {
					updatedR = update(now.ry, now.rx, i);
					updatedB = update(now.by, now.bx, i);
				} else {
					updatedB = update(now.by, now.bx, i);
					updatedR = update(now.ry, now.rx, i);
				}

				if (updatedR == null && updatedB == null) {//둘다 변화 없을 경우
					continue;
				} else if (updatedR == null) {
					updatedR = new Point(now.ry, now.rx);
				} else if (updatedB == null) {
					updatedB = new Point(now.by, now.bx);
				}
				dfs(new Marble(updatedR.y, updatedR.x, updatedB.y, updatedB.x, i), depth + 1);

				Point prevR = new Point(now.ry, now.rx);
				Point prevB = new Point(now.by, now.bx);
				if (updatedR.y != prevR.y || updatedR.x != prevR.x) {
					undoVisitCheck(prevR.y, prevR.x, updatedR.y, updatedR.x);
				}
				if (updatedB.y != prevB.y || updatedB.x != prevB.x) {
					undoVisitCheck(prevB.y, prevB.x, updatedB.y, updatedB.x);
				}
			}
		}
	}

	static boolean isRedFirst(Marble marble) { // 0 상, 1 하, 3 좌, 4 우
		if (marble.prevDir == 0) {
			return marble.ry <= marble.by;
		} else if (marble.prevDir == 1) {
			return marble.ry >= marble.by;
		} else if (marble.prevDir == 2) {
			return marble.rx <= marble.bx;
		} else {
			return marble.rx >= marble.bx;
		}
	}

	static Point update(int y, int x, int dir) {
		Point next = move(y, x, dir);//이동 후 좌표
		int nextY = next.y;
		int nextX = next.x;
		if (y == nextY && x == nextX) {//변화가 없을 경우 null 리턴
			return null;
		}

		if (nextY == destinationY && nextX == destinationX) {//도착
			visit[y][x] = false; //이전 좌표 visit 해제 -> 맵에서 삭제
		} else {
			visit[y][x] = false;
			visit[nextY][nextX] = true;//visit 갱신
		}
		return next;
	}

	static Point move(int y, int x, int dir) {
		while (isPossible(y + dy[dir], x + dx[dir])) {
			y += dy[dir];
			x += dx[dir];
			if (y == destinationY && x == destinationX) {
				return new Point(y, x);//골인 할 경우
			}
		}
		return new Point(y, x);
	}

	static boolean isPossible(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M && !visit[y][x];
	}

	static void undoVisitCheck(int prevY, int prevX, int nextY, int nextX) {
		visit[prevY][prevX] = true;
		visit[nextY][nextX] = false;
	}
}
