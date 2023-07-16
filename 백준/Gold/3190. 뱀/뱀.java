import java.util.*;
import java.io.*;

public class Main {
	static int N, K, L;
	static int[][] arr;
	static boolean[][] visit;
	static HashMap<Integer, Boolean> order = new HashMap<>();
	static int direction = 0; // 0 우 / 1 하 / 2 좌 / 3 상
	static Deque<Point> snake = new ArrayDeque<>();
	static int[] dy = {0, 1, 0, -1}, dx = {1, 0, -1, 0};
	static int count = 0;

	static class Point {
		int y, x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}

			if (obj == null || getClass() != obj.getClass()) {
				return false;
			}

			Point other = (Point)obj;
			return x == other.x && y == other.y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		visit = new boolean[N][N];
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken()) - 1;
			int x = Integer.parseInt(st.nextToken()) - 1;
			arr[y][x] = 2;//apple
			visit[y][x] = true;
		}

		L = Integer.parseInt(br.readLine());
		for (int i = 0; i < L; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			char alphabet = st.nextToken().charAt(0);
			boolean isLeft = alphabet == 'L';
			order.put(time, isLeft);
		}

		snake.add(new Point(0, 0));
		while (true) {
			move();
			setDirection();

		}
	}

	static void setDirection() {
		if (order.containsKey(count)) {
			boolean isLeft = order.get(count);
			if (isLeft) {
				direction--;
				if (direction < 0) {
					direction = 3;
				}
			} else {
				direction++;
				if (direction > 3) {
					direction = 0;
				}
			}
		}
	}

	static void move() {
		Point head = snake.peekFirst();
		int nowY = head.y;
		int nowX = head.x;
		int nextY = nowY + dy[direction];
		int nextX = nowX + dx[direction];

		if (isPossible(nextY, nextX)) {
			if (isApple(nextY, nextX)) {
				stretch(nextY, nextX);
				count++;
			} else {
				stretch(nextY, nextX);
				shorten();
				count++;
			}
		} else {
			count++;
			System.out.println(count);
			System.exit(0);
		}
	}

	static void stretch(int y, int x) {
		if (isPossible(y, x)) {
			snake.addFirst(new Point(y, x));
		}
	}

	static void shorten() {
		snake.removeLast();
	}

	static boolean isPossible(int y, int x) {//몸이나 벽이면 false
		if (snake.contains(new Point(y, x))) {
			return false;
		}
		return 0 <= y && y < N && 0 <= x && x < N;
	}

	static boolean isApple(int y, int x) {
		if (arr[y][x] == 2) {//사과일 경우
			arr[y][x] = 0;
			return true;
		}
		return false;//빈칸일 경우
	}
}
