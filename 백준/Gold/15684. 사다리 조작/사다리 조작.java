import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, H;
	static boolean[][] lines;
	static int answer = 4;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		lines = new boolean[H][N - 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken()) - 1;
			int col = Integer.parseInt(st.nextToken()) - 1;
			lines[row][col] = true;
		}

		dfs(0, 0);

		if (answer > 3) {
			System.out.println(-1);
		} else {
			System.out.println(answer);
		}
	}

	static void dfs(int y, int depth) {
		if (depth > 3) {
			return;
		}
		if (isStraight()) {
			answer = Math.min(answer, depth);
			if (answer == 0) {
				System.out.println(answer);
				System.exit(0);
			}
			return;
		}

		for (int i = y; i < H; i++) {
			for (int j = 0; j < N - 1; j++) {
				if (!lines[i][j] && !isSideExist(i, j)) {
					lines[i][j] = true;
					dfs(i, depth + 1);
					lines[i][j] = false;
				}
			}
		}
	}

	static boolean isSideExist(int y, int x) {
		if (x == 0) {
			if (N - 1 != 1) {
				return lines[y][x + 1];
			} else {
				return false;
			}
		} else if (x == N - 2) {
			return lines[y][x - 1];
		} else {
			return lines[y][x - 1] || lines[y][x + 1];
		}
	}

	static boolean isStraight() {
		for (int start = 0; start < N; start++) {
			int end = getEnd(start);
			if (start != end) {
				return false;
			}
		}
		return true;
	}

	static int getEnd(int start) {
		int floor = 0;
		int position = start;
		while (floor < H) {
			boolean left, right;
			if (0 == N - 2) {
				if (lines[floor][0]) {
					if (position > 0) {
						position--;
					} else if (position == 0) {
						position++;
					}
				}
			} else if (0 < position && position < N - 1) {
				left = lines[floor][position - 1];
				right = lines[floor][position];
				if (left) {
					position--;
				} else if (right) {
					position++;
				}
			} else if (position == 0) {
				right = lines[floor][position];
				if (right) {
					position++;
				}
			} else if (position == N - 1) {
				left = lines[floor][position - 1];
				if (left) {
					position--;
				}
			}
			floor++;
		}
		return position;
	}
}
