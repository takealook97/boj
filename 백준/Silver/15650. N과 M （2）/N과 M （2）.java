import java.util.Scanner;

public class Main {
	static int N, M;
	static int[] arr;
	static int count = 0;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[M];
		comb(1, 0);
		System.out.print(sb);
	}

	static void comb(int start, int depth) {
		if (depth == M) {
			count++;
			for (int num : arr) {
				sb.append(num).append(" ");
			}
			sb.append("\n");
			return;
		}
		for (int i = start; i <= N; i++) {
			arr[depth] = i;
			comb(i + 1, depth + 1);
		}
	}
}
