import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int T, N;
	static int[] parent;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		while (T-- > 0) {
			N = Integer.parseInt(br.readLine());

			make();

			for (int i = 0; i < N - 1; i++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				parent[B] = A;
			}

			visited = new boolean[N + 1];

			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			visited[A] = true;
			while (parent[A] != A) {
				A = parent[A];
				visited[A] = true;
			}

			do {
				B = parent[B];
			} while (!visited[B]);

			sb.append(B).append("\n");
		}

		System.out.print(sb);
	}

	static void make() {
		parent = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			parent[i] = i;
		}
	}
}
