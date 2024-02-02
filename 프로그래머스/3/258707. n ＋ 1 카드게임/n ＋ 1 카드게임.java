class Solution {
	static int n;
	static int coin;
	static boolean[] hasCard;
	static boolean[] isFree;

	public int solution(int coin, int[] cards) {
		n = cards.length;
		hasCard = new boolean[n + 1];
		isFree = new boolean[n + 1];

		for (int i = 0; i < n / 3; i++) {
			hasCard[cards[i]] = true;
			isFree[cards[i]] = true;
		}

		int answer = 1;
		for (int i = n / 3; i < n; i += 2) {
			if (coin > 0) {
				hasCard[cards[i]] = true;
				hasCard[cards[i + 1]] = true;
			}

			boolean flag = false;
			int minCost = 3;
			int trash = -1;
			for (int j = 1; j <= n; j++) {
				if (!hasCard[j]) {
					continue;
				}

				if (hasCard[n + 1 - j]) {
					int cost = 0;
					if(!isFree[j]) {
						cost++;
					}
					if(!isFree[n + 1 - j]) {
						cost++;
					}
					
					if (coin >= cost && minCost > cost) {
						flag = true;
						trash = j;
						minCost = cost;
					}
				}
			}

			if (!flag) {
				break;
			}
			hasCard[trash] = false;
			hasCard[n + 1 - trash] = false;
			coin -= minCost;
			answer++;
		}
		
		return answer;
	}
}
