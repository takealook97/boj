import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	static Jewel[] jewels;
	static int[] bags;
	static PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
	static long answer = 0;

	static class Jewel implements Comparable<Jewel> {
		int weight, price;

		public Jewel(int weight, int price) {
			this.weight = weight;
			this.price = price;
		}

		@Override
		public int compareTo(Jewel o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		jewels = new Jewel[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			jewels[i] = new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		bags = new int[K];
		for (int i = 0; i < K; i++) {
			bags[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(jewels);
		Arrays.sort(bags);

		int jewelIdx = 0;

		for (int i = 0; i < K; i++) {
			while (jewelIdx < N && jewels[jewelIdx].weight <= bags[i]) {
				pq.add(jewels[jewelIdx].price);
				jewelIdx++;
			}

			if (!pq.isEmpty()) {
				answer += pq.poll();
			}
		}

		System.out.println(answer);
	}
}
