import java.io.*;
import java.util.*;

public class Main {
	static int R, C, M;
	static int fishermanPos = -1;
	static Shark[][] board;
	static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, 1, -1};//상 하 우 좌
	static int sizeSum = 0;

	static class Shark {
		int speed, dir, size;

		public Shark(int speed, int dir, int size) {
			this.speed = speed;
			this.dir = dir;
			this.size = size;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new Shark[R][C];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken()) - 1;
			int x = Integer.parseInt(st.nextToken()) - 1;
			board[y][x] = new Shark(
				Integer.parseInt(st.nextToken()),
				Integer.parseInt(st.nextToken()) - 1,
				Integer.parseInt(st.nextToken())
			);
		}

		for (int i = 0; i < C; i++) {
			fishermanPos++;
			catchShark();
			moveSharks();
		}
		System.out.println(sizeSum);
	}

	static void catchShark() {
		for (int y = 0; y < R; y++) {
			if (board[y][fishermanPos] != null) {
				sizeSum += board[y][fishermanPos].size;
				board[y][fishermanPos] = null;
				return;
			}
		}
	}

	static void moveSharks() {
		Shark[][] updatedBoard = new Shark[R][C];
		for (int y = 0; y < R; y++) {
			for (int x = 0; x < C; x++) {
				if (board[y][x] != null) {
					Shark shark = board[y][x];
					int nextY = y + shark.speed * dy[shark.dir];
					int nextX = x + shark.speed * dx[shark.dir];

					while (nextY < 0 || nextY >= R || nextX < 0 || nextX >= C) {
						if (nextY < 0) {
							shark.dir = 1;
							nextY = -nextY;
						} else if (nextY >= R) {
							shark.dir = 0;
							nextY = 2 * R - 2 - nextY;
						}
						
						if (nextX < 0) {
							shark.dir = 2;
							nextX = -nextX;
						} else if (nextX >= C) {
							shark.dir = 3;
							nextX = 2 * C - 2 - nextX;
						}
					}

					if (updatedBoard[nextY][nextX] == null || updatedBoard[nextY][nextX].size < shark.size) {
						updatedBoard[nextY][nextX] = new Shark(shark.speed, shark.dir, shark.size);
					}
				}
			}
		}
		board = updatedBoard;
	}
}
