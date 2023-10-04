import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static boolean[][] isIsland, visit, linkCheck;
	static ArrayList<Point[]> bridgeList = new ArrayList<>();
	static int[] dy = { -1, 1, 0, 0 }, dx = { 0, 0, -1, 1 };
	static HashMap<Point, Integer> islandMap = new HashMap<>();
	static boolean[] bridgeVisit;
	static int islandCount = 0;
	static int islandNum = 1;
	static int answer = Integer.MAX_VALUE;

	static class Point {
		int y, x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Point other = (Point) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "[" + y + "," + x + "]";
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		isIsland = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				isIsland[i][j] = (Integer.parseInt(st.nextToken()) == 1);
			}
		}

		visit = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (isIsland[i][j] && !visit[i][j]) {
					checkIsland(i, j);
					islandCount++;
					islandNum++;
				}
			}
		}
		setBridgeList();

		linkCheck = new boolean[islandCount][islandCount];
		bridgeVisit = new boolean[bridgeList.size()];
		findMininum(0, 0, 0);

		if (answer == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(answer);
		}

//		for (Point[] arr : bridgeList) {
//			System.out.println(Arrays.toString(arr));
//		}

	}

	static void checkIsland(int y, int x) {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(y, x));
		visit[y][x] = true;
		while (!queue.isEmpty()) {
			Point now = queue.poll();
			islandMap.put(now, islandNum);// 섬 숫자 부여
			for (int i = 0; i < 4; i++) {
				int nextY = now.y + dy[i];
				int nextX = now.x + dx[i];
				if (isInRange(nextY, nextX) && isIsland[nextY][nextX] && !visit[nextY][nextX]) {
					visit[nextY][nextX] = true;
					queue.add(new Point(nextY, nextX));
				}
			}
		}
	}

	static void setBridgeList() {
		// 가로
		for (int i = 0; i < N; i++) {
			out: for (int j = 1; j < M; j++) {
				if (!isIsland[i][j] && isIsland[i][j - 1]) {
					int from = islandMap.get(new Point(i, j - 1));// 시작 섬

					// 투포인터
					int left = j;
					int right = j;
					while (true) {
						right++;
						if (right >= M) {
							break out;
						}
						if (isIsland[i][right]) {
							break;
						}
					}
					if (right - left >= 2) {
						Point[] bridge = new Point[right - left + 1];
						int to = islandMap.get(new Point(i, j + right - left));// 도착 섬
						if (from == to) {
							continue;
						}
						bridge[0] = new Point(from, to);
						for (int idx = 1; idx <= right - left; idx++) {
							bridge[idx] = new Point(i, j + idx - 1);
						}

//						System.out.println(from + " " + to);
						bridgeList.add(bridge);
					}
				}
			}
		}
		// 세로
		for (int i = 0; i < M; i++) {
			out: for (int j = 1; j < N; j++) {
				if (!isIsland[j][i] && isIsland[j - 1][i]) {
					int from = islandMap.get(new Point(j - 1, i));// 시작 섬

					// 투포인터
					int left = j;
					int right = j;
					while (true) {
						right++;
						if (right >= N) {
							break out;
						}
						if (isIsland[right][i]) {
							break;
						}
					}
					if (right - left >= 2) {
						Point[] bridge = new Point[right - left + 1];
						int to = islandMap.get(new Point(j + right - left, i));// 도착 섬
						if (from == to) {
							continue;
						}
						bridge[0] = new Point(from, to);
						for (int idx = 1; idx <= right - left; idx++) {
							bridge[idx] = new Point(j + idx - 1, i);
						}

//						System.out.println(from + " " + to);
						bridgeList.add(bridge);
					}
				}
			}
		}
	}

	static void findMininum(int idx, int bridgeLength, int bridgeCount) {
//		System.out.println(Arrays.toString(bridgeVisit));
//		System.out.println(idx + " " + bridgeLength + " " + bridgeCount);
//		printArr(linkCheck, islandCount, islandCount);
//		System.out.println();

		if (bridgeCount == islandCount - 1) {// 다리의 개수가 섬 개수 - 1 일 경우 기저조건
			if(isAllLinked()) {
				answer = Math.min(answer, bridgeLength);
			}
			return;
		}

		for (int i = idx; i < bridgeList.size(); i++) {
			Point[] tempArr = bridgeList.get(i);
			int from = tempArr[0].y - 1;
			int to = tempArr[0].x - 1;
			int length = tempArr.length - 1;
			if (!bridgeVisit[i] && !linkCheck[from][to]) {
				bridgeVisit[i] = true;
				linkCheck[from][to] = true;
				linkCheck[to][from] = true;
				findMininum(i + 1, bridgeLength + length, bridgeCount + 1);
				linkCheck[to][from] = false;
				linkCheck[from][to] = false;
				bridgeVisit[i] = false;
			}
		}
	}
	
	static boolean isAllLinked() {
		int start = 0;
		boolean[] tempVisit = new boolean[islandCount];
		for(int i = 0; i < islandCount; i++) {
			for(int j = 0; j < islandCount; j++) {
				if(linkCheck[i][j]) {
					start = i;
				}
			}
		}
		
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start);
		int count = 1;
		tempVisit[start] = true;
		while(!queue.isEmpty()) {
			int now = queue.poll();
			for(int next = 0; next < islandCount; next++) {
				if(!tempVisit[next] && linkCheck[now][next]) {
					tempVisit[next] = true;
					count++;
					queue.add(next);
				}
			}
		}
		return count == islandCount;
		
	}

	static boolean isInRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}

	static void printArr(boolean[][] arr, int col, int row) {
		for (int i = 0; i < col; i++) {
			for (int j = 0; j < row; j++) {
				System.out.print((arr[i][j] == true ? 1 : 0) + " ");
			}
			System.out.println();
		}
	}
}
