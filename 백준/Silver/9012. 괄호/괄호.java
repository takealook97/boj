import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	static String line;
	static Stack<Integer> stack;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			stack = new Stack<>();
			line = br.readLine();
			if (line.length() % 2 != 0) {
				sb.append("NO");
			} else {
				if (isPossible(line)) {
					sb.append("YES");
				} else {
					sb.append("NO");
				}
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}

	static boolean isPossible(String line) {
		for (int bracket : line.toCharArray()) {
			if (bracket == '(') {
				stack.add(bracket);
			} else {
				bracket--;
				if (stack.isEmpty()) {
					return false;
				} else if (bracket != stack.pop()) {
					return false;
				}
			}
		}
		return stack.isEmpty();
	}
}
