import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static Number[] arr;
	static int answer = 0;

	static class Number implements Comparable<Number> {
		int num, idx;

		public Number(int num, int idx) {
			this.num = num;
			this.idx = idx;
		}

		@Override
		public int compareTo(Number o) {
			return this.num - o.num;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new Number[N];
		for (int i = 0; i < N; i++) {
			arr[i] = new Number(Integer.parseInt(br.readLine()), i);
		}
		Arrays.sort(arr);
		for (int i = 0; i < N; i++) {
			answer = Math.max(answer, arr[i].idx - i);
		}
		System.out.println(answer + 1);
	}
}
