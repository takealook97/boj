import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, A, B, S, E;
	static ArrayList<Integer>[] listArr;
	static boolean[] visited, check;
	static Queue<Integer> queue = new ArrayDeque<>();
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		listArr = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		parent = new int[N + 1];
		check = new boolean[N + 1];

		for (int i = 1; i <= N; i++) {
			listArr[i] = new ArrayList<>();
		}

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			listArr[A].add(B);
			listArr[B].add(A);
		}

		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= N; i++) {
			Collections.sort(listArr[i]);
		}

		List<Integer> toE = bfs(S, E);

		for (int node : toE) {
			check[node] = true;
		}

		check[S] = false;
		List<Integer> toS = bfsWithCheck(E, S);

		System.out.println(toE.size() + toS.size() - 2);
	}

	static List<Integer> bfs(int start, int end) {
		queue.clear();
		Arrays.fill(visited, false);
		Arrays.fill(parent, -1);

		visited[start] = true;
		queue.add(start);

		while (!queue.isEmpty()) {
			int now = queue.poll();

			if (now == end) {
				break;
			}

			for (int next : listArr[now]) {
				if (!visited[next]) {
					visited[next] = true;
					parent[next] = now;
					queue.add(next);
				}
			}
		}

		List<Integer> path = new ArrayList<>();
		for (int i = end; i != -1; i = parent[i]) {
			path.add(i);
		}
		Collections.reverse(path);
		return path;
	}

	static List<Integer> bfsWithCheck(int start, int end) {
		queue.clear();
		Arrays.fill(visited, false);
		Arrays.fill(parent, -1);

		visited[start] = true;
		queue.add(start);

		while (!queue.isEmpty()) {
			int now = queue.poll();

			if (now == end) {
				break;
			}

			for (int neighbor : listArr[now]) {
				if (!visited[neighbor] && !check[neighbor]) {
					visited[neighbor] = true;
					parent[neighbor] = now;
					queue.add(neighbor);
				}
			}
		}

		List<Integer> path = new ArrayList<>();
		for (int i = end; i != -1; i = parent[i]) {
			path.add(i);
		}
		Collections.reverse(path);
		return path;
	}
}
