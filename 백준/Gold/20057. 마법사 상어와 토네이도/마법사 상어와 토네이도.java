import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] board;
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	static int[] dc = {1, 1, 2, 2};
	static int[][] moveY = {
		{1, 1, 0, 0, 0, 0, -1, -1, -2},
		{-1, 1, -2, -1, 1, 2, -1, 1, 0},
		{-1, -1, 0, 0, 0, 0, 1, 1, 2},
		{1, -1, 2, 1, -1, -2, 1, -1, 0}
	};
	static int[][] moveX = {
		{-1, 1, -2, -1, 1, 2, -1, 1, 0},
		{-1, -1, 0, 0, 0, 0, 1, 1, 2},
		{1, -1, 2, 1, -1, -2, 1, -1, 0},
		{1, 1, 0, 0, 0, 0, -1, -1, -2}
	};
	static int[] ratioArr = {1, 1, 2, 7, 7, 2, 10, 10, 5};
	static int answer = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		board = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		getSand(N / 2, N / 2);

		System.out.println(answer);
	}

	static void getSand(int x, int y) {
		int curX = x;
		int curY = y;

		while (true) {
			for (int d = 0; d < 4; d++) {
				for (int moveCount = 0; moveCount < dc[d]; moveCount++) {
					int nextX = curX + dx[d];
					int nextY = curY + dy[d];

					if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
						return;
					}

					int sand = board[nextX][nextY];
					board[nextX][nextY] = 0;
					int spreadTotal = 0;

					for (int spread = 0; spread < 9; spread++) {
						int sandX = nextX + moveX[d][spread];
						int sandY = nextY + moveY[d][spread];
						int spreadAmount = (sand * ratioArr[spread]) / 100;

						if (sandX < 0 || sandX >= N || sandY < 0 || sandY >= N) {
							answer += spreadAmount;
						} else {
							board[sandX][sandY] += spreadAmount;
						}
						spreadTotal += spreadAmount;
					}

					int ax = nextX + dx[d];
					int ay = nextY + dy[d];
					int aAmount = sand - spreadTotal;
					if (ax < 0 || ax >= N || ay < 0 || ay >= N) {
						answer += aAmount;
					} else {
						board[ax][ay] += aAmount;
					}

					curX = nextX;
					curY = nextY;
				}
			}

			for (int i = 0; i < 4; i++) {
				dc[i] += 2;
			}
		}
	}
}
