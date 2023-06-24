import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

class Solution {
	static int basicTime, basicFee, timeUnit, feeUnit;
	static HashMap<Integer, Info> visit = new HashMap<>();
	static ArrayList<Integer> carNumList = new ArrayList<>();
	static int[] result;
	static int END_TIME = 23 * 60 + 59;

	static class Info {
		int time;
		boolean check;
		int sum;

		public Info(int time, boolean check, int sum) {
			this.time = time;
			this.check = check;
			this.sum = sum;
		}
	}

	public int[] solution(int[] fees, String[] records) {
		basicTime = fees[0];
		basicFee = fees[1];
		timeUnit = fees[2];
		feeUnit = fees[3];
		for (String line : records) {
			String[] info = line.split(" ");
			int time = Integer.parseInt(info[0].substring(0, 2)) * 60 + Integer.parseInt(info[0].substring(3));
			int carNum = Integer.parseInt(info[1]);
			boolean check = info[2].equals("IN");

			if (!visit.containsKey(carNum) && check) {//첫 입차
				visit.put(carNum, new Info(time, true, 0));
			} else if (visit.containsKey(carNum) && visit.get(carNum).check && !check) {//출차
				int beforeTime = visit.get(carNum).time;
				visit.put(carNum, new Info(time, false, visit.get(carNum).sum + (time - beforeTime)));
			} else if (visit.containsKey(carNum) && !visit.get(carNum).check) {//재입차
				visit.put(carNum, new Info(time, check, visit.get(carNum).sum));
			}
			if (!carNumList.contains(carNum)) {
				carNumList.add(carNum);
			}
		}
		Collections.sort(carNumList);
		System.out.println(carNumList);
		for (Integer carNum : visit.keySet()) {// 11:59 out
			if (visit.get(carNum).check) {
				int beforeTime = visit.get(carNum).time;
				visit.put(carNum, new Info(END_TIME, false, visit.get(carNum).sum + (END_TIME - beforeTime)));
			}
		}

		result = new int[carNumList.size()];
		int index = 0;
		for (int carNum : carNumList) {
			int timeSum = visit.get(carNum).sum;
			if (timeSum <= basicTime) {
				result[index] = basicFee;
			} else {
				double gap = Math.ceil((timeSum - basicTime) / (double)timeUnit);
				result[index] = (int)(basicFee + gap * feeUnit);
			}
			index++;
		}
		return result;
	}
}
