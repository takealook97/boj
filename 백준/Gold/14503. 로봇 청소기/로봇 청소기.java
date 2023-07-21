import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, y, x, d;
	static int[][] arr;
	static boolean[][] visit;
	static int[] dy = {-1, 0, 1, 0}, dx = {0, 1, 0, -1};// 북 동 남 서
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		y = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while (true) {
			if (!visit[y][x]) {
				visit[y][x] = true;
				answer++;
			}

			if (!isAdjacentExist()) {
				checkBack();
			} else {
				turnDir();
				checkFront();
			}
		}
	}

	static boolean isAdjacentExist() {
		for (int i = 0; i < 4; i++) {
			int nextY = y + dy[i];
			int nextX = x + dx[i];
			if (isInRange(nextY, nextX) && !visit[nextY][nextX] && arr[nextY][nextX] != 1) {
				return true;
			}
		}
		return false;
	}

	static void checkBack() {
		int nextY = y;
		int nextX = x;
		switch (d) {
			case 0 :
				nextY++;
				break;
			case 1 :
				nextX--;
				break;
			case 2 :
				nextY--;
				break;
			case 3 :
				nextX++;
				break;
		}
		if (isInRange(nextY, nextX) && arr[nextY][nextX] != 1) {
			y = nextY;
			x = nextX;
		} else {
			System.out.println(answer);
			System.exit(0);
		}
	}

	static void turnDir() {
		d--;
		if (d < 0) {
			d = 3;
		}
	}

	static void checkFront() {
		int nextY = y;
		int nextX = x;
		switch (d) {
			case 0 :
				nextY--;
				break;
			case 1 :
				nextX++;
				break;
			case 2 : 
				nextY++;
				break;
			case 3 :
				nextX--;
				break;
		}
		if (isInRange(nextY, nextX) && !visit[nextY][nextX] && arr[nextY][nextX] != 1) {
			y = nextY;
			x = nextX;
		}
	}

	static boolean isInRange(int nowY, int nowX) {
		return 0 <= nowY && nowY < N && 0 <= nowX && nowX < M;
	}
}
