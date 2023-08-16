import java.io.*;
import java.util.*;

public class Main {
	static int[][] gameResult;
	static boolean[][] visit;
	static boolean checkFlag;
	static final int WIN = 0;
	static final int DRAW = 1;
	static final int LOSE = 2;
	static final int TEST_CASE = 4;
	static final int TEAM_COUNT = 6;
	static final int CASE_COUNT = 3;
	static final int GAME_COUNT = 15;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		gameResult = new int[TEAM_COUNT][CASE_COUNT];
		for (int tc = 0; tc < TEST_CASE; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < TEAM_COUNT; i++) {
				for (int j = 0; j < CASE_COUNT; j++) {
					gameResult[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			visit = new boolean[TEAM_COUNT][TEAM_COUNT];

			checkFlag = false;
			boolean result = true;
			if (!checkScoreCount()) {
				result = false;
			} else {
				dfs(0, 0);
				if (!checkFlag) {
					result = false;
				}
			}
			answer.append(result ? 1 : 0).append(" ");
		}
		System.out.println(answer);
	}

	static boolean checkScoreCount() {
		int sum = 0;
		for (int i = 0; i < TEAM_COUNT; i++) {
			sum += (gameResult[i][WIN] - gameResult[i][LOSE]);
		}
		return sum == 0;
	}

	static void dfs(int teamIdx, int count) {
		if (count == GAME_COUNT) {
			checkFlag = true;
			return;
		}
		for (int opsIdx = 0; opsIdx < TEAM_COUNT; opsIdx++) {
			if (opsIdx != teamIdx && !visit[teamIdx][opsIdx] && !visit[opsIdx][teamIdx]) {
				for (int gameCase = 0; gameCase < CASE_COUNT; gameCase++) {
					visit[teamIdx][opsIdx] = true;
					visit[opsIdx][teamIdx] = true;
					if (gameCase == 0) {// 승
						if (gameResult[teamIdx][WIN] - 1 >= 0 && gameResult[opsIdx][LOSE] - 1 >= 0) {
							gameResult[teamIdx][WIN]--;
							gameResult[opsIdx][LOSE]--;
							if (isNone(teamIdx)) {
								dfs(teamIdx + 1, count + 1);
							} else {
								dfs(teamIdx, count + 1);
							}
							if (checkFlag) {
								return;
							}
							gameResult[teamIdx][WIN]++;
							gameResult[opsIdx][LOSE]++;
						}
					} else if (gameCase == 2) {// 패
						if (gameResult[teamIdx][LOSE] - 1 >= 0 && gameResult[opsIdx][WIN] - 1 >= 0) {
							gameResult[teamIdx][LOSE]--;
							gameResult[opsIdx][WIN]--;
							if (isNone(teamIdx)) {
								dfs(teamIdx + 1, count + 1);
							} else {
								dfs(teamIdx, count + 1);
							}
							if (checkFlag) {
								return;
							}
							gameResult[teamIdx][LOSE]++;
							gameResult[opsIdx][WIN]++;
						}
					} else {// 무
						if (gameResult[teamIdx][DRAW] - 1 >= 0 && gameResult[opsIdx][DRAW] - 1 >= 0) {
							gameResult[teamIdx][DRAW]--;
							gameResult[opsIdx][DRAW]--;
							if (isNone(teamIdx)) {
								dfs(teamIdx + 1, count + 1);
							} else {
								dfs(teamIdx, count + 1);
							}
							if (checkFlag) {
								return;
							}
							gameResult[teamIdx][DRAW]++;
							gameResult[opsIdx][DRAW]++;
						}
					}

					if (checkFlag) {
						return;
					}
					visit[teamIdx][opsIdx] = false;
					visit[opsIdx][teamIdx] = false;
				}
			}
		}

	}

	static boolean isNone(int teamIdx) {
		for (int i = 0; i < CASE_COUNT; i++) {
			if (gameResult[teamIdx][i] > 0) {
				return false;
			}
		}
		return true;
	}
}
