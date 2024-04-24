import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
	static int[] parent;

	static class Pair implements Comparable<Pair> {
		int start;
		int end;
		int cost;

		public Pair(int start, int end, int cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}

		public int compareTo(Pair p) {
			return this.cost > p.cost ? 1 : -1;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]);
		int m = Integer.parseInt(input[1]);
		int edge_cnt = 0;

		parent = new int[n + 1];
		for (int i = 1; i <= n; i++)
			parent[i] = i;

		for (int i = 0; i < m; i++) {
			input = br.readLine().split(" ");
			int a = Integer.parseInt(input[0]);
			int b = Integer.parseInt(input[1]);

			if (find(a) == find(b)) continue;

			edge_cnt++;
			union(a, b);
		}

		PriorityQueue<Pair> pq = new PriorityQueue<>();
		ArrayList<Pair> list = new ArrayList<>();
		int total = 0;

		for (int i = 1; i <= n; i++) {
			input = br.readLine().split(" ");
			if (i == 1) continue;
			for (int j = i; j < n; j++) {
				pq.add(new Pair(i, j + 1, Integer.parseInt(input[j])));
			}
		}

		while (!pq.isEmpty() && edge_cnt < n - 2) {
			Pair temp = pq.poll();

			int a = temp.start;
			int b = temp.end;

			if (find(a) == find(b)) continue;

			union(a, b);
			total += temp.cost;
			edge_cnt++;
			list.add(new Pair(temp.start, temp.end, 0));
		}

		System.out.println(total + " " + list.size());

		for (Pair temp : list) {
			System.out.println(temp.start + " " + temp.end);
		}

		pq.clear();
	}

	public static void union(int a, int b) {
		a = find(a);
		b = find(b);

		if (a < b)
			parent[b] = a;
		else
			parent[a] = b;
	}

	public static int find(int a) {
		if (parent[a] == a)
			return a;

		return parent[a] = find(parent[a]);
	}
}
