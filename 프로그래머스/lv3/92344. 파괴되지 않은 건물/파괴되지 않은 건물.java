import java.util.*;

class Solution {
	public int solution(int[][] board, int[][] skill) {
		int answer = 0;
		int N = board.length;
		int M = board[0].length;
		int[][] arr = new int[N][M];

		for (int[] line : skill) {
			int type = line[0];
			int degree = line[5];
			arr[line[1]][line[2]] += (type == 1) ? -degree : degree;
			if (line[3] + 1 < N) arr[line[3] + 1][line[2]] += (type == 1) ? degree : -degree;
			if (line[4] + 1 < M) arr[line[1]][line[4] + 1] += (type == 1) ? degree : -degree;
			if (line[3] + 1 < N && line[4] + 1 < M) arr[line[3] + 1][line[4] + 1] += (type == 1) ? -degree : degree;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 1; j < M; j++) {
				arr[i][j] += arr[i][j - 1];
			}
		}

		for (int j = 0; j < M; j++) {
			for (int i = 1; i < N; i++) {
				arr[i][j] += arr[i - 1][j];
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] + arr[i][j] > 0) {
					answer++;
				}
			}
		}

		return answer;
	}
}
