import java.io.*;
import java.util.*;

public class Main {
	static int N, leftAnswer, rightAnswer;
	static int[] arr;
	static int minGap = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int left = 0;
		int right = N - 1;
		leftAnswer = arr[left];
		rightAnswer = arr[right];

		while (left < right) {
			int sum = arr[left] + arr[right];
			int gap = Math.abs(sum);
			if (gap < minGap) {
				minGap = gap;
				leftAnswer = arr[left];
				rightAnswer = arr[right];
			}

			if (sum > 0) {
				right--;
			} else {
				left++;
			}
		}

		System.out.println(leftAnswer + " " + rightAnswer);
	}
}
