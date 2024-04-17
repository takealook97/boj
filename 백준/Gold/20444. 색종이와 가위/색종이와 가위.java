import java.util.Scanner;

public class Main {
	static long n, k;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextLong();
		k = sc.nextLong();

		System.out.println(isPossible() ? "YES" : "NO");
	}

	static boolean isPossible() {
		long lo = 0, hi = n / 2;
		long row, col, sum;

		while (lo <= hi) {
			row = lo + (hi - lo) / 2;
			col = n - row;
			sum = (row + 1) * (col + 1);

			if (sum == k) {
				return true;
			} else if (sum > k) {
				hi = row - 1;
			} else {
				lo = row + 1;
			}
		}

		return false;
	}
}
