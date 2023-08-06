import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static boolean[][] isWall;
	static boolean[][] visit;
	static Point[][] destArr;
	static boolean[][] destVisit;
	static int[] dy = {0, -1, 0, 1}, dx = {-1, 0, 1, 0};
	static int BASIC_STATUS = -1;
	static int answer = -1;

	static class Point {
		int y, x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}

		@Override
		public boolean equals(Object o) {
			Point p = (Point)o;
			if (this.y == p.y && this.x == p.x) {
				return true;
			}
			return false;
		}
	}

	static class Taxi implements Comparable<Taxi> {
		int y, x, fuel, used, status;

		public Taxi(int y, int x, int fuel, int used, int status) {
			this.y = y;
			this.x = x;
			this.fuel = fuel;
			this.used = used;
			this.status = status;
		}

		@Override
		public int compareTo(Taxi o) {
			if (this.fuel == o.fuel) {
				if (this.y == o.y) {
					return this.x - o.x;
				}
				return this.y - o.y;
			}
			return o.fuel - this.fuel;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int fuel = Integer.parseInt(st.nextToken());
		isWall = new boolean[N][N];
		visit = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				if (num == 1) {
					isWall[i][j] = true;
					visit[i][j] = true;
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		int taxiY = Integer.parseInt(st.nextToken()) - 1;
		int taxiX = Integer.parseInt(st.nextToken()) - 1;

		destArr = new Point[M][2];
		destVisit = new boolean[M][2];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			destArr[i][0] = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
			destArr[i][1] = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
		}

		Taxi taxi = new Taxi(taxiY, taxiX, fuel, 0, BASIC_STATUS);
		findPath(taxi);
		System.out.println(answer);
	}

	static void findPath(Taxi taxi) {
		PriorityQueue<Taxi> pq = new PriorityQueue<>();
		pq.add(taxi);
		visit[taxi.y][taxi.x] = true;
		while (!pq.isEmpty()) {
			Taxi now = pq.poll();

			Point nowPoint = new Point(now.y, now.x);
			int nowFuel = now.fuel;
			int nowUsed = now.used;
			int nowStatus = now.status;

			if (nowFuel < 0) {
				continue;
			}

			//status 정의
			for (int i = 0; i < 2; i++) {
				int[] checkIdx = isOnDest(nowPoint, nowStatus);
				if (checkIdx != null) {//목적지 위일 경우
					int rowIdx = checkIdx[0];
					int colIdx = checkIdx[1];

					if (nowStatus == BASIC_STATUS && colIdx == 0) {//시작점 도착 (승객이 안탄 상태)
						if (!destVisit[rowIdx][colIdx]) {
							destVisit[rowIdx][colIdx] = true;
							nowStatus = rowIdx;
							resetVisit();
							pq.clear();
						}
					} else if (nowStatus == rowIdx && colIdx == 1) {//도착점 도착 (승객이 탄상태 + 일치하는 도착점)
						if (!destVisit[rowIdx][colIdx]) {
							destVisit[rowIdx][colIdx] = true;
							nowStatus = BASIC_STATUS;
							resetVisit();
							pq.clear();

							nowFuel += (nowUsed * 2);
							nowUsed = 0;
							if (isAllCleared()) {
								answer = Math.max(answer, nowFuel);
								return;
							}
						}
					}
				}
			}

			for (int i = 0; i < 4; i++) {
				int nextY = now.y + dy[i];
				int nextX = now.x + dx[i];
				if (0 <= nextY && nextY < N && 0 <= nextX && nextX < N && !visit[nextY][nextX]) {
					visit[nextY][nextX] = true;
					if (nowStatus != BASIC_STATUS) {
						pq.add(new Taxi(nextY, nextX, nowFuel - 1, nowUsed + 1, nowStatus));
					} else {
						pq.add(new Taxi(nextY, nextX, nowFuel - 1, nowUsed, nowStatus));
					}
				}
			}

		}
	}

	static int[] isOnDest(Point point, int status) {

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < 2; j++) {
				if (status == BASIC_STATUS) {
					if (j == 0 && !destVisit[i][j] && point.equals(destArr[i][j])) {
						return new int[] {i, j};
					}
				} else {
					if (j == 1 && i == status && !destVisit[i][j] && point.equals(destArr[i][j])) {
						return new int[] {i, j};
					}
				}
			}
		}
		return null;
	}

	static void resetVisit() {
		for (int i = 0; i < N; i++) {
			visit[i] = Arrays.copyOf(isWall[i], N);
		}
	}

	static boolean isAllCleared() {
		for (boolean[] pair : destVisit) {
			for (boolean dest : pair) {
				if (!dest) {
					return false;
				}
			}
		}
		return true;
	}
}
