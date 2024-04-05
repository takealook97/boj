import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N, R, S, pilarLength, branchLength = 0;
	static ArrayList<Edge>[] listArr;
	static boolean[] visited;

	static class Edge {
		int node, weight;

		public Edge(int node, int weight) {
			this.node = node;
			this.weight = weight;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		listArr = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		for (int i = 0; i <= N; i++) {
			listArr[i] = new ArrayList<>();
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			listArr[from].add(new Edge(to, weight));
			listArr[to].add(new Edge(from, weight)); // Add reverse edge as well
		}
		findPillar(R, 0);
		branchLength = findMaxBranch(S, 0);

		System.out.println(pilarLength + " " + branchLength);
	}

	static void findPillar(int now, int total) {
		visited[now] = true;
		if (listArr[now].size() > (now == R ? 1 : 2) || listArr[now].size() == 1 && now != R) {
			S = now;
			pilarLength = total;
			return;
		}

		for (Edge edge : listArr[now]) {
			if (!visited[edge.node]) {
				findPillar(edge.node, total + edge.weight);
			}
		}
	}

	static int findMaxBranch(int now, int total) {
		int max = total;
		for (Edge edge : listArr[now]) {
			if (!visited[edge.node]) {
				visited[edge.node] = true;
				max = Math.max(max, findMaxBranch(edge.node, total + edge.weight));
				visited[edge.node] = false;
			}
		}
		return max;
	}
}
