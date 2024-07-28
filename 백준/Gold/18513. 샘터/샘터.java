import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int N, K;
	static int[] arr;
	static Set<Integer> visited = new HashSet<>();
	static Queue<int[]> queue = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			queue.add(new int[]{arr[i], 0});
			visited.add(arr[i]);
		}

		long answer = 0;
		int count = 0;

		while (count < K) {
			int[] now = queue.poll();
			int position = now[0];
			int distance = now[1];

			if (!visited.contains(position - 1)) {
				visited.add(position - 1);
				queue.add(new int[]{position - 1, distance + 1});
				answer += distance + 1;
				count++;
				if (count == K) {
					break;
				}
			}

			if (!visited.contains(position + 1)) {
				visited.add(position + 1);
				queue.add(new int[]{position + 1, distance + 1});
				answer += distance + 1;
				count++;
				if (count == K) {
					break;
				}
			}
		}

		System.out.println(answer);
	}
}
