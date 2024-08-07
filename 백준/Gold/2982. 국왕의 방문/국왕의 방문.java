import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int nodeCount, edgeCount, start, end, timeGap, visitedNodeCount;
	static int[] visitedNodes;
	static ArrayList<Range> kingMoves = new ArrayList<>();
	static ArrayList<Edge>[] listArr;
	static int[] distance;

	static final int INF = Integer.MAX_VALUE;

	static class Edge implements Comparable<Edge> {
		int node, weight;

		public Edge(int node, int weight) {
			this.node = node;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}

	static class Range {
		int from, to, timeStart, timeEnd;

		public Range(int from, int to, int timeStart, int timeEnd) {
			this.from = from;
			this.to = to;
			this.timeStart = timeStart;
			this.timeEnd = timeEnd;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		nodeCount = Integer.parseInt(st.nextToken());
		edgeCount = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		timeGap = Integer.parseInt(st.nextToken());
		visitedNodeCount = Integer.parseInt(st.nextToken());

		visitedNodes = new int[visitedNodeCount];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < visitedNodeCount; i++) {
			visitedNodes[i] = Integer.parseInt(st.nextToken());
		}

		listArr = new ArrayList[nodeCount + 1];
		for (int i = 1; i <= nodeCount; i++) {
			listArr[i] = new ArrayList<>();
		}

		for (int i = 0; i < edgeCount; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			listArr[from].add(new Edge(to, weight));
			listArr[to].add(new Edge(from, weight));
		}

		setKingMoves();
		move();

		System.out.println(distance[end] - timeGap);
	}

	static void setKingMoves() {
		int curTime = 0;
		for (int i = 0; i < visitedNodeCount - 1; i++) {
			int from = visitedNodes[i];
			int to = visitedNodes[i + 1];
			int moveTime = 0;
			for (Edge edge : listArr[from]) {
				if (edge.node == to) {
					moveTime = edge.weight;
					break;
				}
			}
			kingMoves.add(new Range(from, to, curTime, curTime + moveTime));
			curTime += moveTime;
		}
	}

	static void move() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		distance = new int[nodeCount + 1];
		Arrays.fill(distance, INF);
		distance[start] = timeGap;
		pq.add(new Edge(start, timeGap));

		while (!pq.isEmpty()) {
			Edge now = pq.poll();

			if (now.weight > distance[now.node]) {
				continue;
			}

			for (Edge next : listArr[now.node]) {
				int newTime = now.weight + next.weight;

				for (Range kingMove : kingMoves) {
					if ((kingMove.from == now.node && kingMove.to == next.node) || (kingMove.from == next.node
						&& kingMove.to == now.node)) {
						if (now.weight >= kingMove.timeStart && now.weight < kingMove.timeEnd) {
							newTime = kingMove.timeEnd + next.weight;
							break;
						}
					}
				}

				if (distance[next.node] > newTime) {
					distance[next.node] = newTime;
					pq.add(new Edge(next.node, newTime));
				}
			}
		}
	}
}
