import java.io.*;
import java.util.*;

public class Main {
	static int N, K, answer = 0;
	static int[] arr;
	static ArrayList<Integer> gapList = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		for (int i = 1; i < N; i++) {
			gapList.add(arr[i] - arr[i - 1]);
		}
		gapList.sort(Collections.reverseOrder());

		for (int i = K - 1; i < N - 1; i++) {
			answer += gapList.get(i);
		}

		System.out.println(answer);
	}
}
