import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] order;
	static ArrayList<Integer>[] listArr;
	static int[] degree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		listArr = new ArrayList[N + 1];
		order = new int[N + 1];
		degree = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			listArr[i] = new ArrayList<>();
			order[i] = 1;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			listArr[A].add(B);
			degree[B]++;
		}

		Queue<Integer> queue = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			if (degree[i] == 0) {
				queue.add(i);
			}
		}

		while (!queue.isEmpty()) {
			int current = queue.poll();
			for (int next : listArr[current]) {
				order[next] = Math.max(order[next], order[current] + 1);
				degree[next]--;
				if (degree[next] == 0) {
					queue.add(next);
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			sb.append(order[i]).append(" ");
		}

		System.out.println(sb);
	}
}
