import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static char[][] board;
	static boolean[][] visit;
	static int[][] cycleBoard;
	static HashMap<Character, int[]> dirMap;
	static List<Point> cycleList = new ArrayList<>();
	static int cycleIdx = 1;

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
		board = new char[N][M];
		visit = new boolean[N][M];
		cycleBoard = new int[N][M];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				board[i][j] = line.charAt(j);
			}
		}

		setDir();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visit[i][j] && cycleBoard[i][j] == 0) {
					cycleList.clear();
					findSafeZone(i, j);
				}
			}
		}

		System.out.println(cycleIdx - 1);
	}

	static void setDir() {
		dirMap = new HashMap<>();
		dirMap.put('U', new int[] {-1, 0});
		dirMap.put('R', new int[] {0, 1});
		dirMap.put('D', new int[] {1, 0});
		dirMap.put('L', new int[] {0, -1});
	}

	static void findSafeZone(int y, int x) {
		if (visit[y][x]) {
			if (cycleBoard[y][x] == 0) {
				for (Point point : cycleList) {
					cycleBoard[point.y][point.x] = cycleIdx;
				}
				cycleIdx++;
			} else {
				for (Point point : cycleList) {
					cycleBoard[point.y][point.x] = cycleBoard[y][x];
				}
			}
			return;
		}

		cycleList.add(new Point(y, x));

		visit[y][x] = true;
		int nextY = y + dirMap.get(board[y][x])[0];
		int nextX = x + dirMap.get(board[y][x])[1];
		findSafeZone(nextY, nextX);
	}
}
