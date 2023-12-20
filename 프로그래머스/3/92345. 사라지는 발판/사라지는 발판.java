import java.io.*;
import java.util.*;

class Solution {
	static int N, M;
	static int[] dy = {0, 0, -1, 1}, dx = {-1, 1, 0, 0};
	static int[][] board;
	static boolean[][] visit;

	static int solution(int[][] arr, int[] aLoc, int[] bLoc) {
		N = arr.length;
		M = arr[0].length;
		board = arr;
		visit = new boolean[N][M];
		return getMax(aLoc[0], aLoc[1], bLoc[0], bLoc[1]);
	}

	static int getMax(int ay, int ax, int by, int bx) {
		if (visit[ay][ax]) {
			return 0;
		}
		int result = 0;
		for (int i = 0; i < 4; i++) {
			int nextY = ay + dx[i];
			int nextX = ax + dy[i];
			if (!isInRange(nextY, nextX) || visit[nextY][nextX] || board[nextY][nextX] == 0) {
				continue;
			}

			visit[ay][ax] = true;
			int value = getMax(by, bx, nextY, nextX) + 1;
			visit[ay][ax] = false;

			if (result % 2 == 0 && value % 2 == 1) {
				result = value;
			} else if (result % 2 == 0 && value % 2 == 0) {
				result = Math.max(result, value);
			} else if (result % 2 == 1 && value % 2 == 1) {
				result = Math.min(result, value);
			}
		}
		return result;
	}

	static boolean isInRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}
}
