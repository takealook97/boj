import java.util.*;

class Solution {
	static int N, M;
	static int[][] dice;
	static boolean[] visit;
	static int maxWin = Integer.MIN_VALUE;
	static int[] answer;

	public int[] solution(int[][] arr) {
		N = arr.length;
        M = (int)Math.pow(6, N / 2);
		dice = arr;
		visit = new boolean[N];
		answer = new int[N / 2];

		for (int[] line : dice) {
			Arrays.sort(line);
		}

		comb(0, 0);

		return answer;
	}

	static void comb(int idx, int depth) {
		if (depth == N / 2) {
			int winCount = getWinCount();
			if (maxWin < winCount) {
				maxWin = winCount;
				int index = 0;
				for (int i = 0; i < N; i++) {
					if (visit[i]) {
						answer[index++] = i + 1;
					}
				}
			}
			return;
		}

		for (int i = idx; i < N; i++) {
			if (!visit[i]) {
				visit[i] = true;
				comb(i + 1, depth + 1);
				visit[i] = false;
			}
		}
	}

    static int getWinCount() {
        int[] aDice = new int[N / 2];
        int[] bDice = new int[N / 2];
        int aIndex = 0, bIndex = 0;
        for (int i = 0; i < N; i++) {
            if (visit[i]) aDice[aIndex++] = i;
            else bDice[bIndex++] = i;
        }

        int winCount = 0;
        int[] aSums = calculateSums(aDice);
        int[] bSums = calculateSums(bDice);
        Arrays.sort(aSums);
        Arrays.sort(bSums);

        for (int aSum : aSums) {
            int low = 0, high = bSums.length - 1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (bSums[mid] < aSum) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            winCount += low;
        }

        return winCount;
    }


	static int[] calculateSums(int[] diceIndexes) {
		int[] sums = new int[M];
		for (int i = 0; i < M; i++) {
			int sum = 0, temp = i;
			for (int index : diceIndexes) {
				sum += dice[index][temp % 6];
				temp /= 6;
			}
			sums[i] = sum;
		}
		return sums;
	}
}