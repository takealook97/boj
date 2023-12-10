import java.util.Scanner;

public class Main {
	static long x1, y1, x2, y2, x3, y3, x4, y4;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		x1 = sc.nextLong();
		y1 = sc.nextLong();
		x2 = sc.nextLong();
		y2 = sc.nextLong();

		x3 = sc.nextLong();
		y3 = sc.nextLong();
		x4 = sc.nextLong();
		y4 = sc.nextLong();

		int answer = 0;
		boolean check = false;

		if (ccw(x1, y1, x2, y2, x3, y3) * ccw(x1, y1, x2, y2, x4, y4) == 0 &&
			ccw(x3, y3, x4, y4, x1, y1) * ccw(x3, y3, x4, y4, x2, y2) == 0) {
			check = true;
			if (Math.min(x1, x2) <= Math.max(x3, x4) && Math.min(x3, x4) <= Math.max(x1, x2) &&
				Math.min(y1, y2) <= Math.max(y3, y4) && Math.min(y3, y4) <= Math.max(y1, y2)) {
				answer = 1;
			}
		}

		if (ccw(x1, y1, x2, y2, x3, y3) * ccw(x1, y1, x2, y2, x4, y4) <= 0 &&
			ccw(x3, y3, x4, y4, x1, y1) * ccw(x3, y3, x4, y4, x2, y2) <= 0) {
			if (!check) {
				answer = 1;
			}
		}

		System.out.println(answer);
	}

	static int ccw(long x1, long y1, long x2, long y2, long x3, long y3) {
		long tmp = (x2 - x1) * (y3 - y1) - (x3 - x1) * (y2 - y1);
		if (tmp > 0) {
			return 1;
		} else if (tmp < 0) {
			return -1;
		} else {
			return 0;
		}
	}
}
