import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int V, E, macdonaldCount, starbucksCount, mLimit, sLimit, macdonaldDummy, starbucksDummy;
	static boolean[] isMacDonald, isStarbucks;
	static ArrayList<Edge>[] listArr;
	static boolean[] visited;
	static long[] mDistance, sDistance;
	static PriorityQueue<Edge> pq;
	static long answer = Long.MAX_VALUE;

	static final long INF = Long.MAX_VALUE;

	static class Edge implements Comparable<Edge> {
		int node;
		long weight;

		public Edge(int node, long weight) {
			this.node = node;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.weight, o.weight);
		}
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		macdonaldDummy = V + 1;
		starbucksDummy = V + 2;

		listArr = new ArrayList[V + 3];
		for (int i = 1; i <= V + 2; i++) {
			listArr[i] = new ArrayList<>();
		}
		visited = new boolean[V + 3];
		mDistance = new long[V + 3];
		sDistance = new long[V + 3];

		int from, to, weight;
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			weight = Integer.parseInt(st.nextToken());
			listArr[from].add(new Edge(to, weight));
			listArr[to].add(new Edge(from, weight));
		}

		setMacdonaldAndStarbucks();
		setMacdonaldDist();
		setStarbucksDist();

		for (int i = 1; i <= V; i++) {
			if (!isMacDonald[i] && !isStarbucks[i] && mDistance[i] <= mLimit && sDistance[i] <= sLimit) {
				answer = Math.min(answer, mDistance[i] + sDistance[i]);
			}
		}

		if (answer == INF) {
			System.out.println(-1);
			return;
		}
		
		System.out.println(answer);
	}

	static void setMacdonaldAndStarbucks() throws IOException {
		isMacDonald = new boolean[V + 1];
		isStarbucks = new boolean[V + 1];
		int idx;

		st = new StringTokenizer(br.readLine());
		macdonaldCount = Integer.parseInt(st.nextToken());
		mLimit = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < macdonaldCount; i++) {
			idx = Integer.parseInt(st.nextToken());
			listArr[macdonaldDummy].add(new Edge(idx, 0));
			isMacDonald[idx] = true;
		}

		st = new StringTokenizer(br.readLine());
		starbucksCount = Integer.parseInt(st.nextToken());
		sLimit = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < starbucksCount; i++) {
			idx = Integer.parseInt(st.nextToken());
			listArr[starbucksDummy].add(new Edge(idx, 0));
			isStarbucks[idx] = true;
		}
	}

	static void setMacdonaldDist() {
		Arrays.fill(mDistance, INF);
		mDistance[macdonaldDummy] = 0;
		pq = new PriorityQueue<>();
		pq.add(new Edge(macdonaldDummy, 0));

		while (!pq.isEmpty()) {
			Edge now = pq.poll();

			if (!visited[now.node]) {
				visited[now.node] = true;

				for (Edge next : listArr[now.node]) {
					if (!visited[next.node]) {
						if (mDistance[next.node] > mDistance[now.node] + next.weight) {
							mDistance[next.node] = mDistance[now.node] + next.weight;
							pq.add(new Edge(next.node, mDistance[next.node]));
						}
					}
				}
			}
		}
	}

	static void setStarbucksDist() {
		pq = new PriorityQueue<>();
		Arrays.fill(visited, false);
		Arrays.fill(sDistance, INF);
		sDistance[starbucksDummy] = 0;

		pq.add(new Edge(starbucksDummy, 0));

		while (!pq.isEmpty()) {
			Edge now = pq.poll();

			if (!visited[now.node]) {
				visited[now.node] = true;

				for (Edge next : listArr[now.node]) {
					if (!visited[next.node]) {
						if (sDistance[next.node] > sDistance[now.node] + next.weight) {
							sDistance[next.node] = sDistance[now.node] + next.weight;
							pq.add(new Edge(next.node, sDistance[next.node]));
						}
					}
				}
			}
		}
	}
}
