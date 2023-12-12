import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long A = sc.nextLong();
		long B = sc.nextLong();

		long result = countOnes(B) - countOnes(A - 1);
		System.out.println(result);
	}

	static long countOnes(long num) {
		long count = 0;
		long bit = 1;
		long n = num + 1;

		while (num >= bit) {
			long quotient = n / (bit << 1);
			long remainder = n % (bit << 1);

			count += quotient * bit;

			if (remainder > bit) {
				count += remainder - bit;
			}

			bit <<= 1;
		}
		return count;
	}
}
