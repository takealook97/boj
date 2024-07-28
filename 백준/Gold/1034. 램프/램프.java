import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static int[][] board;
	static Map<String, Integer> map = new HashMap<>();
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				board[i][j] = line.charAt(j) - '0';
			}
		}
		K = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			StringBuilder sb = new StringBuilder();
			int count = 0;
			for (int j = 0; j < M; j++) {
				sb.append(board[i][j]);
				if (board[i][j] == 0) {
					count++;
				}
			}

			String line = sb.toString();
			if (count <= K && (K - count) % 2 == 0) {
				map.put(line, map.getOrDefault(line, 0) + 1);
			}
		}

		for (int count : map.values()) {
			answer = Math.max(answer, count);
		}

		System.out.println(answer);
	}
}
