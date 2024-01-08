import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] arr;
	static HashMap<Integer, Integer> count = new HashMap<>();
	static Stack<Integer> stack = new Stack<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		int[] answer = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			count.put(arr[i], count.getOrDefault(arr[i], 0) + 1);
		}

		for (int i = N - 1; i >= 0; i--) {
			while (!stack.isEmpty() && 
				count.get(arr[i]) >= count.get(arr[stack.peek()])) {
				stack.pop();
			}

			if (stack.isEmpty()) {
				answer[i] = -1;
			} else {
				answer[i] = arr[stack.peek()];
			}

			stack.push(i);
		}

		StringBuilder sb = new StringBuilder();
		for (int num : answer) {
			sb.append(num).append(" ");
		}
		System.out.println(sb);
	}
}
