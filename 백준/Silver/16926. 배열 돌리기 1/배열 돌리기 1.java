import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, R;
	static int[][] board;
	static int[] dy = { 1, 0, -1, 0 }, dx = { 0, 1, 0, -1 }; // 하 우 상 좌

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int fromY = 0;
		int fromX = 0;
		int toY = N - 1;
		int toX = M - 1;

		while (fromY <= toY && fromX <= toX) {
			rotateBorder(fromY, fromX, toY, toX);
			fromY++;
			fromX++;
			toY--;
			toX--;
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(board[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}

	static void rotateBorder(int fromY, int fromX, int toY, int toX) {
		int count = R % ((toY + toX - fromY - fromX) * 2);

		while (count-- > 0) {
			int temp = board[fromY][fromX];
			for (int j = fromX; j < toX; j++) {
				board[fromY][j] = board[fromY][j + 1];
			}
			for (int i = fromY; i < toY; i++) {
				board[i][toX] = board[i + 1][toX];
			}
			for (int j = toX; j > fromX; j--) {
				board[toY][j] = board[toY][j - 1];
			}
			for (int i = toY; i > fromY; i--) {
				board[i][fromX] = board[i - 1][fromX];
			}
			board[fromY + 1][fromX] = temp;
		}
	}
}
