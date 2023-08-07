import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static Stack<Num> stack = new Stack<>();
	static int[] answers;
	static final int HEAD = 0;

	static class Num {
		int index, number;

		public Num(int index, int num) {
			this.index = index;
			this.number = num;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		answers = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			getLeftIdx(i, Integer.parseInt(st.nextToken()));
		}

		StringBuilder sb = new StringBuilder();
		for (int answer : answers) {
			sb.append(answer).append(" ");
		}
		System.out.println(sb);
	}

	static void getLeftIdx(int idx, int input) {
		while (!stack.isEmpty()) {
			if (stack.peek().number >= input) {
				answers[idx] = stack.peek().index;
				break;
			}
			stack.pop();
		}
		if (stack.isEmpty()) {
			answers[idx] = HEAD;
		}
		stack.add(new Num(idx + 1, input));
	}
}
