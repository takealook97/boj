class Solution {
	static int N, M;
	static int max;
	static int answer;
	public int solution(int[][] beginning, int[][] target) {
		N = beginning.length;
		M = beginning[0].length;
		max = N * M + 1;
		answer = max;

		for (int i = 0; i < (1 << N); i++) {
			for (int j = 0; j < (1 << M); j++) {
				int count = Integer.bitCount(i) + Integer.bitCount(j);
				if (count < answer && compare(beginning, target, i, j)) {
					answer = count;
				}
			}
		}

		return answer < max ? answer : -1;
	}
	static boolean compare(int[][] beginning, int[][] target, int row, int col) {
		int N = beginning.length;
		int M = beginning[0].length;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int gap = ((row >> i) % 2 + ((col >> j) % 2)) % 2;
				if ((beginning[i][j] + gap) % 2 != target[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
}
