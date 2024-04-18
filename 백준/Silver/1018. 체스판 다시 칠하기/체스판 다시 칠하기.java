import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int M, N;
	static char[][] board;
	static int answer = Integer.MAX_VALUE;
	static final String B_LINE = "BWBWBWBW", W_LINE = "WBWBWBWB";

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		board = new char[M][N];

		for (int i = 0; i < M; i++) {
			board[i] = br.readLine().toCharArray();
		}

		for (int y = 0; y <= M - 8; y++) {
			for (int x = 0; x <= N - 8; x++) {
				int count = 0;

				for (int i = y; i < y + 8; i++) {
					for (int j = x; j < x + 8; j++) {
						if (i % 2 == 0) {
							if (B_LINE.charAt(j - x) != board[i][j]) {
								count++;
							}
						} else {
							if (W_LINE.charAt(j - x) != board[i][j]) {
								count++;
							}
						}
					}
				}

				count = Math.min(count, 64 - count);
				answer = Math.min(answer, count);
			}
		}

		System.out.println(answer);
	}
}
