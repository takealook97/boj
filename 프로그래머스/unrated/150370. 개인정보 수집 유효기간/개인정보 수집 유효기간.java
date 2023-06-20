import java.util.HashMap;

class Solution {
	static int y, m, d, sumA;
	static HashMap<Character, Integer> map = new HashMap<>();
	static boolean[] result;

	public int[] solution(String today, String[] terms, String[] privacies) {
		String[] info = today.split("\\.");
		y = Integer.parseInt(info[0]);
		m = Integer.parseInt(info[1]);
		d = Integer.parseInt(info[2]);
		for (String term : terms) {
			String[] dueDate = term.split(" ");
			map.put(dueDate[0].charAt(0), Integer.parseInt(dueDate[1]));
		}
		result = new boolean[privacies.length];
		sumA = y * 10000 + m * 100 + d;
		int count = 0;
		for (int i = 0; i < privacies.length; i++) {
			String privacy = privacies[i];
			int nowY = Integer.parseInt(privacy.substring(0, 4));
			int nowM = Integer.parseInt(privacy.substring(5, 7));
			int nowD = Integer.parseInt(privacy.substring(8, 10));
			char term = privacy.charAt(privacy.length() - 1);
			nowM += map.get(term);
			if (nowM > 12) {
				int yearGap;
				if (nowM % 12 != 0) {
					yearGap = nowM / 12;
					nowM %= 12;
				} else {
					yearGap = nowM / 12 - 1;
					nowM = 12;
				}
				nowY += yearGap;
			}
			nowD--;

			if (nowD == 0) {
				nowM--;
				if (nowM == 0) {
					nowM = 12;
					nowY--;
				}
				nowD = 28;
			}

			int sumB = nowY * 10000 + nowM * 100 + nowD;
			if (sumA > sumB) {
				result[i] = true;
				count++;
			}
		}
		int[] answer = new int[count];
		int index = 0;
		for (int i = 0; i < result.length; i++) {
			if (result[i]) {
				answer[index] = i + 1;
				index++;
			}
		}
		return answer;
	}
}
