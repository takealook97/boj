import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
	static int N;
	static Deque<Integer> dqA = new ArrayDeque<>();
	static Deque<Integer> dqB = new ArrayDeque<>();
	static long sumA, sumB, sum;

	public int solution(int[] queue1, int[] queue2) {
		makeDeque(queue1, queue2);
		sum = sumA + sumB;
		if (sum % 2 == 1) {
			return -1;
		}
		sum /= 2;
		
		int numA = 0;
		int numB = 0;
		N = queue1.length;
		while (numA <= 2 * N && numB <= 2 * N) {
			if (sumA == sum)
				return numA + numB;
			if (sumA > sum) {
				int temp = dqA.pop();
				sumA -= temp;
				sumB += temp;
				dqB.add(temp);
				numA++;
			} else {
				int num = dqB.pop();
				sumB -= num;
				sumA += num;
				dqA.add(num);
				numB++;
			}
		}
		return -1;
	}

	static void makeDeque(int[] queue1, int[] queue2) {
		for (int num : queue1) {
			dqA.add(num);
			sumA += num;
		}
		for (int tmp : queue2) {
			dqB.add(tmp);
			sumB += tmp;
		}
	}
}