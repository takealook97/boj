import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static long B;
	static int[][] arr, answer;
	static final int MOD = 1000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		B = Long.parseLong(st.nextToken());
		arr = new int[N][N];
		answer = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		answer = involution(arr, B);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(answer[i][j]).append(" ");
			}
			sb.append("\n");
		}

		System.out.print(sb);
	}

	public static int[][] involution(int[][] board, long num) {
		int[][] result = new int[N][N];

		for (int i = 0; i < N; i++) {
			result[i][i] = 1;
		}

		while (num > 0) {
			if (num % 2 == 1) {
				result = multiply(result, board);
			}
			board = multiply(board, board);
			num /= 2;
		}

		return result;
	}

	public static int[][] multiply(int[][] boardA, int[][] boardB) {
		int[][] result = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					result[i][j] += boardA[i][k] * boardB[k][j];
					result[i][j] %= MOD;
				}
			}
		}

		return result;
	}
}
