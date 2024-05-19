import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, A, B, S, E;
	static ArrayList<Integer>[] listArr;
	static boolean[] visited, check;
	static Queue<Integer> queue = new ArrayDeque<>();
	static ArrayList<Integer> list = new ArrayList<>();
	static int[] parent;
	static int answer = 0;

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
			if (listArr[i].size() > 1) {
				Collections.sort(listArr[i]);
			}
		}

		bfs(S, E);

		for (int node : list) {
			check[node] = true;
		}

		check[S] = false;
		bfsWithCheck(E, S);

		System.out.println(answer);
	}

	static void bfs(int start, int end) {
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

		for (int i = end; i != -1; i = parent[i]) {
			list.add(i);
		}

		answer += list.size() - 1;
	}

	static void bfsWithCheck(int start, int end) {
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
				if (!visited[next] && !check[next]) {
					visited[next] = true;
					parent[next] = now;
					queue.add(next);
				}
			}
		}

		list.clear();
		for (int i = end; i != -1; i = parent[i]) {
			list.add(i);
		}

		answer += list.size() - 1;
	}
}
