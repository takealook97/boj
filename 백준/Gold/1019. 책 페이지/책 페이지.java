import java.util.Scanner;

public class Main {
	static int[] arr = new int[10];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		int start = 1;
		int digit = 1;
		while (start <= N) {
			while (N % 10 != 9 && start <= N) {
				count(N, digit);
				N--;
			}

			while (start % 10 != 0 && start <= N) {
				count(start, digit);
				start++;
			}
			if (N < start) {
				break;
			}
			start /= 10;
			N /= 10;
			for (int i = 0; i < 10; i++) {
				arr[i] += (N - start + 1) * digit;
			}
			digit *= 10;
		}

		for (int i : arr) {
			System.out.print(i + " ");
		}
	}

	static void count(int num, int digit) {
		while (num > 0) {
			arr[num % 10] += digit;
			num /= 10;
		}
	}
}
