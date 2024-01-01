import java.util.*;

class Solution {
	public int solution(int[][] scores) {
		int answer = 0;
		int[] target = scores[0];

		Arrays.sort(scores, (x, y) -> {
			if (x[0] == y[0]) return Integer.compare(x[1], y[1]);
			return Integer.compare(y[0], x[0]);
		});

		int temp = 0;
		for (int[] score : scores) {
			boolean isLower = false;

			if (temp < score[1]) {
				temp = score[1];
			} else if (temp > score[1]) {
				isLower = true;
			}

			if (isLower) {
				if (score[0] == target[0] && score[1] == target[1]) {
					return -1;
				}
				continue;
			}

			if (score[0] + score[1] > target[0] + target[1]) {
				answer++;
			}
		}
		
		return answer + 1;
	}
}
