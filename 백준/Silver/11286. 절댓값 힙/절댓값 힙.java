import java.io.*;
import java.util.*;

public class Main {
	static class Number implements Comparable<Number> {
		int num, absNum;

		public Number(int num) {
			this.num = num;
			this.absNum = Math.abs(num);
		}

		@Override
		public int compareTo(Number o) {
			if (this.absNum == o.absNum) {
				return this.num - o.num;
			}
			return this.absNum - o.absNum;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Number> pq = new PriorityQueue<>();
		StringBuilder sb = new StringBuilder();
		while (N-- > 0) {
			int x = Integer.parseInt(br.readLine());
			if (x != 0) {
				pq.add(new Number(x));
			} else {
				int answer = 0;
				if (!pq.isEmpty()) {
					answer = pq.poll().num;
				}
				sb.append(answer).append("\n");
			}
		}
		System.out.print(sb);
	}
}
