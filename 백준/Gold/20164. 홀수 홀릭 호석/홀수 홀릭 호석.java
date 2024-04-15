import java.util.Scanner;

public class Main {
	static int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		dfs(sc.nextInt(), 0);
		System.out.println(min + " " + max);
	}

	static void dfs(int number, int sum) {
		int length = getNumLength(number);
		int oddCount = getOddCount(number);
		sum += oddCount;

		if (length == 1) {
			update(sum);
		} else if (length == 2) {
			dfs(number / 10 + number % 10, sum);
		} else {
			String numStr = Integer.toString(number);
			for (int i = 1; i < length - 1; i++) {
				for (int j = i + 1; j < length; j++) {
					int nextNum = 0;
					nextNum += Integer.parseInt(numStr.substring(0, i));
					nextNum += Integer.parseInt(numStr.substring(i, j));
					nextNum += Integer.parseInt(numStr.substring(j));
					dfs(nextNum, sum);
				}
			}
		}
	}

	static int getNumLength(int number) {
		int count = 0;
		while (number > 0) {
			number /= 10;
			count++;
		}

		return count;
	}

	static int getOddCount(int number) {
		int count = 0;
		while (number > 0) {
			if (number % 10 % 2 != 0) {
				count++;
			}
			number /= 10;
		}

		return count;
	}

	static void update(int count) {
		min = Math.min(min, count);
		max = Math.max(max, count);
	}
}
