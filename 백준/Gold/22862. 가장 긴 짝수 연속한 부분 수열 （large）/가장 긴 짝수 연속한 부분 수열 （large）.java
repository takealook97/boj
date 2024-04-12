import java.io.*;
import java.util.*;

public class Main {
	static int N, K;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int answer = 0;
		int left = 0, right = 0;
		int evenCount = 0, oddCount = 0;

		while (right < N) {
			if (arr[right] % 2 == 0) {
				evenCount++;
			} else {
				oddCount++;
			}

			while (oddCount > K) {
				if (arr[left] % 2 != 0) {
					oddCount--;
				} else {
					evenCount--;
				}
				left++;
			}

			answer = Math.max(answer, evenCount);
			right++;
		}

		System.out.println(answer);
	}
}
