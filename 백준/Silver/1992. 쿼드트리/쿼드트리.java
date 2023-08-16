import java.io.*;

public class Main {
	static int[][] board;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				board[i][j] = line.charAt(j) - '0';
			}
		}
		
		dq(0, 0, N);
		System.out.println(answer);
	}

	static void dq(int y, int x, int size) {
		if (isPossible(y, x, size)) {
			answer.append(board[y][x]);
			return;
		}
		
		size /= 2;
		answer.append("(");
		dq(y, x, size);
		dq(y, x + size, size);
		dq(y + size, x, size);
		dq(y + size, x + size, size);
		answer.append(")");
	}

	static boolean isPossible(int y, int x, int size) {
		int standard = board[y][x];
		for (int i = y; i < y + size; i++) {
			for (int j = x; j < x + size; j++) {
				if (standard != board[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
}
