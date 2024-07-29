import java.util.Scanner;

public class Main {
	static int K;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		K = sc.nextInt();

		StringBuilder sb = new StringBuilder();

		while (K > 0) {
			if (K % 2 == 1) {
				sb.append('4');
			} else {
				sb.append('7');
			}
			K = (K - 1) / 2;
		}

		System.out.println(sb.reverse());
	}
}
