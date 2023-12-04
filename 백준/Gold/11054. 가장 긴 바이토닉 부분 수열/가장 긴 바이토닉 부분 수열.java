import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] arr, dpL, dpR;
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1];
		dpL = new int[N + 1];
		dpR = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= N; i++) {
			dpL[i] = 1;
			for (int j = 1; j < i; j++) {
				if (arr[i] > arr[j]) {
					dpL[i] = Math.max(dpL[j] + 1, dpL[i]);
				}
			}
		}

		for (int i = N; i > 0; i--) {
			dpR[i] = 1;
			for (int j = N; j > i; j--) {
				if (arr[i] > arr[j]) {
					dpR[i] = Math.max(dpR[j] + 1, dpR[i]);
				}
			}
		}

		for (int i = 1; i <= N; i++) {
			answer = Math.max(answer, dpL[i] + dpR[i]);
		}

		System.out.println(answer - 1);
	}
}
