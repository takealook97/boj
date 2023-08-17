import java.io.*;
import java.util.*;

public class Main {
	static int N, M, D, count;
	static boolean[][] boardSrc;
	static boolean[][] board;
	static ArrayList<int[]> archerPosList = new ArrayList<>();
	static int[] archerPos;

	static final int ARCHER_COUNT = 3;
	static int answer = 0;

	static class Ops implements Comparable<Ops> {
		int y, x, dist;

		public Ops(int y, int x, int dist) {
			this.y = y;
			this.x = x;
			this.dist = dist;
		}

		@Override
		public int compareTo(Ops o) {
			if (this.dist == o.dist) {
				return this.x - o.x;
			}
			return this.dist - o.dist;
		}

		@Override
		public boolean equals(Object o) {
			Ops temp = (Ops) o;
			return this.y == temp.y && this.x == temp.x;
		}

		@Override
		public int hashCode() {
			return Objects.hash(y, x);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		boardSrc = new boolean[N + 1][M + 1];
		board = new boolean[N + 1][M + 1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				boardSrc[i][j] = (Integer.parseInt(st.nextToken()) == 1);
			}
		}

		setArcherPosList();

		for (int[] pos : archerPosList) {
			archerPos = pos;
			resetBoard();
			count = 0;
			while (!isClear()) {
				catchOps();
				moveOps();
			}
			answer = Math.max(answer, count);
		}
		System.out.println(answer);
	}

	static void resetBoard() {
		for (int i = 0; i < N; i++) {
			board[i] = Arrays.copyOf(boardSrc[i], M);
		}
	}

	static void setArcherPosList() {
		for (int archerA = 0; archerA < M - 2; archerA++) {
			for (int archerB = archerA + 1; archerB < M - 1; archerB++) {
				for (int archerC = archerB + 1; archerC < M; archerC++) {
					archerPosList.add(new int[] { archerA, archerB, archerC });
				}
			}
		}
	}

	static void catchOps() {
		HashSet<Ops> catchSet = new HashSet<>();
		for (int archerIdx = 0; archerIdx < ARCHER_COUNT; archerIdx++) {
			int y = N;
			int x = archerPos[archerIdx];
			PriorityQueue<Ops> pq = new PriorityQueue<>();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (board[i][j]) {
						int dist = Math.abs(y - i) + Math.abs(x - j);
						if (0 < dist && dist <= D) {
							pq.add(new Ops(i, j, dist));
						}
					}
				}
			}
			if (!pq.isEmpty()) {
				catchSet.add(pq.poll());
			}
		}
		if (!catchSet.isEmpty()) {
			for (Ops ops : catchSet) {
				board[ops.y][ops.x] = false;
				count++;
			}
		}
	}

	static void moveOps() {
		for (int y = N - 1; y >= 0; y--) {
			for (int x = 0; x < M; x++) {
				if (board[y][x]) {
					int nextY = y + 1;
					if (nextY < N) {
						board[nextY][x] = true;
					}
					board[y][x] = false;
				}
			}
		}
	}

	static boolean isInRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}

	static boolean isClear() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (board[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
}
