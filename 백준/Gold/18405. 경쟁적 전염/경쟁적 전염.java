import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, K, S, X, Y;
	static int[][] board;
	static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};

	static class Virus implements Comparable<Virus> {
		int type, y, x, time;

		public Virus(int type, int y, int x, int time) {
			this.type = type;
			this.y = y;
			this.x = x;
			this.time = time;
		}

		@Override
		public int compareTo(Virus o) {
			if (this.time == o.time) {
				return Integer.compare(this.type, o.type);
			}
			return Integer.compare(this.time, o.time);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		board = new int[N][N];
		PriorityQueue<Virus> pq = new PriorityQueue<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] > 0) {
					pq.add(new Virus(board[i][j], i, j, 0));
				}
			}
		}

		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken()) - 1;
		Y = Integer.parseInt(st.nextToken()) - 1;

		while (!pq.isEmpty()) {
			Virus virus = pq.poll();

			if (virus.time == S || board[X][Y] > 0) {
				break;
			}

			for (int i = 0; i < 4; i++) {
				int nextY = virus.y + dy[i];
				int nextX = virus.x + dx[i];

				if (isPossible(nextY, nextX) && board[nextY][nextX] == 0) {
					board[nextY][nextX] = virus.type;
					pq.add(new Virus(virus.type, nextY, nextX, virus.time + 1));
				}
			}
		}

		System.out.println(board[X][Y]);
	}

	static boolean isPossible(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N;
	}
}
