import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] arr;
	static int[][] cctv;
	static ArrayList<int[]>[] directionList;
	static ArrayList<Point> list = new ArrayList<>();
	static boolean[] visit;
	static int count = 0;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static int result = Integer.MAX_VALUE;

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
		arr = new int[N][M];
		cctv = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] != 0 && arr[i][j] != 6) {
					count++;
					list.add(new Point(i, j));
					cctv[i][j]++;
				} else if (arr[i][j] == 6) {
					cctv[i][j]++;
				}
			}
		}
		visit = new boolean[count];
		makeDirectionList();
		dfs(0);
		System.out.println(result);
	}

	static void makeDirectionList() {
		directionList = new ArrayList[6];
		for (int i = 1; i <= 5; i++) {
			directionList[i] = new ArrayList<>();
		}
		for (int i = 0; i < 4; i++) {
			directionList[1].add(new int[]{i});
		}
		directionList[2].add(new int[]{0, 1});
		directionList[2].add(new int[]{2, 3});
		directionList[3].add(new int[]{0, 2});
		directionList[3].add(new int[]{0, 3});
		directionList[3].add(new int[]{1, 2});
		directionList[3].add(new int[]{1, 3});
		directionList[4].add(new int[]{0, 1, 2});
		directionList[4].add(new int[]{0, 1, 3});
		directionList[4].add(new int[]{0, 2, 3});
		directionList[4].add(new int[]{1, 2, 3});
		directionList[5].add(new int[]{0, 1, 2, 3});
	}

	static void dfs(int depth) {
		if (depth == count) {
			result = Math.min(result, getBlindSpots());
			return;
		}
		Point now = list.get(depth);
		int nowY = now.y;
		int nowX = now.x;
		int nowNum = arr[nowY][nowX];
		for (int i = 0; i < directionList[nowNum].size(); i++) {
			visit[depth] = true;
			checkView(nowY, nowX, i, true);
			dfs(depth + 1);
			checkView(nowY, nowX, i, false);
			visit[depth] = false;
		}
	}

	static void checkView(int y, int x, int index, boolean isOn) {
		int cctvNum = arr[y][x];
		int curY = y;
		int curX = x;
		int[] directionArr = directionList[cctvNum].get(index);
		for (int direction : directionArr) {
			curY += dy[direction];
			curX += dx[direction];
			while (0 <= curY && curY < N && 0 <= curX && curX < M && arr[curY][curX] != 6) {
				if (isOn) {
					cctv[curY][curX]++;
				} else {
					cctv[curY][curX]--;
				}
				curY += dy[direction];
				curX += dx[direction];
			}
			curY = y;
			curX = x;
		}
	}

	static int getBlindSpots() {
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (cctv[i][j] == 0) {
					count++;
				}
			}
		}
		return count;
	}
}
