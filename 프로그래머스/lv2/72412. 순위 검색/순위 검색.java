import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
	static class Information {
		String lang, pos, career, food;
		int score;

		public Information(String lang, String pos, String career, String food, int score) {
			this.lang = lang;
			this.pos = pos;
			this.career = career;
			this.food = food;
			this.score = score;
		}
	}

	public int[] solution(String[] info, String[] query) {
		Map<String, ArrayList<Integer>> map = new HashMap<>();
		for (String line : info) {
			String[] person = line.split(" ");
			int score = Integer.parseInt(person[4]);
			getComb(person, 0, "", map, score);
		}

		for (List<Integer> scores : map.values()) {
			scores.sort(null);
		}

		int[] result = new int[query.length];
		for (int i = 0; i < query.length; i++) {
			String currentQuery = query[i];
			currentQuery = currentQuery.replaceAll(" and ", " ");
			String[] queryParts = currentQuery.split(" ");

			String key = queryParts[0] + queryParts[1] + queryParts[2] + queryParts[3];
			int score = Integer.parseInt(queryParts[4]);

			ArrayList<Integer> scores = map.getOrDefault(key, new ArrayList<>());
			int count = bs(scores, score);
			result[i] = scores.size() - count;
		}

		return result;
	}

	public static void getComb(String[] person, int index, String current, Map<String, ArrayList<Integer>> map,
		int score) {
		if (index == 4) {
			if (!map.containsKey(current)) {
				map.put(current, new ArrayList<>());
			}
			map.get(current).add(score);
			return;
		}
		getComb(person, index + 1, current + person[index], map, score);
		getComb(person, index + 1, current + "-", map, score);
	}

	public static int bs(ArrayList<Integer> scores, int score) {
		int left = 0;
		int right = scores.size() - 1;
		int targetIndex = scores.size();

		while (left <= right) {
			int mid = (left + right) / 2;
			int midScore = scores.get(mid);

			if (midScore >= score) {
				targetIndex = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}

		return targetIndex;
	}
}
