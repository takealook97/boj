import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static PriorityQueue<Integer>[] pqArr;
	static int[] order;
	static ArrayList<Integer> answer = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		pqArr = new PriorityQueue[N + 1];
		order = new int[N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			if (pqArr[A] == null) {
				pqArr[A] = new PriorityQueue<>();
			}
			pqArr[A].add(B);
			order[B]++;
		}

		setOrder();

		StringBuilder sb = new StringBuilder();
		for (int i : answer) {
			sb.append(i).append(" ");
		}
		System.out.println(sb);
	}

	static void setOrder() {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int num = 1; num <= N; num++) {
			if (order[num] == 0) {
				pq.add(num);
			}
		}

		while (!pq.isEmpty()) {
			int now = pq.poll();
			if (pqArr[now] != null) {
				for (int i : pqArr[now]) {
					order[i]--;
				}
			}
			answer.add(now);

			if (pqArr[now] != null) {
				for (int next : pqArr[now]) {
					if (order[next] == 0) {
						pq.add(next);
					}
				}
			}
		}
	}
}
