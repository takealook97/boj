import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

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

		int left = 0;
		int right = 0;
		int maxLength = 0;
		Map<Integer, Integer> countMap = new HashMap<>();

		while (right < N) {
			countMap.put(arr[right], countMap.getOrDefault(arr[right], 0) + 1);

			while (countMap.get(arr[right]) > K) {
				countMap.put(arr[left], countMap.get(arr[left]) - 1);
				if (countMap.get(arr[left]) == 0) {
					countMap.remove(arr[left]);
				}
				left++;
			}

			maxLength = Math.max(maxLength, right - left + 1);
			right++;
		}

		System.out.println(maxLength);
	}
}
