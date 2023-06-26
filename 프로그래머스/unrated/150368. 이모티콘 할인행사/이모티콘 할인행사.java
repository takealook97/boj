import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

class Solution {
	static int N, M;
	static int[][] people;
	static int[][] arr;
	static PriorityQueue<Joined> pq = new PriorityQueue<>(Collections.reverseOrder());

	static class Joined implements Comparable<Joined> {
		int count, sum;

		public Joined(int count, int sum) {
			this.count = count;
			this.sum = sum;
		}

		@Override
		public int compareTo(Joined o) {
			return this.count - o.count;
		}
	}

	public int[] solution(int[][] users, int[] emoticons) {
		N = emoticons.length;
		M = users.length;

		setDiscountArr(emoticons);
		setStandard(users);

		ArrayList<Integer> list = new ArrayList<>();
		dfs(list);
		return getMax();
	}

	static void setDiscountArr(int[] emoticons) {
		arr = new int[N][4];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 4; j++) {
				arr[i][j] = emoticons[i] / 10 * (10 - j - 1);
			}
			System.out.println();
		}
	}

	static void setStandard(int[][] users) {
		for (int i = 0; i < users.length; i++) {
			users[i][0] = (int)Math.ceil((double)users[i][0] / 10) - 1;
			if (users[i][0] < 0) {
				users[i][0] = 0;
			}
		}
		people = users;
	}

	static void dfs(ArrayList<Integer> list) {
		int depth = list.size();
		if (depth == N) {
			int[] buySum = new int[M];
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					int rate = list.get(j);
					if (people[i][0] <= rate) {
						buySum[i] += arr[j][rate];
					}
				}
			}
			int count = 0;
			int sum = 0;
			for (int i = 0; i < M; i++) {
				if (buySum[i] >= people[i][1]) {
					count++;
				} else {
					sum += buySum[i];
				}
			}
			pq.add(new Joined(count, sum));
			return;
		}

		for (int i = 0; i < 4; i++) {
			list.add(i);
			dfs(list);
			list.remove(list.size() - 1);
		}
	}

	static int[] getMax() {
		int count = pq.peek().count;
		int sum = 0;
		while (!pq.isEmpty()) {
			Joined joined = pq.poll();
			if (joined.count == count) {
				sum = Math.max(sum, joined.sum);
			} else
				break;
		}
		return new int[] {count, sum};
	}
}