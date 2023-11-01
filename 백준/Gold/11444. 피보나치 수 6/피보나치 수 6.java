import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static long[][] arrSrc, arr;
	static long MOD = 1000000007;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long N = Long.parseLong(br.readLine());
		if (N == 1 || N == 0) {
			System.out.println(N);
			System.exit(0);
		}
		N--;
		arrSrc = new long[][] {{1, 1}, {1, 0}};
		arr = new long[][] {{1, 0}, {0, 1}};
		while (N > 0) {
			if (N % 2 == 1) {
				arr = multiply(arr, arrSrc);
			}
			arrSrc = multiply(arrSrc, arrSrc);
			N /= 2;
		}
		System.out.println(arr[0][0]);
	}

	public static long[][] multiply(long[][] o1, long[][] o2) {
		long[][] temp = new long[2][2];
		temp[0][0] = ((o1[0][0] * o2[0][0]) + (o1[0][1] * o2[1][0])) % MOD;
		temp[0][1] = ((o1[0][0] * o2[0][1]) + (o1[0][1] * o2[1][1])) % MOD;
		temp[1][0] = ((o1[1][0] * o2[0][0]) + (o1[1][1] * o2[1][0])) % MOD;
		temp[1][1] = ((o1[1][0] * o2[0][1]) + (o1[1][1] * o2[1][1])) % MOD;
		return temp;
	}
}
