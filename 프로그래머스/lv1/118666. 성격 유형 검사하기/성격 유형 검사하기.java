class Solution {
	static int[][] result;

	/*
	R C J A
	T F M N
	 */
	public String solution(String[] survey, int[] choices) {
		result = new int[2][4];
		for (int i = 0; i < survey.length; i++) {
			char disagree = survey[i].charAt(0);
			char agree = survey[i].charAt(1);
			int d = 0;
			int a = 0;
			int choice = choices[i];
			if (1 <= choice && choice <= 3) {
				d += Math.abs(choice - 4);
			} else if (5 <= choice && choice <= 7) {
				a += (choice - 4);
			}
			if (disagree == 'R' || disagree == 'T') {
				if (disagree == 'R') {
					result[0][0] += d;
					result[1][0] += a;
				} else {
					result[0][0] += a;
					result[1][0] += d;
				}
			} else if (disagree == 'C' || disagree == 'F') {
				if (disagree == 'C') {
					result[0][1] += d;
					result[1][1] += a;
				} else {
					result[0][1] += a;
					result[1][1] += d;
				}
			} else if (disagree == 'J' || disagree == 'M') {
				if (disagree == 'J') {
					result[0][2] += d;
					result[1][2] += a;
				} else {
					result[0][2] += a;
					result[1][2] += d;
				}
			} else {
				if (disagree == 'A') {
					result[0][3] += d;
					result[1][3] += a;
				} else {
					result[0][3] += a;
					result[1][3] += d;
				}
			}

			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 4; k++) {
					System.out.print(result[j][k] + " ");
				}
				System.out.println();
			}
			System.out.println();
		}
		StringBuilder sb = new StringBuilder();
		if (result[0][0] > result[1][0]) {
			sb.append("R");
		} else if (result[0][0] < result[1][0]) {
			sb.append("T");
		} else {
			sb.append("R");
		}
		if (result[0][1] > result[1][1]) {
			sb.append("C");
		} else if (result[0][1] < result[1][1]) {
			sb.append("F");
		} else {
			sb.append("C");
		}
		if (result[0][2] > result[1][2]) {
			sb.append("J");
		} else if (result[0][2] < result[1][2]) {
			sb.append("M");
		} else {
			sb.append("J");
		}
		if (result[0][3] > result[1][3]) {
			sb.append("A");
		} else if (result[0][3] < result[1][3]) {
			sb.append("N");
		} else {
			sb.append("A");
		}

		return sb.toString();

	}
}
