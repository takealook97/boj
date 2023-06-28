import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

class Solution {
	static ArrayList<ArrayList<Character>> arr = new ArrayList<>();
	static HashMap<String, Integer> result = new HashMap<>();
	static ArrayList<Character> tempArr;
	static ArrayList<Character> tempResult;
	static int N;
	static int M;
	static boolean[] visit;

	public String[] solution(String[] orders, int[] course) {
		for (String order : orders) {
			ArrayList<Character> list = new ArrayList<>();
			for (int i = 0; i < order.length(); i++) {
				list.add(order.charAt(i));
			}
			Collections.sort(list);
			arr.add(list);
		}
		getComb();
		ArrayList<String> list = new ArrayList<>();
		for (int i : course) {
			list.addAll(getMax(i));
		}
		Collections.sort(list);
		String[] answer = new String[list.size()];
		for (int i = 0; i < answer.length; i++) {
			answer[i] = list.get(i);
		}
		return answer;
	}

	static void getComb() {
		for (ArrayList<Character> count : arr) {
			tempArr = count;
			tempResult = new ArrayList<>();
			N = count.size();
			for (int i = 2; i <= count.size(); i++) {
				visit = new boolean[count.size()];
				M = i;
				dfs(0, 0);
			}
		}
	}

	static void dfs(int now, int depth) {
		if (depth == M) {
			StringBuilder sb = new StringBuilder();
			for (char i : tempResult) {
				sb.append(i);
			}
			String line = sb.toString();
			if (!result.containsKey(line)) {
				result.put(line, 1);
			} else {
				result.put(line, result.get(line) + 1);
			}
			return;
		}
		for (int i = now; i < N; i++) {
			if (!visit[i]) {
				visit[i] = true;
				tempResult.add(tempArr.get(i));
				dfs(i, depth + 1);
				tempResult.remove(tempResult.size() - 1);
				visit[i] = false;
			}
		}
	}

	static ArrayList<String> getMax(int size) {
		ArrayList<String> list = new ArrayList<>();
		int max = 0;
		for (String order : result.keySet()) {
			if (order.length() == size) {
				max = Math.max(max, result.get(order));
			}
		}
		if (max == 1) {
			return list;
		}
		for (String order : result.keySet()) {
			if (order.length() == size && result.get(order) == max) {
				list.add(order);
			}
		}
		return list;
	}
}
