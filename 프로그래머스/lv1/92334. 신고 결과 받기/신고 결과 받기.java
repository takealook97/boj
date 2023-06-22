import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class Solution {
	static HashMap<String, ArrayList<String>> list = new HashMap<>();
	static HashMap<String, Integer> reportCount = new HashMap<>();

	public int[] solution(String[] id_list, String[] report, int k) {
		for (String name : id_list) {
			list.put(name, new ArrayList<>());
			reportCount.put(name, 0);
		}
		for (String names : report) {
			String[] snitch = names.split(" ");
			if (!list.get(snitch[0]).contains(snitch[1])) {
				reportCount.put(snitch[1], reportCount.get(snitch[1]) + 1);
			}
			list.get(snitch[0]).add(snitch[1]);
		}
		ArrayList<String> banned = new ArrayList<>();
		for (String name : reportCount.keySet()) {
			if (reportCount.get(name) >= k) {
				banned.add(name);
			}
		}

		int[] result = new int[id_list.length];
		int index = 0;
		for (String name : id_list) {
			int count = 0;
			for (String bannedName : banned) {
				if (list.get(name).contains(bannedName)) {
					count++;
				}
			}
			result[index] = count;
			index++;
		}

		return result;
	}
}