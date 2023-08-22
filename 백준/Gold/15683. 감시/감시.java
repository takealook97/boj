import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static int[][] board;
	static int[][] checkArr;
	static ArrayList<int[]>[] directionList;
	static ArrayList<Point> cctvPosList = new ArrayList<>();
	static boolean[] visit;
	static int count = 0;
	static int[] dy = { -1, 1, 0, 0 }, dx = { 0, 0, -1, 1 };

	static final int UP = 0;
	static final int DOWN = 1;
	static final int LEFT = 2;
	static final int RIGHT = 3;
	static final int CCTV_COUNT = 5;

	static int answer = Integer.MAX_VALUE;

	static class Point {
		int y, x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	static class BoardConstant {
		static final int SPACE = 0;
		static final int CCTV_FIRST = 1;
		static final int CCTV_SECOND = 2;
		static final int CCTV_THIRD = 3;
		static final int CCTV_FOURTH = 4;
		static final int CCTV_FIFTH = 5;
		static final int WALL = 6;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		checkArr = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] != BoardConstant.SPACE && board[i][j] != BoardConstant.WALL) {// cctv인경우
					count++;
					cctvPosList.add(new Point(i, j));
					checkArr[i][j]++;
				} else if (board[i][j] == BoardConstant.WALL) {
					checkArr[i][j]++;
				}
			}
		}
		visit = new boolean[count];
		setDirectionList();
		dfs(0);
		System.out.println(answer);
	}

	static void setDirectionList() {
		directionList = new ArrayList[CCTV_COUNT + 1];
		for (int i = 1; i <= CCTV_COUNT; i++) {
			directionList[i] = new ArrayList<>();
		}
		for (int i = 0; i < 4; i++) {
			directionList[BoardConstant.CCTV_FIRST].add(new int[] { i });
		}
		directionList[BoardConstant.CCTV_SECOND].add(new int[] { UP, DOWN });
		directionList[BoardConstant.CCTV_SECOND].add(new int[] { LEFT, RIGHT });

		directionList[BoardConstant.CCTV_THIRD].add(new int[] { UP, LEFT });
		directionList[BoardConstant.CCTV_THIRD].add(new int[] { UP, RIGHT });
		directionList[BoardConstant.CCTV_THIRD].add(new int[] { DOWN, LEFT });
		directionList[BoardConstant.CCTV_THIRD].add(new int[] { DOWN, RIGHT });

		directionList[BoardConstant.CCTV_FOURTH].add(new int[] { UP, DOWN, LEFT });
		directionList[BoardConstant.CCTV_FOURTH].add(new int[] { UP, DOWN, RIGHT });
		directionList[BoardConstant.CCTV_FOURTH].add(new int[] { UP, LEFT, RIGHT });
		directionList[BoardConstant.CCTV_FOURTH].add(new int[] { DOWN, LEFT, RIGHT });

		directionList[BoardConstant.CCTV_FIFTH].add(new int[] { UP, DOWN, LEFT, RIGHT });
	}

	static void dfs(int idx) {
		if (idx == count) {
			answer = Math.min(answer, getBlindSpots());
			return;
		}
		Point now = cctvPosList.get(idx);
		int nowCctvIdx = board[now.y][now.x];
		for (int dirArrIdx = 0; dirArrIdx < directionList[nowCctvIdx].size(); dirArrIdx++) {
			visit[idx] = true;
			changeViewSpots(now.y, now.x, dirArrIdx, true);
			dfs(idx + 1);
			changeViewSpots(now.y, now.x, dirArrIdx, false);
			visit[idx] = false;
		}
	}

	static void changeViewSpots(int y, int x, int dirArrIdx, boolean isOn) {
		int cctvNum = board[y][x];
		int curY = y;
		int curX = x;
		int[] dirArr = directionList[cctvNum].get(dirArrIdx);
		for (int dir : dirArr) {
			curY += dy[dir];
			curX += dx[dir];
			while (isInRange(curY, curX) && board[curY][curX] != BoardConstant.WALL) {
				if (isOn) {
					checkArr[curY][curX]++;
				} else {
					checkArr[curY][curX]--;
				}
				curY += dy[dir];
				curX += dx[dir];

			}
			curY = y;
			curX = x;
		}
	}

	static boolean isInRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}

	static int getBlindSpots() {
		int blindSpotCount = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (checkArr[i][j] == 0) {
					blindSpotCount++;
				}
			}
		}
		return blindSpotCount;
	}
}
